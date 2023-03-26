package tech.ada.minhaquina.api.aposta;

import org.springframework.stereotype.Service;
import tech.ada.minhaquina.api.exception.DataJogoException;
import tech.ada.minhaquina.api.exception.NumeroSorteioException;
import tech.ada.minhaquina.api.sorteio.SorteioService;
import tech.ada.minhaquina.api.usuario.UsuarioModel;
import tech.ada.minhaquina.api.usuario.UsuarioRepository;
import tech.ada.minhaquina.client.QuinaRestClient;
import tech.ada.minhaquina.client.SorteioDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ApostaService {

    private final ApostaRepository apostaRepository;
    private final QuinaRestClient quinaRestClient;
    private final SorteioService sorteioService;
    private final UsuarioRepository usuarioRepository;

    public ApostaService(ApostaRepository apostaRepository, QuinaRestClient quinaRestClient, SorteioService sorteioService, UsuarioRepository usuarioRepository) {
        this.apostaRepository = apostaRepository;
        this.quinaRestClient = quinaRestClient;
        this.sorteioService = sorteioService;
        this.usuarioRepository = usuarioRepository;
    }

    public List<ApostaDTO> getAllApostas(Long userId) {
        usuarioRepository.findById(userId)
                .orElseThrow(()-> new NoSuchElementException("Id de usuário não existe"));

        return apostaRepository.findAllByUsuarioId(userId)
                .stream()
                .map(ApostaDTO::new)
                .toList();
    }

    public ApostaDTO getApostabyId(Long userId, Long apostaId){
        usuarioRepository.findById(userId)
                .orElseThrow(()-> new NoSuchElementException("Id de usuário não existe"));
        ApostaModel apostaModel = apostaRepository.findById(apostaId)
                .orElseThrow(()-> new NoSuchElementException("Id da aposta não existe"));
        return new ApostaDTO(apostaModel);
    }

    public ApostaDTO saveAposta(Long userId, ApostaDTO apostaDTO) {
        UsuarioModel usuarioModel = usuarioRepository.findById(userId)
                .orElseThrow(()-> new NoSuchElementException("Id de usuário não existe"));
        return salvarAposta(new ApostaModel(), apostaDTO, usuarioModel);
    }

    public ApostaDTO updateAposta(Long userId, Long apostaId, ApostaDTO apostaDTO){
        UsuarioModel usuarioModel = usuarioRepository.findById(userId)
                .orElseThrow(()-> new NoSuchElementException("Id de usuário não existe"));
        ApostaModel apostaAModificar = apostaRepository.findById(apostaId)
                .orElseThrow(()-> new NoSuchElementException("Id da aposta não existe"));
        return salvarAposta(apostaAModificar, apostaDTO, usuarioModel);
    }

    public void deleteAposta(Long userId, Long apostaId) throws NoSuchElementException{
        usuarioRepository.findById(userId)
                .orElseThrow(()-> new NoSuchElementException("Id de usuário não existe"));
        ApostaModel aposta = apostaRepository.findById(apostaId)
                .orElseThrow(()-> new NoSuchElementException("Id da aposta não existe"));
        apostaRepository.delete(aposta);
    }

    private ApostaDTO salvarAposta(ApostaModel apostaModel, ApostaDTO apostaDTO, UsuarioModel usuarioModel){
        verificarNumeroSorteio(apostaDTO.getNumeroSorteio());
        verificarDataJogo(apostaDTO.getNumeroSorteio(), apostaDTO.getDataJogo());
        apostaRepository.save(ApostaDTO.convertToModel(apostaModel, apostaDTO, usuarioModel));
        sorteioService.saveSorteio(apostaDTO.getNumeroSorteio());
        return apostaDTO;
    }

    private void verificarNumeroSorteio(Integer numeroSorteio) {
        SorteioDTO ultimoSorteio = quinaRestClient.getUltimoSorteio();
        if (numeroSorteio > ultimoSorteio.getNumeroConcursoProximo())
            throw new NumeroSorteioException(ultimoSorteio.getNumeroConcursoProximo());
    }

    private void verificarDataJogo(Integer numeroSorteio, LocalDate dataJogo) {
        SorteioDTO ultimoSorteio = quinaRestClient.getUltimoSorteio();
        if(ultimoSorteio.getNumeroConcursoProximo().equals(numeroSorteio)){
            if(dataJogo.isAfter(ultimoSorteio.getDataProximoSorteio()))
                throw new DataJogoException(numeroSorteio, ultimoSorteio.getDataProximoSorteio());
            return;
        }

        SorteioDTO sorteio = quinaRestClient.getSorteioByNumeroSorteio(numeroSorteio);
        if(dataJogo.isAfter(sorteio.getDataSorteio()))
            throw new DataJogoException(numeroSorteio, sorteio.getDataSorteio());
    }

}
