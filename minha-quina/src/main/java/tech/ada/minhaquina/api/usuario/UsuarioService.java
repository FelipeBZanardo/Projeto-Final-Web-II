package tech.ada.minhaquina.api.usuario;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioResponse saveUsuario(@Valid UsuarioRequest usuarioRequest) {
        Optional<UsuarioModel> optionalUsuario = usuarioRepository.findByEmail(usuarioRequest.getEmail());
        if (optionalUsuario.isPresent()) {
            throw new DuplicatedEmailException("E-mail já cadastrado");
        }
        UsuarioModel usuarioModel = usuarioRepository.save(UsuarioModel.from(usuarioRequest));
        return new UsuarioResponse(usuarioModel);
    }

    public UsuarioResponse updateUsuario(Long id, UsuarioRequest usuarioRequest) {
        UsuarioModel usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Id de usuário não existe"));

        usuario.setEmail(usuarioRequest.getEmail());
        usuario.setPassword(usuarioRequest.getPassword());
        usuario.setUsername(usuarioRequest.getUsername());
        usuarioRepository.save(usuario);
        return new UsuarioResponse(usuario);
    }

    public void deleteUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }


}
