package ru.familyproject.ryabov.masteritsa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.familyproject.ryabov.masteritsa.entity.Comment;
import ru.familyproject.ryabov.masteritsa.entity.Product;
import ru.familyproject.ryabov.masteritsa.repository.CommentRepository;
import ru.familyproject.ryabov.masteritsa.repository.ProductRepository;
import ru.familyproject.ryabov.masteritsa.repository.ProductRepositoryImpl;
import ru.familyproject.ryabov.masteritsa.repository.CommentRepositoryImpl;

import java.util.List;

/**
 * EN: Service for working with the entity Product<br>
 * RU: Сервис для работы с сущностью Product
 * @see Product
 * @author Danila Ryabov
 *
 * @version 1.0
 */
@Service public class ProductService {
    /**
     * EN: Private field for working with <b>Product</b> entities in the database<br>
     * RU: Приватное поле работы с сущностями <b>Product</b> в БД
     * @see ProductRepository
     */
    private final ProductRepository productRepository;

    /**
     * EN: Private field for working with <b>Comment</b> entities in the database<br>
     * RU: Приватное поле работы с сущностями <b>Comment</b> в БД
     * @see CommentRepository
     */
    private final CommentRepository commentRepository;

    /**
     * EN: Constructor for field initialization <b>repository</b><br>
     * RU: Конструктор для инициализации поля <b>repository</b>
     * @see ProductRepositoryImpl
     * @see CommentRepositoryImpl
     */
    public ProductService(@Autowired ProductRepository productRepository, @Autowired CommentRepository commentRepository) {
        this.productRepository = productRepository;
        this.commentRepository = commentRepository;
    }

    /**
     * EN: Method for getting the entire list of products from the database<br>
     * RU: Метод для получения всего списка продуктов из БД
     */
    public List<Product> getAll(){
        return productRepository.getAll();
    }

    /**
     * EN: Method for getting all products, with product_type_id = id<br>
     * RU: Метод для получения всех товаров, с product_type_id = id
     */
    public List<Product> getAllById(Long id){
        return productRepository.getAllById(id);
    }

    /**
     * EN: Method for getting all comments for a product with product_id = id<br>
     * RU: Метод для получения всех комментариев для товара с product_id = id
     */
    public List<Comment> getAllCommentsById(Long id){
        return commentRepository.getAllCommentsById(id);
    }

    /**
     * EN: Method for getting a product with product_id = id<br>
     * RU: Метод для получения товара с product_id = id
     */
    public Product getById(Long id){
        return productRepository.getById(id);
    }
}
