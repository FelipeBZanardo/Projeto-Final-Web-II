package tech.ada.minhaquina.api.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.ada.minhaquina.api.usuario.UsuarioModel;

public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long> {
}
