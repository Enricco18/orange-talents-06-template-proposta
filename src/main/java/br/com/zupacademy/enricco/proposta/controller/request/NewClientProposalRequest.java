package br.com.zupacademy.enricco.proposta.controller.request;

import br.com.zupacademy.enricco.proposta.models.ClientProposal;
import br.com.zupacademy.enricco.proposta.utils.obfuscate.Obfuscator;
import br.com.zupacademy.enricco.proposta.validations.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class NewClientProposalRequest {
    @Document
    @NotNull @NotBlank
    private String document;
    @NotNull @NotBlank
    @Email
    private String email;
    @NotNull @NotBlank
    private String name;
    @NotNull @NotBlank
    private String address;
    @NotNull @Positive
    private BigDecimal salary;

    public ClientProposal toModel(){
        return new ClientProposal(this.document,this.email,this.name,this.address,this.salary);
    }

    @Override
    public String toString() {
        return "NewClientRequest{" +
                "document='" + Obfuscator.obfuscateDocument(document) + '\'' +
                ", email='" + Obfuscator.obfuscateEmail(email) + '\'' +
                ", name='" + Obfuscator.obfuscateName(name) + '\'' +
                ", address='" + Obfuscator.obfuscateAddress(address) + '\'' +
                ", salary=" + Obfuscator.obfuscateSalary(salary) +
                '}';
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
}
