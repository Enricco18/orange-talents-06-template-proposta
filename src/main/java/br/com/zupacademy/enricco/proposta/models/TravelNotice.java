package br.com.zupacademy.enricco.proposta.models;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class TravelNotice {
    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    private LocalDate validUntil;
    @NotNull
    private String destination;
    private String requestedFromAgent;
    private String requestedFromIp;
    private LocalDateTime requested_on;
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

    public TravelNotice(LocalDate validUntil, String destination, String requestedFromAgent, String requestedFromIp, LocalDateTime requested_on, PaymentCard card) {
        this.validUntil = validUntil;
        this.destination = destination;
        this.requestedFromAgent = requestedFromAgent;
        this.requestedFromIp = requestedFromIp;
        this.requested_on = requested_on;
        this.card = card;
    }
}
