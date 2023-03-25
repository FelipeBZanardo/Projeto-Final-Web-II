package tech.ada.minhaquina.api.aposta;

import org.springframework.stereotype.Service;
import tech.ada.minhaquina.api.exception.DataJogoException;
import tech.ada.minhaquina.api.exception.NumeroSorteioException;
import tech.ada.minhaquina.client.QuinaRestClient;
import tech.ada.minhaquina.client.SorteioDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ApostaService {

    private final ApostaRepository apostaRepository;
    private final QuinaRestClient quinaRestClient;

    public ApostaService(ApostaRepository apostaRepository, QuinaRestClient quinaRestClient) {
        this.apostaRepository = apostaRepository;
        this.quinaRestClient = quinaRestClient;
    }

    public List<ApostaDTO> getAllApostas() {
        return apostaRepository.findAll().stream()
                .map(ApostaDTO::new)
                .toList();
    }

    public ApostaDTO getApostabyId(Long id) throws NoSuchElementException {
        ApostaModel apostaModel = apostaRepository.findById(id).orElseThrow();
        return new ApostaDTO(apostaModel);
    }

    public ApostaDTO saveAposta(ApostaDTO apostaDTO) {
        return criarAposta(new ApostaModel(), apostaDTO);
    }

    public ApostaDTO updateAposta(Long id, ApostaDTO apostaDTO) throws NoSuchElementException{
        ApostaModel apostaAModificar = apostaRepository.findById(id).orElseThrow();
        return criarAposta(apostaAModificar, apostaDTO);
    }

    public void deleteAposta(Long id) throws NoSuchElementException{
        ApostaModel aposta = apostaRepository.findById(id).orElseThrow();
        apostaRepository.delete(aposta);
    }

    private ApostaDTO criarAposta(ApostaModel apostaModel, ApostaDTO apostaDTO){
        verificarNumeroSorteio(apostaDTO.getNumeroSorteio());
        verificarDataJogo(apostaDTO.getNumeroSorteio(), apostaDTO.getDataJogo());
        apostaRepository.save(ApostaDTO.convertToModel(apostaModel, apostaDTO));
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
