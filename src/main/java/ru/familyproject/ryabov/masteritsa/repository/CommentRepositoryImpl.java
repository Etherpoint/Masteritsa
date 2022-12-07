package ru.familyproject.ryabov.masteritsa.repository;

import org.hibernate.HibernateException;
import org.hibernate.QueryException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.familyproject.ryabov.masteritsa.entity.Comment;
import ru.familyproject.ryabov.masteritsa.entity.Product;
import ru.familyproject.ryabov.masteritsa.entity.ProductType;
import ru.familyproject.ryabov.masteritsa.entity.User;

import java.util.List;

@Repository
public class CommentRepositoryImpl implements CommentRepository{
    /**slf4j логгер*/
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductRepositoryImpl.class);
    /**Интерфейс для работы с БД*/
    private final SessionFactory sessionFactory;

    public CommentRepositoryImpl() {
        try{
            sessionFactory= new Configuration()
                    .configure()
                    .addAnnotatedClass(Comment.class)
                    .addAnnotatedClass(User.class)
                    .buildSessionFactory();
            LOGGER.info("Configuration in CommentRepositoryImpl was successful");
        }catch (HibernateException e){
            LOGGER.error("Error while configuring sessionFactory");
            throw new HibernateException(e);
        }
    }

    @Override
    public List<Comment> getAllCommentsById(Long id) {
        try(Session session = sessionFactory.openSession()){
            Query<Comment> result = session.createQuery("SELECT c FROM comment c WHERE c.product.id= :id", Comment.class);
            result.setParameter("id", id);
            LOGGER.info("Method getAllCommentsById completed successfully");
            return result.list();
        }catch (Exception e){
            LOGGER.error("Error in method getAllCommentsById");
            throw new QueryException(e);
        }
    }
}
