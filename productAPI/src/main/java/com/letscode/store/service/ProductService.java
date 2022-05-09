package com.letscode.store.service;

import com.letscode.store.dto.ProductDTO;
import com.letscode.store.exception.AlreadyExistException;
import com.letscode.store.exception.NotFoundException;
import com.letscode.store.model.Product;
import com.letscode.store.repository.ProductRepository;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final MongoTemplate mongoTemplate;
    private final ProductRepository productRepository;

    public ProductDTO saveProduct(ProductDTO productDTO) {
        Optional<Product> product = productRepository.findProductByProductCode(productDTO.getProductCode());
        product.ifPresent(s -> {
            throw new AlreadyExistException("Product Already Exist");
        });

        return ProductDTO.convert(productRepository.save(Product.convert(productDTO)));
    }

    public Page<ProductDTO> listProduct(Predicate predicate, Pageable pageable) {
        return productRepository.findAll(predicate, pageable).map(ProductDTO::convert);
    }

    public ProductDTO updateProduct(ProductDTO productDTO) {
        Product product = getProduct(productDTO.getProductCode());
        product.setQuantity(productDTO.getQuantity());
        product.setPrice(productDTO.getPrice());
        return ProductDTO.convert(productRepository.save(product));
    }

    public void deleteProduct(String code) {
        Product product = getProduct(code);
        productRepository.delete(product);
    }

    public Product getProduct(String code) {
        Optional<Product> product = productRepository.findProductByProductCode(code);
        product.orElseThrow(()-> new NotFoundException("Product with code "+ code +" not found"));
        return product.get();
    }
}
