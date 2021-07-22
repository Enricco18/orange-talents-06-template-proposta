package br.com.zupacademy.enricco.proposta.events;

import br.com.zupacademy.enricco.proposta.models.ClientProposal;
import org.springframework.context.ApplicationEvent;

public class ClassifyProposalEvent extends ApplicationEvent {
    private final ClientProposal proposal;
    public ClassifyProposalEvent(Object source, ClientProposal proposal) {
        super(source);
        this.proposal = proposal;
    }

    public ClientProposal getProposal() {
        return proposal;
    }
}
