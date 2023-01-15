package ru.familyproject.ryabov.masteritsa.repository;

import org.springframework.stereotype.Repository;
import ru.familyproject.ryabov.masteritsa.entity.User;

/**
 * EN: Interface for working with <b>User</b> entities in the database<br>
 * RU: Интерфейс для работы с сущностями <b>User</b> в БД
 *
 * @see User
 *
 * @author Danila Ryabov
 * @version 1.0
 */
@Repository
public interface UserRepository {
    void save(User user);

    void delete(User user);

    User findByUsername(String username);
}
