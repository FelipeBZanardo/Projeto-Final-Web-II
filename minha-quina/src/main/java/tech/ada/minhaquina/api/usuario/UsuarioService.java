package tech.ada.minhaquina.api.usuario;

import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UsuarioResponse saveUsuario(@Valid UsuarioRequest usuarioRequest) {
        verificarDuplicidade(usuarioRequest);
        UsuarioModel user = UsuarioModel.builder()
                .email(usuarioRequest.getEmail())
                .username(usuarioRequest.getUsername())
                .role(Enum.valueOf(Role.class, usuarioRequest.getRole().toUpperCase()))
                .password(passwordEncoder.encode(usuarioRequest.getPassword()))
                .build();
        UsuarioModel usuarioModel = usuarioRepository.save(user);
        return new UsuarioResponse(usuarioModel);
    }

    private void verificarDuplicidade(UsuarioRequest usuarioRequest) {
        Optional<UsuarioModel> optionalUsuario = usuarioRepository.findByEmail(usuarioRequest.getEmail());
        if (optionalUsuario.isPresent()) {
            throw new DuplicatedUserException("E-mail já cadastrado");
        }
        Optional<UsuarioModel> optionalUsuarioName = usuarioRepository.findByUsername(usuarioRequest.getUsername());
        if (optionalUsuarioName.isPresent()) {
            throw new DuplicatedUserException("Nome de usuário já cadastrado");
        }
    }

    public UsuarioResponse updateUsuario(Long id, UsuarioRequest usuarioRequest) {
        verificarDuplicidade(usuarioRequest);
        UsuarioModel user = UsuarioModel.builder()
                .email(usuarioRequest.getEmail())
                .username(usuarioRequest.getUsername())
                .role(Enum.valueOf(Role.class, usuarioRequest.getRole().toUpperCase()))
                .password(passwordEncoder.encode(usuarioRequest.getPassword()))
                .build();
        user.setId(id);

        usuarioRepository.save(user);
        return new UsuarioResponse(user);
    }

    public void deleteUsuario(Long id) {
        UsuarioModel usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Id de usuário não existe"));
        usuarioRepository.delete(usuario);
    }
}
