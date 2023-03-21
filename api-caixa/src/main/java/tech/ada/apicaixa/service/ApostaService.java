package tech.ada.apicaixa.service;

import org.springframework.stereotype.Service;
import tech.ada.apicaixa.model.dao.Aposta;
import tech.ada.apicaixa.model.dto.ApostaDTO;
import tech.ada.apicaixa.repository.ApostaRepository;

import java.util.List;

@Service
public class ApostaService {

    private final ApostaRepository apostaRepository;

    public ApostaService(ApostaRepository apostaRepository) {
        this.apostaRepository = apostaRepository;
    }

    public List<Aposta> getAllApostas() {
        return null;
    }

    public Aposta getApostabyId(Long id) {
        return null;
    }

    public Aposta saveAposta(ApostaDTO apostaDTO) {
        return null;
    }

    public Aposta updateAposta(Long id, ApostaDTO apostaDTO) {
        return null;
    }

    public void deleteAposta(Long id) {
    }
}
