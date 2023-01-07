package ru.familyproject.ryabov.masteritsa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.familyproject.ryabov.masteritsa.entity.Role;
import ru.familyproject.ryabov.masteritsa.entity.User;
import ru.familyproject.ryabov.masteritsa.repository.RoleRepository;
import ru.familyproject.ryabov.masteritsa.repository.RoleRepositoryImpl;
import ru.familyproject.ryabov.masteritsa.repository.UserRepository;
import ru.familyproject.ryabov.masteritsa.repository.UserRepositoryImpl;

import java.util.HashSet;

/**
 * EN: Service for working with entities <b>User</b><br>
 * RU: Сервис для работы с сущностями <b>User</b>
 *
 * @see UserDetailsService
 *
 * @author Danila Ryabov
 *
 * @version 1.0
 */
@Service
public class UserService implements UserDetailsService {
    /**
     * EN:Private field for working with <b>User</b> entities in the database<br>
     * RU: Приватное поле для работы с сущностями <b>User</b> в БД
     *
     * @see UserRepositoryImpl
     */
    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final RoleRepository roleRepository;

    /**
     * EN: Method that returns an object for encrypting passwords<br>
     * RU: Метод, возвращающий объект для шифрования паролей
     */
    @Autowired
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * EN: Constructor for field initialization <b>userRepository</b><br>
     * RU: Конструктор для инициализации поля <b>userRepository</b>
     * @see #userRepository
     */
    public UserService(@Autowired UserRepositoryImpl userRepository, @Autowired RoleRepositoryImpl roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    /**
     * EN: Method for saving the entity <b>User</b> in the database<br>
     * RU: Метод для сохранения сущности <b>User</b> в БД
     * @see UserRepositoryImpl#save(User)
     */
    public void save(User user){
        user.setPassword(passwordEncoder().encode(user.getPassword()));
        user.setEnabled(true);
        HashSet<Role> roles = new HashSet<>();
        roles.add(roleRepository.getRoleByName("USER"));
        user.setRoles(roles);
        userRepository.save(user);
    }

    /**
     * EN: Method for deleting the <b>User</b> entity from the database<br>
     * RU: Метод для удаления сущности <b>User</b> из БД
     *
     * @see UserRepositoryImpl#delete(User)
     */
    public void delete(User user){
        userRepository.delete(user);
    }

    /**
     * EN: Method for searching and getting the entity <b>User</b> in the database by the field <b>username</b><br>
     * RU: Метод для поиска и получения сущности <b>User</b> в БД по полю <b>username</b>
     *
     * @see UserRepositoryImpl#findByUsername(String)
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }
}
