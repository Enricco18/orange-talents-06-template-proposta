package br.com.zupacademy.enricco.proposta.utils.clients.request;

import br.com.zupacademy.enricco.proposta.models.DigitalWallet;

import javax.validation.constraints.Email;
import java.time.LocalDateTime;

public class CarteiraDigital {
    private String email;
    private String carteira;

    public String getEmail() {
        return email;
    }

    public String getCarteira() {
        return carteira;
    }

    public CarteiraDigital(DigitalWallet wallet) {
        this.email = wallet.getEmail();
        this.carteira = wallet.getType().name();
    }

    //    {
//        "id": "string",
//            "email": "string",
//            "associadaEm": "2021-07-26T10:29:28.838Z",
//            "emissor": "string"
//    }
}
