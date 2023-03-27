package tech.ada.minhaquina.api.usuario;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "usuarios")
public class UsuarioModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private String password;

    public UsuarioModel(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public static UsuarioModel from(UsuarioRequest usuarioRequest) {
        return new UsuarioModel(usuarioRequest.getUsername(), usuarioRequest.getEmail(), usuarioRequest.getPassword());
    }
}
