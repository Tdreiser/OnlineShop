package ru.online.shop.repositoriy;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import ru.online.shop.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
