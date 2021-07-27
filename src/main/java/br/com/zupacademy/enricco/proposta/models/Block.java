package br.com.zupacademy.enricco.proposta.models;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Block {
    @Id
    @Column(name = "ID", nullable = false)
    private String id;
    private LocalDateTime blockedOn;
    private String blockedBy;
    private Boolean active;
    @ManyToOne
    private PaymentCard card;

    @Deprecated
    private Block() {
    }

    public Block(String id, LocalDateTime blockedOn, String system, Boolean active, PaymentCard card) {
        this.id = id;
        this.blockedOn = blockedOn;
        this.blockedBy = system;
        this.active = active;
        this.card = card;
    }
}
