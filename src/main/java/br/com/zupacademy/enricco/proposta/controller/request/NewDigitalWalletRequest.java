package br.com.zupacademy.enricco.proposta.controller.request;

import br.com.zupacademy.enricco.proposta.models.DigitalWallet;
import br.com.zupacademy.enricco.proposta.models.PaymentCard;
import br.com.zupacademy.enricco.proposta.models.enums.DigitalWalletType;
import br.com.zupacademy.enricco.proposta.utils.obfuscate.Obfuscator;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class NewDigitalWalletRequest {
    @Email @NotBlank
    private String email;
    private DigitalWalletType type;

    public String getEmail() {
        return email;
    }

    public DigitalWalletType getType() {
        return type;
    }

    public DigitalWallet toModel(PaymentCard card) {
       return new DigitalWallet(this.email,this.type,card);
    }

    @Override
    public String toString() {
        return "NewDigitalWalletRequest{" +
                "email='" + Obfuscator.obfuscateEmail(email) + '\'' +
                ", type=" + type +
                '}';
    }
}
