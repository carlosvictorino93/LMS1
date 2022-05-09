package com.letscode.store.model;

import com.letscode.store.dto.ResponsePurchaseDTO;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "purchase")
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    @Column(name = "purchase_date")
    private LocalDateTime purchaseDate;

    @Column(name = "total_purchased")
    private Double totalPurchased;

    @ManyToOne
    @JoinColumn(name = "id_client")
    private Client client;

    @OneToMany(mappedBy = "purchase")
    List<PurchaseProduct> purchaseProducts;

    public static Purchase convert(ResponsePurchaseDTO dto){
        return Purchase.builder()
                .purchaseDate(dto.getPurchaseDate())
                .totalPurchased(dto.getTotalPurchased())
                .client(Client.convert(dto.getClientDTO()))
                .build();

    }
}
