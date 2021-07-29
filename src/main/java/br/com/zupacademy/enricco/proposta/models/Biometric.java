package br.com.zupacademy.enricco.proposta.models;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.Base64;

@Entity
public class Biometric {
    @Id
    @GeneratedValue
    public Long id;

    @ManyToOne
    private PaymentCard card;
    @NotNull @NotBlank @NotEmpty
    private String base64_biometric;

    @CreationTimestamp
    private LocalDateTime created_on;

    @Deprecated
    private Biometric() {
    }

    public Biometric(PaymentCard card, String base64_biometric) {
        this.card = card;
        this.base64_biometric = base64_biometric;
    }

    public URI generateURL(UriComponentsBuilder builder) {
       return builder.path("/biometric/{id}").buildAndExpand(this.base64_biometric).toUri();
    }

    public boolean isValid() {
        Base64.Decoder decoder = Base64.getDecoder();
        try {
            byte[] bytes = decoder.decode(this.base64_biometric);
            return  true;
        }catch ( IllegalArgumentException e ){
            return false;
        }
    }
}
