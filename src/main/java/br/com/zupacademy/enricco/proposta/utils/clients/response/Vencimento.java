package br.com.zupacademy.enricco.proposta.utils.clients.response;

import br.com.zupacademy.enricco.proposta.models.DueDate;
import br.com.zupacademy.enricco.proposta.models.PaymentCard;

import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

public class Vencimento {
    private String id;
    private Short dia;
    private LocalDateTime dataDeCriacao;

    public DueDate toModel(PaymentCard card) {
        return new DueDate(id,dia,dataDeCriacao,card);
    }

    public String getId() {
        return id;
    }

    public Short getDia() {
        return dia;
    }

    public LocalDateTime getDataDeCriacao() {
        return dataDeCriacao;
    }
}
