package br.com.zupacademy.enricco.proposta.utils.clients.response;

import br.com.zupacademy.enricco.proposta.models.ClientProposal;
import br.com.zupacademy.enricco.proposta.models.DueDate;
import br.com.zupacademy.enricco.proposta.models.PaymentCard;
import br.com.zupacademy.enricco.proposta.models.Renegotiation;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Card {
    private String id;
    private LocalDateTime emitidoEm;
    private String titular;
    private UUID idProposta;
    private Renegociacao renegociacao;
    private Vencimento vencimento;
    private Integer limite;


    @Override
    public String toString() {
        return "Card{" +
                "id='" + id + '\'' +
                ", emitidoEm=" + emitidoEm +
                ", titular='" + titular + '\'' +
                ", idProposta=" + idProposta +
                '}';
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getEmitidoEm() {
        return emitidoEm;
    }

    public String getTitular() {
        return titular;
    }

    public UUID getIdProposta() {
        return idProposta;
    }

    public Renegociacao getRenegociacao() {
        return renegociacao;
    }

    public Vencimento getVencimento() {
        return vencimento;
    }

    public Integer getLimite() {
        return limite;
    }

    public PaymentCard toModel(ClientProposal proposal) {

        return new PaymentCard(this.id,this.emitidoEm,this.titular,proposal,this.renegociacao,this.vencimento,this.limite);
    }
}
