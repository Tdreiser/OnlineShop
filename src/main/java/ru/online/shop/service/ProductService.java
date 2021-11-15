package ru.online.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.online.shop.entity.Product;
import ru.online.shop.repositoriy.ProductRepository;


import java.util.List;
import java.util.Map;

@Service
public class ProductService {
    private final ProductRepository productRepository;


    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Integer getPriceByProductId(Integer id) {
        return productRepository.getById(id).getPrise();
    }

    public boolean updateProductStockValue(Integer id, Integer howManyBought) {
        if (productRepository.existsById(id)) {
            Product product = productRepository.getById(id);
            product.setStock(product.getStock() - howManyBought);
            productRepository.save(product);
            return true;
        }
        return false;
    }

    public Map<String, Integer> recountProductPrise(List<Product> productList, String currency) {
        return null;
    }


    public Product getProductById(Integer id) {
        return productRepository.getById(id);
    }


}
