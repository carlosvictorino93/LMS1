package com.letscode.store.service;

import com.letscode.store.dto.ProductDTO;
import com.letscode.store.exception.AlreadyExistException;
import com.letscode.store.exception.NotFoundException;
import com.letscode.store.model.Product;
import com.letscode.store.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final MongoTemplate mongoTemplate;
    private final ProductRepository productRepository;

    public ProductDTO saveProduct(ProductDTO productDTO) throws AlreadyExistException {
        Optional<Product> product = productRepository.findProductByProductCode(productDTO.getProductCode());
        product.ifPresent(s -> {
            throw new AlreadyExistException("Product Already Exist");
        });
        String id = UUID.randomUUID().toString();
        while (productRepository.findById(id).isPresent()){
            id = UUID.randomUUID().toString();
        }
        return ProductDTO.convert(productRepository.save(Product.convert(id, productDTO)));
    }

    public Page<ProductDTO> listProduct(ProductDTO productDTO, Pageable pageable) {
        List<Criteria> criteriaList = new ArrayList<>();
        if (productDTO.getProductCode() != null && !productDTO.getProductCode().isEmpty()){
            criteriaList.add(Criteria.where("productCode").is(productDTO.getProductCode()));
        }
        if (productDTO.getQuantity() != null){
            criteriaList.add(Criteria.where("quantity").is(productDTO.getQuantity()));
        }
        if (productDTO.getPrice() != null){
            criteriaList.add(Criteria.where("price").is(productDTO.getPrice()));
        }
        if(criteriaList.size() > 0){
            Criteria criteria = new Criteria().andOperator(criteriaList);
            Query query = Query.query(criteria).with(pageable);
            Query queryCount = Query.query(criteria);

            Long count = mongoTemplate.count(queryCount, Product.class);
            List<ProductDTO> productDTOListList = mongoTemplate.find(query, Product.class)
                    .stream()
                    .map(ProductDTO::convert)
                    .collect(Collectors.toList());


            return new PageImpl<>(
                    productDTOListList,
                    pageable,
                    count
            );
        }
        return productRepository.findAll(pageable).map(ProductDTO::convert);
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
