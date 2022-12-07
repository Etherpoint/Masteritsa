package ru.familyproject.ryabov.masteritsa.repository;

import org.springframework.stereotype.Repository;
import ru.familyproject.ryabov.masteritsa.entity.Comment;

import java.util.List;

@Repository
public interface CommentRepository {
    List<Comment> getAllCommentsById(Long id);
}
