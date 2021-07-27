package br.com.zupacademy.enricco.proposta.utils.clients.response;

import javax.persistence.Id;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class Parcela {
    private String id;
    private Integer quantidade;
    private BigDecimal valor;

    public String getId() {
        return id;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public BigDecimal getValor() {
        return valor;
    }

    //    {
//        "id": "string",
//            "quantidade": 0,
//            "valor": 0
//    }
}
