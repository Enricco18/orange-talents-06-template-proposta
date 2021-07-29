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

    public Block(String useragent,String ip, PaymentCard card) {
        this.id = UUID.randomUUID().toString();
        this.blockedOn = LocalDateTime.now();
        this.active = true;
        this.blockedBy = useragent+" "+ip;
        this.card = card;
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getBlockedOn() {
        return blockedOn;
    }

    public String getBlockedBy() {
        return blockedBy;
    }

    public PaymentCard getCard() {
        return card;
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
        return Objects.equals(id, block.id) && Objects.equals(blockedOn, block.blockedOn) && Objects.equals(blockedBy, block.blockedBy) && Objects.equals(active, block.active) && Objects.equals(card, block.card);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, blockedOn, blockedBy, active, card);
    }
}
