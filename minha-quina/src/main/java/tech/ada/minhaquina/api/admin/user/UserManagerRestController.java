package tech.ada.minhaquina.api.admin.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.ada.minhaquina.api.usuario.UsuarioRepository;
import tech.ada.minhaquina.api.usuario.UsuarioResponse;

import java.util.List;

@RestController
@RequestMapping("/admin/users")
@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
public class UserManagerRestController {

    private final UsuarioRepository userJpaRepository;

    @GetMapping
    public List<UsuarioResponse> listar() {
        return this.userJpaRepository.findAll().stream()
            .map(UsuarioResponse::new)
            .toList();
    }

}
