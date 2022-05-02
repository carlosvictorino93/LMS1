package com.letscode.store.model;

import lombok.*;

import javax.persistence.*;

@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "purchase_product")
public class PurchaseProduct {

    @EmbeddedId
    private PurchasedProductKey purchasedProductKey;

    @ManyToOne
    @MapsId("idPurchase")
    @JoinColumn(name = "id_purchase")
    private Purchase purchase;

    @ManyToOne
    @MapsId("idProduct")
    @JoinColumn(name = "id_product")
    private Product product;

    @Column(name = "quantity_purchased")
    private Integer quantityPurchased;
}
