package ru.familyproject.ryabov.masteritsa.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.familyproject.ryabov.masteritsa.entity.User;
import ru.familyproject.ryabov.masteritsa.repository.UserRepository;

/**
 * Сервис для работы с сущностями <b>User</b>
 * @author Danila Ryabov
 *
 * @version 1.0
 */
@Service
public class UserService {
    //todo добавить javadoc

    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;

    private void save(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
}
