package ru.online.shop.repositoriy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.online.shop.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    Comment getCommentById(Integer id);

}
