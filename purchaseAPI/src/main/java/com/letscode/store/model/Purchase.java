package com.letscode.store.model;

import com.letscode.store.dto.ResponsePurchaseDTO;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "purchase")
public class Purchase {

    @MongoId
    private String id;

    @Field(name = "purchase_date")
    private LocalDateTime purchaseDate;

    @Field(name = "total_purchased")
    private Double totalPurchased;

    @Field(name = "id_client")
    private String client;

    List<PurchaseProduct> products;

}
