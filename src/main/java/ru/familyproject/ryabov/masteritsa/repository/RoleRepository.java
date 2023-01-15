package ru.familyproject.ryabov.masteritsa.repository;

import org.springframework.stereotype.Repository;
import ru.familyproject.ryabov.masteritsa.entity.Role;

/**
 * EN: Interface for working with <b>Role</b> entities in the database<br>
 * RU: Интерфейс для работы с сущностями <b>Role</b> в БД
 * @see Role
 *
 * @author Danila Ryabov
 *
 * @version 1.0
 */
@Repository
public interface RoleRepository {
    Role getRoleByName(String name);
}
