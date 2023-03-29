package tech.ada.minhaquina.api.usuario;

import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/minha-quina/api/v1/usuarios")
@Log4j2
@PreAuthorize("hasRole('ADMIN')")
public class UsuarioRestController {

    private final UsuarioService usuarioService;

    public UsuarioRestController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }


    @PostMapping("/cadastro")
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioResponse saveUsuario(@RequestBody @Valid UsuarioRequest usuarioRequest) {
        return usuarioService.saveUsuario(usuarioRequest);
    }


    @PutMapping("/{id}/editar")
    @ResponseStatus(HttpStatus.OK)
    public UsuarioResponse updateUser(@PathVariable Long id, @RequestBody @Valid UsuarioRequest usuarioRequest) {
        return usuarioService.updateUsuario(id, usuarioRequest);
    }

    @DeleteMapping("/{id}/deletar")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable Long id){
        usuarioService.deleteUsuario(id);
    }
}
