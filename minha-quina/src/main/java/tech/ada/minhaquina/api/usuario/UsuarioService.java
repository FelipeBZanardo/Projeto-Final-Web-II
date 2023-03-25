package tech.ada.minhaquina.api.usuario;

import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository userRepository;

    public UsuarioService(UsuarioRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UsuarioModel saveUser(UsuarioModel user) {
        return null;
    }

    public UsuarioModel updateUser(Long id, UsuarioModel user) {
        return null;
    }

    public void deleteUser(Long id) {

    }
}
