package br.com.zupacademy.enricco.proposta.utils.clients.response;

import br.com.zupacademy.enricco.proposta.models.ClientProposal;
import br.com.zupacademy.enricco.proposta.models.enums.ClientProposalType;
import br.com.zupacademy.enricco.proposta.repositories.ClientProposalRepository;

import java.util.Optional;
import java.util.UUID;

public class ClassifiedProposal {
    private String documento;
    private String nome;
    private String idProposta;
    private PropositionsType resultadoSolicitacao;

    @Override
    public String toString() {
        return "ClassifiedProposal{" +
                "documento='" + documento + '\'' +
                ", nome='" + nome + '\'' +
                ", idProposta='" + idProposta + '\'' +
                ", resultadoSolicitacao=" + resultadoSolicitacao +
                '}';
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

    public PropositionsType getResultadoSolicitacao() {
        return resultadoSolicitacao;
    }

    public ClientProposal toModel(ClientProposalRepository repository) {

        Optional<ClientProposal> isProposal = repository.findById(UUID.fromString(this.idProposta));

        if(isProposal.isEmpty()){
            return null;
        }

        ClientProposal proposal = isProposal.get();
        proposal.setStatus(this.resultadoSolicitacao);

        return proposal;
    }
}
