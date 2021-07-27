package br.com.zupacademy.enricco.proposta.controller.request;

import br.com.zupacademy.enricco.proposta.models.Biometric;
import br.com.zupacademy.enricco.proposta.models.PaymentCard;
import br.com.zupacademy.enricco.proposta.utils.clients.response.Card;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Base64;

public class NewBiometricRequest {
    @NotNull @NotBlank @NotEmpty
    private String id;

    public String getId() {
        return id;
    }

    public Biometric toModel(PaymentCard card) {
        return new Biometric(card, this.id);
    }
}
