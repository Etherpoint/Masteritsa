package ru.familyproject.ryabov.masteritsa.repository;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.familyproject.ryabov.masteritsa.entity.Comment;
import ru.familyproject.ryabov.masteritsa.utils.MySessionFactory;

import java.util.List;

/**
 * EN: Class for working with entities <b>Comment</b> in the database<br>
 * RU: Класс для работы с сущностями <b>Comment</b> в БД
 * @see CommentRepository
 * @see Comment
 *
 * @author Danila Ryabov
 *
 * @version 1.0
 */
@Repository
public class CommentRepositoryImpl implements CommentRepository{
    /**slf4j logger*/
    private static final Logger LOGGER = LoggerFactory.getLogger(CommentRepositoryImpl.class);
    /**
     * EN: Interface for working with the database<br>
     * RU: Интерфейс для работы с БД
     */
    private final SessionFactory sessionFactory;

    /**
     * EN: Constructors with <b>sessionFactory</b> configuration<br>
     * RU: Конструкторы с конфигурацией <b>sessionFactory</b>
     * @see SessionFactory
     */
    public CommentRepositoryImpl() {
        sessionFactory = MySessionFactory.getSessionFactory();
    }
    public CommentRepositoryImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }
    /**
     * EN: Method for getting a list of comments <b>Comment</b>, where product id = method parameter<br>
     * RU: Метод для получения списка комментариев <b>Comment</b>, где id продукта = параметру метода
     */
    @Override
    public List<Comment> getAllCommentsById(Long id) {
        try(Session session = sessionFactory.openSession()){
            Query<Comment> result = session.createQuery("SELECT c FROM comment c WHERE c.product.id= :id", Comment.class);
            result.setParameter("id", id);
            LOGGER.info("Method getAllCommentsById completed successfully");
            return result.list();
        }catch (HibernateException e ) {
            LOGGER.error("Error when opened session on sessionFactory in method getAllCommentsById from CommentRepositoryImpl");
            throw new HibernateException("Error when opened session on sessionFactory in method getAllCommentsById from CommentRepositoryImpl");
        }
    }
}
