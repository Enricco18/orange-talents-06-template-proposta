package br.com.zupacademy.enricco.proposta.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Renegotiation {
    @Id
    private String id;
    @Positive
    private Integer quantity;
    @Positive
    private BigDecimal value;
    private LocalDateTime created_at;
    @OneToOne
    private PaymentCard card;

    @Deprecated
    private Renegotiation() {
    }

    public Renegotiation(String id, Integer quantity, BigDecimal value, LocalDateTime created_at, PaymentCard card) {
        this.id = id;
        this.quantity = quantity;
        this.value = value;
        this.created_at = created_at;
        this.card = card;
    }

    //    {
//        "id": "string",
//            "quantidade": 0,
//            "valor": 0,
//            "dataDeCriacao": "2021-07-26T10:29:28.838Z"
//    }
}
