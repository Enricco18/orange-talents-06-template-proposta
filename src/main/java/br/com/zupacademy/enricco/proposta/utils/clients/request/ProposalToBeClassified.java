package br.com.zupacademy.enricco.proposta.utils.clients.request;

import br.com.zupacademy.enricco.proposta.models.ClientProposal;

public class ProposalToBeClassified {
    private String documento;
    private String nome;
    private String idProposta;

    public ProposalToBeClassified(ClientProposal proposal) {
        this.documento = proposal.getDocument();
        this.nome =proposal.getName();
        this.idProposta = proposal.getId().toString();
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public String getIdProposta() {
        return idProposta;
    }
}
