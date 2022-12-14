package ru.familyproject.ryabov.masteritsa.entity;


import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Entity-класс товара со свойствами <b>id</b>, <b>name</b>, <b>price</b>, <b>image</b>,
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
     * Поле id в базе данных
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Поле названия товара
     */
    @Column(name = "name")
    private String name;

    /**
     * Поле цены товара
     */
    @Column(name = "price")
    private Double price;

    /**
     * Поле названия картинки товара
     */
    @Column(name = "image")
    private String image;

    /**
     * Поле описания товара
     */
    @Column(name = "description", length = 65535)
    @Type(type = "text")
    private String description;

    /**
     * Поле цвета товара
     */
    @Column(name = "color")
    private String color;

    /**
     * Поле для связи с объектом класса <b>ProductType</b>
     *
     * @see ProductType
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_type_id")
    private ProductType productType;

    /**
     * Поле для связи со всеми комментариями к данному продукту
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

    public void setPrice(Double price) {
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

    public void setDescription(String description) {
        this.description = description;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
