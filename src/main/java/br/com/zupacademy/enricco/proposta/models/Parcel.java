package br.com.zupacademy.enricco.proposta.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Entity
public class Parcel {
    @Id
    private String id;
    @Positive
    private Integer quantity;
    @Positive
    private BigDecimal value;
    @ManyToOne
    private PaymentCard card;

    @Deprecated
    private Parcel() {
    }

    public Parcel(String id, Integer quantity, BigDecimal value, PaymentCard card) {
        this.id = id;
        this.quantity = quantity;
        this.value = value;
        this.card = card;
    }

    //    {
//        "id": "string",
//            "quantidade": 0,
//            "valor": 0
//    }
}
