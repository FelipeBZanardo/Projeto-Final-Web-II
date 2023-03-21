package tech.ada.apicaixa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.ada.apicaixa.model.dao.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
