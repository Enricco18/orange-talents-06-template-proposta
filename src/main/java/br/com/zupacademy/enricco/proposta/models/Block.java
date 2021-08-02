package br.com.zupacademy.enricco.proposta.models;

import javax.persistence.*;
import java.awt.font.TextHitInfo;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
public class Block {
    @Id
    @Column(name = "ID", nullable = false)
    private String id;
    private String requestedFromAgent;
    private String requestedFromIp;
    private LocalDateTime requested_on;
    private Boolean active;
    @ManyToOne
    private PaymentCard card;

    @Deprecated
    private Block() {
    }


    public Block(String useragent,String ip, PaymentCard card) {
        this.id = UUID.randomUUID().toString();
        this.requested_on = LocalDateTime.now();
        this.active = true;
        this.requestedFromIp = ip;
        this.requestedFromAgent= useragent;
        this.card = card;
    }

    public String getId() {
        return id;
    }

    public PaymentCard getCard() {
        return card;
    }

    public String getRequestedFromAgent() {
        return requestedFromAgent;
    }

    public String getRequestedFromIp() {
        return requestedFromIp;
    }

    public LocalDateTime getRequested_on() {
        return requested_on;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Block block = (Block) o;
        return Objects.equals(id, block.id) && Objects.equals(requestedFromAgent, block.requestedFromAgent) && Objects.equals(requestedFromIp, block.requestedFromIp) && Objects.equals(requested_on, block.requested_on) && Objects.equals(active, block.active) && Objects.equals(card, block.card);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, requestedFromAgent, requestedFromIp, requested_on, active, card);
    }
}
