package tech.ada.minhaquina.api.usuario;

public class DuplicatedEmailException extends RuntimeException {

    public DuplicatedEmailException(String message) {
        super(message);
    }
}
