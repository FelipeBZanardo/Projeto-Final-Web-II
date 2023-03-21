package tech.ada.apicaixa.controller;

import org.springframework.web.bind.annotation.*;
import tech.ada.apicaixa.model.dao.User;
import tech.ada.apicaixa.service.UserService;

@RestController
@RequestMapping("/minha-quina/api/v1/users")
public class UsuarioRestController {

    private final UserService userService;

    public UsuarioRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("login/{id}")
    public void login(){

    }

    @PostMapping("/cadastro")
    public User saveUser(@RequestBody User user){
        return userService.saveUser(user);
    }

    @PutMapping("/{id}/editar")
    public User updateUser(@PathVariable Long id, @RequestBody User user){
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/{id}/deletar")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }

}
