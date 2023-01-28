package ru.familyproject.ryabov.masteritsa.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

/**
 * EN: User entity class with properties <b>id</b>, <b>name</b>, <b>firstName</b>, <b>lastName</b>, <b>image</b>,
 * <b>password</b>, <b>confirmPassword</b>, <b>email</b><br>
 * <p>
 * RU: Entity-класс пользователя со свойствами <b>id</b>, <b>name</b>, <b>firstName</b>, <b>lastName</b>, <b>image</b>,
 * <b>password</b>, <b>confirmPassword</b>, <b>email</b>
 *
 * @author Danila Ryabov
 * @version 1.0
 * @see Entity
 */
@Entity(name = "users")
public class User implements UserDetails {

    /**
     * EN: The id field in the database<br>
     * RU: Поле id в базе данных
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * EN: User nickname field<br>
     * RU: Поле никнейма пользователя
     */
    @Column(name = "username")
    private String name;

    /**
     * EN: User first name field<br>
     * RU: Поле имени пользователя
     */
    @Column(name = "firstname")
    private String firstName;

    /**
     * EN: User last name field<br>
     * RU: Поле фамилии пользователя
     */
    @Column(name = "lastname")
    private String lastName;

    /**
     * EN: Field of the path to the user's avatar<br>
     * RU: Поле пути к аватару пользователя
     */
    @Column(name = "image")
    private String image;

    /**
     * EN: User password field<br>
     * RU: Поле пароля пользователя
     */
    @Column(name = "password")
    private String password;

    /**
     * EN: Password confirmation field<br>
     * RU: Поле подтверждения пароля
     *
     * @see Transient
     */
    @Transient
    private String confirmPassword;

    /**
     * EN: User email field<br>
     * RU: Поле имейла пользователя
     */
    @Column(name = "email")
    private String email;


    /**
     * EN: Field for communication with the collection of user roles<br>
     * RU: Поле для связи с коллекцией ролей пользователя
     *
     * @see Role
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    /**
     * EN: User status field (enabled/deactivated)<br>
     * RU: Поле состояния пользователя (активирован/деактивирован)
     */
    @Column(name = "enabled")
    private boolean enabled;

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

    @SuppressWarnings("unused")
    public String getFirstName() {
        return firstName;
    }

    @SuppressWarnings("unused")
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @SuppressWarnings("unused")
    public String getLastName() {
        return lastName;
    }

    @SuppressWarnings("unused")
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @SuppressWarnings("unused")
    public String getEmail() {
        return email;
    }

    @SuppressWarnings("unused")
    public void setEmail(String email) {
        this.email = email;
    }

    @SuppressWarnings("unused")
    public String getConfirmPassword() {
        return confirmPassword;
    }

    @SuppressWarnings("unused")
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
