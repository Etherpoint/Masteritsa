package ru.familyproject.ryabov.masteritsa.repository;

import org.springframework.stereotype.Repository;
import ru.familyproject.ryabov.masteritsa.entity.Comment;

import java.util.List;

/**
 * EN: Interface for working with <b>Comment</b> entities in the database<br>
 * RU: Интерфейс для работы с сущностями <b>Comment</b> в БД
 * @see Comment
 *
 * @author Danila Ryabov
 *
 * @version 1.0
 */
@Repository
public interface CommentRepository {
    List<Comment> getAllCommentsById(Long id);
}
