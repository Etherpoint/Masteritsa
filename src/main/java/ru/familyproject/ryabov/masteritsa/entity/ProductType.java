package ru.familyproject.ryabov.masteritsa.entity;


import javax.persistence.*;

/**
 * EN: Entity class of product type with fields <b>name</b>, <b>id</b><br>
 * RU: Entity-класс типа товара с полями <b>name</b>, <b>id</b>
 * @see Entity
 *
 * @author Danila Ryabov
 *
 * @version 1.0
 */
@Entity(name = "product_type")
public class ProductType {

    /**
     * EN: The id field in the database<br>
     * RU: Поле id в базе данных
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * EN: Product type name field<br>
     * RU: Поле названия типа товара
     */
    @Column(name = "name")
    private String name;

    //------------------------------------------- конец entity свойств-------------------------------------------------

    public ProductType() {
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
}
