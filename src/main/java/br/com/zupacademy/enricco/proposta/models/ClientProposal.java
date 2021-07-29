package br.com.zupacademy.enricco.proposta.models;

import br.com.zupacademy.enricco.proposta.models.enums.ClientProposalType;
import br.com.zupacademy.enricco.proposta.utils.clients.response.PropositionsType;
import br.com.zupacademy.enricco.proposta.utils.obfuscate.Obfuscator;
import br.com.zupacademy.enricco.proposta.validations.Document;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.net.URI;
import java.util.UUID;

@Entity
@Table(name = "proposal")
public class ClientProposal {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Type(type = "uuid-char")
    private UUID id;
    @Document
    @NotNull @NotBlank
    @Column(unique = true)
    private String document;
    @NotNull @NotBlank
    @Email
    private String email;
    @NotNull @NotBlank
    private String name;
    @NotNull @NotBlank
    private String address;
    @NotNull
    @Positive
    private BigDecimal salary;
    @Enumerated(value = EnumType.STRING)
    @NotNull
    private ClientProposalType status;
    @OneToOne(cascade = CascadeType.ALL)
    private PaymentCard card;
    private String user_id;

    @Deprecated
    private ClientProposal() {
    }

    public ClientProposal(@NotBlank String document,
                          @NotBlank @Email String email,
                          @NotBlank String name,
                          @NotBlank String address,
                          @NotNull @Positive BigDecimal salary,
                          @NotNull String user_id) {
        this.document = document;
        this.email = email;
        this.name = name;
        this.address = address;
        this.salary = salary;
        this.status = ClientProposalType.AGUARDANDO;
        this.user_id = user_id;
    }

    public static ClientProposal getOrThrow404(EntityManager manager, UUID client_id){
        ClientProposal clientProposal = manager.find(ClientProposal.class, client_id);
        if(clientProposal == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return clientProposal;
    }

    public URI buildResourceUrl(UriComponentsBuilder builder) {
        return builder.path("/proposal/{id}").buildAndExpand(this.id.toString()).toUri();
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

    public PaymentCard getCard() {
        return card;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setStatus(PropositionsType resultadoSolicitacao) {
        this.status = ClientProposalType.values()[resultadoSolicitacao.ordinal()];
    }

    public void setCard_id(PaymentCard card_id) {
        this.card = card_id;
    }

    @Override
    public String toString() {
        return "ClientProposal{" +
                "id=" + id +
                ", document='" + Obfuscator.obfuscateDocument(document) + '\'' +
                ", email='" + Obfuscator.obfuscateEmail(email)  + '\'' +
                ", name='" + Obfuscator.obfuscateName(name) + '\'' +
                ", address='" + Obfuscator.obfuscateAddress(address) + '\'' +
                ", salary=" + Obfuscator.obfuscateSalary(salary) +
                ", status=" + status +
                ", card=" + card +
                '}';
    }
}

