package ru.online.shop.repositoriy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.online.shop.entity.Category;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Category getCategoryByName(String name);
}
