package com.letscode.store.controller;

import com.letscode.store.dto.RequestPurchaseDTO;
import com.letscode.store.dto.ResponsePurchaseDTO;
import com.letscode.store.model.Purchase;
import com.letscode.store.service.PurchaseService;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/purchase")
public class PurchaseController {
    private final PurchaseService purchaseService;

//    @ResponseStatus(HttpStatus.CREATED)
//    @PostMapping
//    public ResponsePurchaseDTO savePurchase(@RequestBody @Valid RequestPurchaseDTO requestPurchaseDTO){
////        return purchaseService.savePurchase(requestPurchaseDTO);
//    }

    @GetMapping
    public Page<ResponsePurchaseDTO> listPurchase(@QuerydslPredicate(root = Purchase.class)Predicate predicate, Pageable pageable ){
        return purchaseService.listPurchase(predicate, pageable);
    }

}
