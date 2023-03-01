package ru.familyproject.ryabov.masteritsa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.familyproject.ryabov.masteritsa.entity.Comment;
import ru.familyproject.ryabov.masteritsa.entity.User;
import ru.familyproject.ryabov.masteritsa.service.CommentService;
import ru.familyproject.ryabov.masteritsa.service.ProductService;
import ru.familyproject.ryabov.masteritsa.service.UserService;
import ru.familyproject.ryabov.masteritsa.utils.Endpoints;

@Controller
@RequestMapping(Endpoints.COMMENT)
public class CommentController {
    /**
     * EN: Service for working with Entities <b>Comment</b> in the database<br>
     * RU: Сервис для работы с сущностями <b>Comment</b> в БД
     */
    private final CommentService commentService;

    /**
     * EN: Service for working with entities <b>Product</b> in the database<br>
     * RU: Сервис для работы с сущностями <b>Product</b> в БД
     */
    private final ProductService productService;

    /**
     * EN: Service for working with Entities <b>User</b> in the database<br>
     * RU: Сервис для работы с сущностями <b>User</b> в БД
     */
    private final UserService userService;

    /**
     * EN: Services initialization constructor<br>
     * RU: Конструктор для инициализации сервисов
     */
    @Autowired
    public CommentController(CommentService commentService, ProductService productService, UserService userService) {
        this.commentService = commentService;
        this.productService = productService;
        this.userService = userService;
    }

    @GetMapping(Endpoints.FIND_BY_ID)
    public void deleteComment(@PathVariable Long id) {
        Comment comment = commentService.getCommentById(id);
        commentService.deleteComment(comment);
    }

    /**
     * EN: POST method for saving comment<br>
     * RU: POST метод для сохранения комментария
     *
     * @return redirected file <b>product.html</b>
     */
    @PostMapping(Endpoints.FIND_BY_ID)
    public String saveComment(@AuthenticationPrincipal UserDetails user,
                              @PathVariable Long id,
                              Comment comment) {
        User userEntity = null;
        if (user != null) {
            userEntity = userService.loadUserByUsername(user.getUsername());
        }
        comment.setProduct(productService.getById(id));
        comment.setUser(userEntity);
        commentService.saveComment(comment);
        return "redirect:" + Endpoints.PRODUCT + "/" + id;
    }
}
