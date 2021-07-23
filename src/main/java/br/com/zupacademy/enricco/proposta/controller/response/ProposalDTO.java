package br.com.zupacademy.enricco.proposta.controller.response;

import br.com.zupacademy.enricco.proposta.models.ClientProposal;
import br.com.zupacademy.enricco.proposta.models.enums.ClientProposalType;
import br.com.zupacademy.enricco.proposta.validations.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.UUID;

public class ProposalDTO {
    private UUID id;
    @Document @NotNull @NotBlank
    private String document;
    @NotNull @NotBlank @Email
    private String email;
    @NotNull @NotBlank
    private String name;
    @NotNull @NotBlank
    private String address;
    @NotNull @Positive
    private BigDecimal salary;
    private ClientProposalType status;
    private String card_id;

    public ProposalDTO(ClientProposal clientProposal) {
        this.id = clientProposal.getId();
        this.document = clientProposal.getDocument();
        this.address = clientProposal.getAddress();
        this.email = clientProposal.getEmail();
        this.name = clientProposal.getName();
        this.salary = clientProposal.getSalary();
        this.status = clientProposal.getStatus();
        this.card_id = clientProposal.getCard();
    }

    public String getCard_id() {
        return card_id;
    }

    public UUID getId() {
        return id;
    }

    public String getDocument() {
        return document;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public ClientProposalType getStatus() {
        return status;
    }
}
