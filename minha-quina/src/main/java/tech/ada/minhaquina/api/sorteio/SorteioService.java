package tech.ada.minhaquina.api.sorteio;

import org.springframework.stereotype.Service;
import tech.ada.minhaquina.api.aposta.ApostaModel;
import tech.ada.minhaquina.api.aposta.ApostaRepository;
import tech.ada.minhaquina.api.usuario.UsuarioRepository;
import tech.ada.minhaquina.client.QuinaRestClient;
import tech.ada.minhaquina.client.SorteioDTO;

import java.util.NoSuchElementException;

@Service
public class SorteioService {

    private final QuinaRestClient quinaRestClient;
    private final SorteioRepository sorteioRepository;
    private final ApostaRepository apostaRepository;
    private final UsuarioRepository usuarioRepository;

    public SorteioService(QuinaRestClient quinaRestClient, SorteioRepository sorteioRepository, ApostaRepository apostaRepository, UsuarioRepository usuarioRepository) {
        this.quinaRestClient = quinaRestClient;
        this.sorteioRepository = sorteioRepository;
        this.apostaRepository = apostaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public SorteioDTO getUltimoSorteio() {
        return quinaRestClient.getUltimoSorteio();
    }

    public SorteioDTO getSorteioByNumeroSorteio(Integer numero) {
        return quinaRestClient.getSorteioByNumeroSorteio(numero);
    }

    public void saveSorteio(Integer numeroSorteio) {
        SorteioDTO sorteioDTO = getSorteioByNumeroSorteio(numeroSorteio);
        sorteioRepository.save(SorteioDTO.convertToModel(new SorteioModel(), sorteioDTO));
    }


    public SorteioDTO getSorteioByAposta(Long userId, Long apostaId) {
        usuarioRepository.findById(userId)
                .orElseThrow(()-> new NoSuchElementException("Id de usuário não existe"));
        ApostaModel apostaModel = apostaRepository.findById(apostaId)
                .orElseThrow(()-> new NoSuchElementException("Id de aposta não existe"));
        Integer numeroSorteio = apostaModel.getNumeroSorteio();
        SorteioModel sorteioModel = sorteioRepository.findByNumeroSorteio(numeroSorteio).orElseThrow();
        return new SorteioDTO(sorteioModel);
    }
}
