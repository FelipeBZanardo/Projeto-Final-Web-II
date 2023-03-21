package tech.ada.apicaixa.service;

import org.springframework.stereotype.Service;
import tech.ada.apicaixa.model.dao.User;
import tech.ada.apicaixa.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(User user) {
        return null;
    }

    public User updateUser(Long id, User user) {
        return null;
    }

    public void deleteUser(Long id) {

    }
}
