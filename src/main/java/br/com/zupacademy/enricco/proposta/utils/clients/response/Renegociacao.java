package br.com.zupacademy.enricco.proposta.utils.clients.response;

import br.com.zupacademy.enricco.proposta.models.PaymentCard;
import br.com.zupacademy.enricco.proposta.models.Renegotiation;

import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Renegociacao {
    private String id;
    private Integer quantidade;
    private BigDecimal valor;
    private LocalDateTime dataDeCriacao;

    public String getId() {
        return id;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public LocalDateTime getDataDeCriacao() {
        return dataDeCriacao;
    }

    public Renegotiation toModel(PaymentCard card) {
        return new Renegotiation(this.id,this.quantidade,this.valor,this.dataDeCriacao,card);
    }

    //    {
//        "id": "string",
//            "quantidade": 0,
//            "valor": 0,
//            "dataDeCriacao": "2021-07-26T10:29:28.838Z"
//    }
}
