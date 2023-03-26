package tech.ada.minhaquina.api.aposta;


import org.springframework.data.jpa.repository.JpaRepository;
import tech.ada.minhaquina.api.aposta.ApostaModel;

import java.util.List;

public interface ApostaRepository extends JpaRepository<ApostaModel, Long> {
    List<ApostaModel> findAllByUsuarioId(Long userId);
}
