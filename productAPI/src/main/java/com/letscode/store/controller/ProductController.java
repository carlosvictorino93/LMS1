package com.letscode.store.controller;

import com.letscode.store.dto.AuthenticationDTO;
import com.letscode.store.dto.ProductDTO;
import com.letscode.store.dto.ValidationProductDTO;
import com.letscode.store.exception.AlreadyExistException;
import com.letscode.store.exception.NotAuthorizedException;
import com.letscode.store.service.ProductService;
import com.letscode.store.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    private final TokenService tokenService;

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping
    public ProductDTO saveProduct(@RequestBody @Valid ProductDTO productDTO, @RequestHeader("authorization") String token) throws AlreadyExistException {
        if (tokenService.getAuthenticate(token).getAuthenticated()){
        return productService.saveProduct(productDTO);
        }
        throw new NotAuthorizedException("não permitido");
    }

    @GetMapping
    public Page<ProductDTO> listProduct(ProductDTO productDTO, Pageable pageable, @RequestHeader("authorization") String token) {
        if (tokenService.getAuthenticate(token).getAuthenticated()){
        return productService.listProduct(productDTO, pageable);
        }
        throw new NotAuthorizedException("não permitido");
    }

    @PatchMapping("/{code}")
    public ProductDTO updateProduct(
            @RequestBody @Valid ProductDTO productDTO,
            @PathVariable String code,
            @RequestHeader("authorization") String token
    ) {
        if (tokenService.getAuthenticate(token).getAuthenticated()){
            return productService.updateProduct(productDTO, code);
        }
        throw new NotAuthorizedException("não permitido");
    }


    @DeleteMapping("/{code}")
    public void deleteProduct(
            @PathVariable String code,
            @RequestHeader("authorization") String token
    ) {
        if (tokenService.getAuthenticate(token).getAuthenticated()){
            productService.deleteProduct(code);
        }else{
            throw new NotAuthorizedException("não permitido");
        }
    }

    @GetMapping("/validation/{productCode}")
    public ValidationProductDTO getClientValidation(@PathVariable String productCode, @RequestHeader("authorization") String token){
        AuthenticationDTO authenticationDTO = tokenService.getAuthenticate(token);
        if (
                authenticationDTO.getAuthenticated()
                        && authenticationDTO.getRoles().contains("VALIDATOR")
        ){
            return ValidationProductDTO.convert(productService.getProduct(productCode));
        }else{
            throw new NotAuthorizedException("não permitido");
        }
    }

}
