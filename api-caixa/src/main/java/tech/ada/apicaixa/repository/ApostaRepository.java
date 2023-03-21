package tech.ada.apicaixa.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import tech.ada.apicaixa.model.dao.Aposta;

public interface ApostaRepository extends JpaRepository<Aposta, Long> {
}
