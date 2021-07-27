package br.com.zupacademy.enricco.proposta.models;

import org.apache.tomcat.jni.Local;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import java.time.LocalDateTime;

@Entity
public class DigitalWallet {
    @Id
    private String id;
    @Email
    private String email;
    private LocalDateTime associatedOn;
    private String emissor;
    @ManyToOne
    private PaymentCard card;

    @Deprecated
    private DigitalWallet() {
    }

    public DigitalWallet(String id, String email, LocalDateTime associatedOn, String emissor, PaymentCard card) {
        this.id = id;
        this.email = email;
        this.associatedOn = associatedOn;
        this.emissor = emissor;
        this.card = card;
    }

    //    {
//        "id": "string",
//            "email": "string",
//            "associadaEm": "2021-07-26T10:29:28.838Z",
//            "emissor": "string"
//    }
}
