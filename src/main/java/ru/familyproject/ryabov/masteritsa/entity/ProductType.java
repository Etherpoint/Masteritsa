package ru.familyproject.ryabov.masteritsa.entity;


import javax.persistence.*;

/**Entity-класс типа товара с полями <b>name</b>, <b>id</b>
 * @see Entity
 */
@Entity(name = "product_type")
public class ProductType {

    /**
     * Поле id в базе данных
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Поле названия типа товара
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
