package tech.ada.minhaquina.api.aposta;

import org.springframework.stereotype.Service;
import tech.ada.minhaquina.api.aposta.ApostaModel;
import tech.ada.minhaquina.api.aposta.ApostaDTO;
import tech.ada.minhaquina.api.aposta.ApostaRepository;

import java.util.List;

@Service
public class ApostaService {

    private final ApostaRepository apostaRepository;

    public ApostaService(ApostaRepository apostaRepository) {
        this.apostaRepository = apostaRepository;
    }

    public List<ApostaModel> getAllApostas() {
        return null;
    }

    public ApostaModel getApostabyId(Long id) {
        return null;
    }

    public ApostaModel saveAposta(ApostaDTO apostaDTO) {
        return null;
    }

    public ApostaModel updateAposta(Long id, ApostaDTO apostaDTO) {
        return null;
    }

    public void deleteAposta(Long id) {
    }
}
