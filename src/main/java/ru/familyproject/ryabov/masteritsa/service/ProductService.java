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

/**Сервис для работы с сущностью Product*/
@Service public class ProductService {
    /**Приватное поле работы с сущностями <b>Product</b> в БД
     * @see ProductRepository
     */
    private final ProductRepository productRepository;

    /**Приватное поле работы с сущностями <b>Comment</b> в БД
     * @see CommentRepository
     */
    private final CommentRepository commentRepository;

    /**Конструктор для инициализации поля <b>repository</b>
     * @see ProductRepositoryImpl
     * @see CommentRepositoryImpl
     */
    public ProductService(@Autowired ProductRepository productRepository, @Autowired CommentRepository commentRepository) {
        this.productRepository = productRepository;
        this.commentRepository = commentRepository;
    }

    /**Метод для получения всего списка продуктов из БД*/
    public List<Product> getAll(){
        return productRepository.getAll();
    }

    /**
     * Метод для получения всех товаров, с product_type_id = id
     */
    public List<Product> getAllById(Long id){
        return productRepository.getAllById(id);
    }

    /**
     * Метод для получения всех комментариев для товара с product_id = id
     */
    public List<Comment> getAllCommentsById(Long id){
        return commentRepository.getAllCommentsById(id);
    }

    /**
     * Метод для получения товара с product_id = id
     */
    public Product getById(Long id){
        return productRepository.getById(id);
    }
}
