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
    public ProductDTO saveProduct(@RequestBody @Valid ProductDTO productDTO, @RequestHeader("authorization") String token) throws AlreadyExistException{
        if (tokenService.getAuthenticate(token)){
        return productService.saveProduct(productDTO);
        }
        throw new NotAuthorizedException("não permitido");
    }

    @GetMapping
    public Page<ProductDTO> listProduct(ProductDto productDto, Pageable pageable, @RequestHeader("authorization") String token) {
        if (tokenService.getAuthenticate(token)){
        return productService.listProduct(productDto, pageable);
        }
        throw new NotAuthorizedException("não permitido");
    }

    @PatchMapping("/{code}")
    public ProductDTO updateProduct(@RequestBody @Valid ProductDTO productDTO, @RequestHeader("authorization") String token) {
        if (tokenService.getAuthenticate(token)){
        return productService.updateProduct(productDTO);      }
        throw new NotAuthorizedException("não permitido");
    }


    @DeleteMapping("/{code}")
    public void deleteProduct(@PathVariable String code, @RequestHeader("authorization") String token) {
        if (tokenService.getAuthenticate(token)){
        productService.deleteProduct(code);
        }
        throw new NotAuthorizedException("não permitido");
    }
}
