package br.com.zupacademy.enricco.proposta.utils.clients.response;

import java.time.LocalDate;

public class AvisoViagem {
    private LocalDate validoAte;
    private String destino;

    public LocalDate getValidoAte() {
        return validoAte;
    }

    public String getDestino() {
        return destino;
    }

    //    {
//        "validoAte": "2021-07-26",
//            "destino": "string"
//    }
}
