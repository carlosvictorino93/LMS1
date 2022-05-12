package com.letscode.store.controller;

import com.letscode.store.dto.ProductDTO;
import com.letscode.store.model.Product;
import com.letscode.store.service.ProductService;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping
    public ProductDTO saveProduct(@RequestBody @Valid ProductDTO productDTO){

        return productService.saveProduct(productDTO);
    }

    @GetMapping
    public Page<ProductDTO> listProduct(@QuerydslPredicate(root = Product.class) Predicate predicate, Pageable pageable){
        return productService.listProduct(predicate, pageable);
    }

    @PatchMapping("/{code}")
    public ProductDTO updateProduct(@RequestBody @Valid ProductDTO productDTO) {
        return productService.updateProduct(productDTO);
    }


    @DeleteMapping("/{code}")
    public void deleteClient(@PathVariable String code) {
        productService.deleteProduct(code);
    }
}
