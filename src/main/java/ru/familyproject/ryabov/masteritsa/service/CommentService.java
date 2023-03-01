package ru.familyproject.ryabov.masteritsa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.familyproject.ryabov.masteritsa.entity.Comment;
import ru.familyproject.ryabov.masteritsa.repository.CommentRepository;
import ru.familyproject.ryabov.masteritsa.repository.CommentRepositoryImpl;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class CommentService {
    /**
     * EN: Service for working with entities <b>Comment</b> in the database<br>
     * RU: Сервис для работы с сущностями <b>Comment</b> в БД
     */
    private final CommentRepository commentRepository;

    /**
     * EN: Service initialization constructor<br>
     * RU: Конструктор для инициализации сервиса
     */
    public CommentService(@Autowired CommentRepositoryImpl commentRepository) {
        this.commentRepository = commentRepository;
    }

    /**
     * EN: Method for finding a comment in the database<br>
     * RU: Метод для сохранения комментария в БД
     */
    public void saveComment(Comment comment) {
        Date date = new Date();
        SimpleDateFormat simpleDate = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        comment.setDateOfCreate(simpleDate.format(date));
        commentRepository.saveComment(comment);
    }

    public void deleteComment(Comment comment){
        commentRepository.deleteComment(comment);
    }

    public Comment getCommentById(Long id){
        return commentRepository.getCommentById(id);
    }
}