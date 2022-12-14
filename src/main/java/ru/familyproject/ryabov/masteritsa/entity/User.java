package ru.familyproject.ryabov.masteritsa.entity;

import javax.persistence.*;

//todo изменить описание полей сущности в javadoc

/**
 * Entity-класс пользователя со свойствами <b>id</b>, <b>name</b>, <b>image</b>
 *
 * @see Entity
 *
 * @author Danila Ryabov
 *
 * @version 1.0
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
     * Поле имени пользователя
     */
    @Column(name = "firstname")
    private String firstName;

    /**
     * Поле фамилии пользователя
     */
    @Column(name = "lastname")
    private String lastName;

    /**
     * Поле пути к аватару пользователя
     */
    @Column(name = "image")
    private String image;

    /**
     * Поле пароля пользователя
     */
    @Column(name = "password")
    private String password;

    /**Поле подтверждения пароля
     *@see Transient
     */
    @Transient
    private String confirmPassword;

    /**
     * Поле имейла пользователя
     */
    @Column(name = "email")
    private String email;

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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
