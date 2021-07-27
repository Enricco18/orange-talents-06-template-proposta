package br.com.zupacademy.enricco.proposta.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

@Entity
public class DueDate {
    @Id
    private String id;
    @Positive
    private Short day;
    private LocalDateTime created_at;
    @OneToOne
    private PaymentCard card;

    @Deprecated
    private DueDate() {
    }

    public DueDate(String id, Short day, LocalDateTime created_at, PaymentCard card) {
        this.id = id;
        this.day = day;
        this.created_at = created_at;
        this.card = card;
    }

    //    {
//        "id": "string",
//            "dia": 0,
//            "dataDeCriacao": "2021-07-26T10:29:28.838Z"
//    }
}
