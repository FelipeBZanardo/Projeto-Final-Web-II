package tech.ada.minhaquina.api.usuario;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/minha-quina/api/v1/users")
public class UsuarioRestController {

    private final UsuarioService userService;

    public UsuarioRestController(UsuarioService userService) {
        this.userService = userService;
    }

    @PostMapping("login/{id}")
    public void login(){

    }

    @PostMapping("/cadastro")
    public UsuarioModel saveUser(@RequestBody UsuarioModel user){
        return userService.saveUser(user);
    }

    @PutMapping("/{id}/editar")
    public UsuarioModel updateUser(@PathVariable Long id, @RequestBody UsuarioModel user){
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/{id}/deletar")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }

}
