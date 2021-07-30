package br.com.zupacademy.enricco.proposta.controller.request;

import br.com.zupacademy.enricco.proposta.models.PaymentCard;
import br.com.zupacademy.enricco.proposta.models.TravelNotice;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class NewTravelNoticeRequest {
    @NotNull @Future
    private LocalDate validUntil;
    @NotBlank
    private String destination;

    public LocalDate getValidUntil() {
        return validUntil;
    }

    public String getDestination() {
        return destination;
    }

    public TravelNotice toModel(String agent, String ip, PaymentCard card){
        return new TravelNotice(this.validUntil,this.destination, agent,ip, LocalDateTime.now(),card);
    }
}
