package ru.familyproject.ryabov.masteritsa.entity;

import javax.persistence.*;

/**
 * Entity-класс пользователя со свойствами <b>id</b>, <b>name</b>, <b>image</b>
 *
 * @see Entity
 */
@Entity(name = "users")
public class User {

    /**
     * Поле id в базе данных
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Поле никнейма пользователя
     */
    @Column(name = "username")
    private String name;

    /**
     * Поле пути к аватару пользователя
     */
    @Column(name = "image")
    private String image;

//------------------------------------------- конец entity свойств-------------------------------------------------

    public User() {
    }

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
