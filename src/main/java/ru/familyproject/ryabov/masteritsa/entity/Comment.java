package ru.familyproject.ryabov.masteritsa.entity;

import javax.persistence.*;

/**
 * EN: Comment entity class with properties <b>id</b>, <b>description</b>, <b>dateOfCreate</b>, <b>user</b>, <b>product</b><br>
 * RU: Entity-класс комментария со свойствами <b>id</b>, <b>description</b>, <b>dateOfCreate</b>, <b>user</b>, <b>product</b>
 * @see Entity
 *
 * @author Danila Ryabov
 *
 * @version 1.0
 */
@Entity(name = "comment")
public class Comment {

    /**
     * EN: The id field in the database<br>
     * RU: Поле id в базе данных
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * EN: Comment text field<br>
     * RU: Поле текста комментария
     */
    @Column(name = "description")
    private String description;

    /**
     * EN: Field with the date of creation of the comment<br>
     * RU: Поле с датой создания комментария
     */
    @Column(name = "date_of_create")
    private String dateOfCreate;

    /**
     * EN: Field for communication with the user of this comment<br>
     * RU: Поле для связи с пользователем данного комментария
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * EN: Field for communication with the product to which the comment is written<br>
     * RU: Поле для связи с продуктом, к которому написан комментарий
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Product product;

//------------------------------------------- конец entity свойств-------------------------------------------------

    public Comment() {
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

    @SuppressWarnings("unused")public void setDescription(String description) {
        this.description = description;
    }

    public String getDateOfCreate() {
        return dateOfCreate;
    }

    @SuppressWarnings("unused")public void setDateOfCreate(String dateOfCreate) {
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
