package br.com.zupacademy.enricco.proposta.events.handlers;

import br.com.zupacademy.enricco.proposta.events.ClassifyProposalEvent;
import br.com.zupacademy.enricco.proposta.models.ClientProposal;
import br.com.zupacademy.enricco.proposta.repositories.ClientProposalRepository;
import br.com.zupacademy.enricco.proposta.utils.clients.TransactionClient;
import br.com.zupacademy.enricco.proposta.utils.clients.request.ProposalToBeClassified;
import br.com.zupacademy.enricco.proposta.utils.clients.response.ClassifiedProposal;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Component
public class ClassifyProposalEventListener {
    private Logger logger = LoggerFactory.getLogger(ClassifyProposalEventListener.class);
    @Autowired
    private ClientProposalRepository repository;

    @Autowired
    private TransactionClient httpClient;

    @EventListener
    public void onApplicationEvent(ClassifyProposalEvent classifyProposalEvent){
        ClientProposal proposal = classifyProposalEvent.getProposal();
        try {
            ClassifiedProposal classifiedProposal = httpClient.classifyProposal(new ProposalToBeClassified(proposal));
            ClientProposal classified = classifiedProposal.toModel(repository);
            repository.save(classified);
        }catch (FeignException.FeignClientException e){
            logger.error(e.getMessage());
        }catch (Exception e){
            return;
        }
    }
}
