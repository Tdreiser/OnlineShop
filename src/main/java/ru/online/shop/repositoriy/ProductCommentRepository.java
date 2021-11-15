package ru.online.shop.repositoriy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.online.shop.entity.ProductComment;

import java.util.List;

@Repository
public interface ProductCommentRepository extends JpaRepository<ProductComment, Integer> {
    Integer countByProductId(Integer id);

    List<ProductComment> getProductCommentsByProductId(Integer id);
}
