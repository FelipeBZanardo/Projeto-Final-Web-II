package tech.ada.minhaquina.api.aposta;


import org.springframework.data.jpa.repository.JpaRepository;
import tech.ada.minhaquina.api.aposta.ApostaModel;

public interface ApostaRepository extends JpaRepository<ApostaModel, Long> {
}
