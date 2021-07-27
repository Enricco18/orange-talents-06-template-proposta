package br.com.zupacademy.enricco.proposta.utils.clients.response;

import javax.validation.constraints.Email;
import java.time.LocalDateTime;

public class CarteiraDigital {
    private String id;
    private String email;
    private LocalDateTime associadaEm;
    private String emissor;

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getAssociadaEm() {
        return associadaEm;
    }

    public String getEmissor() {
        return emissor;
    }

    //    {
//        "id": "string",
//            "email": "string",
//            "associadaEm": "2021-07-26T10:29:28.838Z",
//            "emissor": "string"
//    }
}
