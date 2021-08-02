package br.com.zupacademy.enricco.proposta.models;

import br.com.zupacademy.enricco.proposta.models.enums.DigitalWalletType;
import org.apache.tomcat.jni.Local;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class DigitalWallet {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Type(type = "uuid-char")
    private UUID id;
    @Email @NotNull
    private String email;
    private LocalDateTime associatedOn;
    @Enumerated(value = EnumType.STRING)
    private DigitalWalletType type;
    @ManyToOne
    private PaymentCard card;

    @Deprecated
    private DigitalWallet() {
    }

    public DigitalWallet(String email, DigitalWalletType type, PaymentCard card) {
        this.email = email;
        this.associatedOn = LocalDateTime.now();
        this.type = type;
        this.card = card;
    }

    public String getEmail() {
        return email;
    }

    public DigitalWalletType getType() {
        return type;
    }

    public URI buildResourceUrl(UriComponentsBuilder builder) {
        return builder.path("/card/{idCard}/digital-wallet/{idWallet}").buildAndExpand(this.card.getNumber(),this.id.toString()).toUri();
    }

    //    {
//        "id": "string",
//            "email": "string",
//            "associadaEm": "2021-07-26T10:29:28.838Z",
//            "emissor": "string"
//    }
}
