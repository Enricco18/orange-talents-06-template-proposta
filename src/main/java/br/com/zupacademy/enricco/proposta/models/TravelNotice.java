package br.com.zupacademy.enricco.proposta.models;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class TravelNotice {
    @Id
    @GeneratedValue
    private Long id;
    private LocalDate validUntil;
    private String destination;
    @ManyToOne
    private PaymentCard card;

    @Deprecated
    private TravelNotice() {
    }

    public TravelNotice(LocalDate validUntil, String destination,PaymentCard card) {
        this.validUntil = validUntil;
        this.destination = destination;
        this.card = card;
    }


}
