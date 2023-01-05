package ru.familyproject.ryabov.masteritsa.entity;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

/**
 * EN: User roles entity class with properties <b>id</b>, <b>name</b>, <b>users</b><br>
 * RU: Entity-класс ролей пользователя со свойствами <b>id</b>, <b>name</b>, <b>users</b>
 *
 * @see Entity
 *
 * @author Danila Ryabov
 *
 * @version 1.0
 */
@Entity(name = "roles")
public class Role implements GrantedAuthority {
    /**
     * EN: The id field in the database<br>
     * RU: Поле id в базе данных
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * EN: Role name field<br>
     * RU: Поле названия роли
     */
    @Column(name = "name")
    private String name;

    /**
     * EN: Field for connection with the collection of users<br>
     * RU: Поле для связи с коллекцией пользователей
     */
    @Column(name = "users")
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    //------------------------------------------- конец entity свойств-------------------------------------------------

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", users=" + users +
                '}';
    }

    @Override
    public String getAuthority() {
        return getName();
    }
}