package ru.familyproject.ryabov.masteritsa.entity;


import org.hibernate.annotations.Type;

import javax.persistence.*;


/**
 * Entity-класс товара со свойствами <b>id</b>, <b>name</b>, <b>price</b>, <b>image</b>, <b>description</b>, <b>productType</b>
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
     * Поле для связи с объектом класса <b>ProductType</b>
     *
     * @see ProductType
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_type_id")
    private ProductType productType;

    /**
     * Конструктор класса по умолчанию
     */
    public Product() {
    }

    /**
     * Метод для доступа к приватному полю <b>id</b>
     *
     * @see Product#id
     * @see Product#setId(Long)
     */
    public Long getId() {
        return id;
    }

    /**
     * Метод для изменения значения приватного поля <b>id</b>
     *
     * @param id
     * @see Product#id
     * @see Product#getId()
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Метод для доступа к приватному полю <b>name</b>
     *
     * @see Product#name
     * @see Product#setName(String)
     */
    public String getName() {
        return name;
    }

    /**
     * Метод для изменения значения приватного поля <b>name</b>
     *
     * @param name
     * @see Product#name
     * @see Product#getName()
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Метод для получения значения приватного поля <b>price</b>
     *
     * @see Product#price
     * @see Product#setPrice(Double)
     */
    public Double getPrice() {
        return price;
    }

    /**
     * Метод для изменения значения поля <b>price</b>
     *
     * @param price
     * @see Product#price
     * @see Product#getPrice()
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * Метод для доступа к приватному полю <b>image</b>
     *
     * @see Product#image
     * @see Product#setImage(String)
     */
    public String getImage() {
        return image;
    }

    /**
     * Метод для изменения значения приватного поля <b>image</b>
     *
     * @param image
     * @see Product#image
     * @see Product#getImage()
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * Метод для получения доступа к приватному полю <b>description</b>
     *
     * @see Product#description
     * @see Product#setDescription(String)
     */
    public String getDescription() {
        return description;
    }

    /**
     * Метод для изменения значения приватного поля <b>description</b>
     *
     * @param description
     * @see Product#description
     * @see Product#getDescription()
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Метод для получения доступа к приватоному полю <b>productType</b>
     *
     * @see Product#productType
     * @see Product#setProductType(ProductType)
     * @see ProductType
     */
    public ProductType getProductType() {
        return productType;
    }

    /**
     * Метод для изменения значения приватного поля <b>productType</b>
     *
     * @param productType
     * @see Product#productType
     * @see Product#getProductType()
     * @see ProductType
     */
    public void setProductType(ProductType productType) {
        this.productType = productType;
    }
}
