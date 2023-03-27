package tech.ada.minhaquina.api.usuario;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/minha-quina/api/v1/usuarios")
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

    @PostMapping("/login/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void login(){
    }

    @PutMapping("/{id}/editar")
    @ResponseStatus(HttpStatus.OK)
    public UsuarioResponse updateUser(@PathVariable Long id, @RequestBody UsuarioRequest usuarioRequest) {
        return usuarioService.updateUsuario(id, usuarioRequest);
    }

    @DeleteMapping("/{id}/deletar")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable Long id){
        usuarioService.deleteUsuario(id);
    }
}
