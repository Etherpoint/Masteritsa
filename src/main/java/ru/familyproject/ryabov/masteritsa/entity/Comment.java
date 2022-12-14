package ru.familyproject.ryabov.masteritsa.entity;

import javax.persistence.*;

/**
 * Entity-класс комментария со свойствами <b>id</b>, <b>description</b>, <b>dateOfCreate</b>, <b>user</b>, <b>product</b>
 * @see Entity
 *
 * @author Danila Ryabov
 *
 * @version 1.0
 */
@Entity(name = "comment")
public class Comment {

    /**
     * Поле id в базе данных
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Поле текста комментария
     */
    @Column(name = "description")
    private String description;

    /**
     * Поле с датой создания комментария
     */
    @Column(name = "date_of_create")
    private String dateOfCreate;

    /**
     * Поле для связи с пользователем данного комментария
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * Поле для связи с продуктом, к которому написан комментарий
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Product product;

//------------------------------------------- конец entity свойств-------------------------------------------------

    public Comment() {
    }

    public Comment(Long id, String description, String dateOfCreate, User user, Product product) {
        this.id = id;
        this.description = description;
        this.dateOfCreate = dateOfCreate;
        this.user = user;
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateOfCreate() {
        return dateOfCreate;
    }

    public void setDateOfCreate(String dateOfCreate) {
        this.dateOfCreate = dateOfCreate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
