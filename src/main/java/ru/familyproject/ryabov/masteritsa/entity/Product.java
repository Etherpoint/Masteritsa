package ru.familyproject.ryabov.masteritsa.entity;


import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


/**
 * EN: Product entity class with properties <b>id</b>, <b>name</b>, <b>price</b>, <b>image</b>,
 * <b>description</b>, <b>color</b>, <b>productType</b>, <b>comments</b><br>
 *
 * RU: Entity-класс товара со свойствами <b>id</b>, <b>name</b>, <b>price</b>, <b>image</b>,
 * <b>description</b>, <b>color</b>, <b>productType</b>, <b>comments</b>
 *
 * @see Entity
 *
 * @author Danila Ryabov
 *
 * @version 1.0
 */
@Entity(name = "product")
public class Product {

    /**
     * EN:The id field in the database<br>
     * RU:Поле id в базе данных
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * EN: Product name field<br>
     * RU: Поле названия товара
     */
    @Column(name = "name")
    private String name;

    /**
     * EN: Product price field<br>
     * RU: Поле цены товара
     */
    @Column(name = "price")
    private Double price;

    /**
     * EN: Product image name field<br>
     * RU: Поле названия картинки товара
     */
    @Column(name = "image")
    private String image;

    /**
     * EN: Product description field<br>
     * RU: Поле описания товара
     */
    @Column(name = "description", length = 65535)
    @Type(type = "text")
    private String description;

    /**
     * EN: Product color field<br>
     * RU: Поле цвета товара
     */
    @Column(name = "color")
    private String color;

    /**
     * EN: Field for connection with an object of class <b>ProductType</b><br>
     * RU: Поле для связи с объектом класса <b>ProductType</b>
     *
     * @see ProductType
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_type_id")
    private ProductType productType;

    /**
     * EN: Field for communication with all comments on this product<br>
     * RU: Поле для связи со всеми комментариями к данному продукту
     * @see Comment
     */
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private List<Comment> comments = new ArrayList<>();

    //------------------------------------------- конец entity свойств-------------------------------------------------

    public Product() {
    }

    public Product(Long id, String name, Double price, String image, String description, String color, ProductType productType, List<Comment> comments) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
        this.description = description;
        this.color = color;
        this.productType = productType;
        this.comments = comments;
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

    public Double getPrice() {
        return price;
    }

    @SuppressWarnings("unused")public void setPrice(Double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    public String getDescription() {
        return description;
    }

    @SuppressWarnings("unused")public void setDescription(String description) {
        this.description = description;
    }

    @SuppressWarnings("unused")public String getColor() {
        return color;
    }

    @SuppressWarnings("unused")public void setColor(String color) {
        this.color = color;
    }

    @SuppressWarnings("unused")public ProductType getProductType() {
        return productType;
    }

    @SuppressWarnings("unused")public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    @SuppressWarnings("unused")public List<Comment> getComments() {
        return comments;
    }

    @SuppressWarnings("unused")public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
