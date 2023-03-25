package tech.ada.minhaquina.api.usuario;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UsuarioRequest {
    private String username;
    private String password;
    private String email;
}
