package com.letscode.store.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class PurchasedProductKey implements Serializable {

    @Column(name = "id_purchase")
    private Long idPurchase;

    @Column(name = "id_product")
    private Long idProduct;
}
