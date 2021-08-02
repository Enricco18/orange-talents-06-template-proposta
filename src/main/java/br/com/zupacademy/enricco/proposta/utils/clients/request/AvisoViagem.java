package br.com.zupacademy.enricco.proposta.utils.clients.request;

import br.com.zupacademy.enricco.proposta.models.TravelNotice;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class AvisoViagem {
    @NotNull
    private LocalDate validoAte;
    @NotBlank
    private String destino;

    public LocalDate getValidoAte() {
        return validoAte;
    }

    public String getDestino() {
        return destino;
    }

    public AvisoViagem(TravelNotice notice) {
        this.destino = notice.getDestination();
        this.validoAte = notice.getValidUntil();
    }

    //    {
//        "validoAte": "2021-07-26",
//            "destino": "string"
//    }
}
