package tech.ada.minhaquina.api.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import tech.ada.minhaquina.api.system.JwtService;
import tech.ada.minhaquina.api.usuario.UsuarioModel;
import tech.ada.minhaquina.api.usuario.UsuarioRepository;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Log
public class AuthenticationRestController {

    private final UsuarioRepository userJpaRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public AuthenticationResponse login(@RequestBody AuthenticationRequest request){
        var authentication = new UsernamePasswordAuthenticationToken(request.username(), request.password());
        authenticationManager.authenticate(authentication);
        UsuarioModel user = userJpaRepository.findByUsername(request.username()).orElseThrow();
        String token = jwtService.createToken(user);
        jwtService.destroyToken(false);
        return new AuthenticationResponse(token);
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logut(){
        jwtService.destroyToken(true);
        return ResponseEntity.ok("Logout feito com sucesso");
    }

}
