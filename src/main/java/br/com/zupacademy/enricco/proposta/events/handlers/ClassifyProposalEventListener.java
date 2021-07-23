package br.com.zupacademy.enricco.proposta.events.handlers;

import br.com.zupacademy.enricco.proposta.events.ClassifyProposalEvent;
import br.com.zupacademy.enricco.proposta.models.ClientProposal;
import br.com.zupacademy.enricco.proposta.repositories.ClientProposalRepository;
import br.com.zupacademy.enricco.proposta.utils.clients.TransactionClient;
import br.com.zupacademy.enricco.proposta.utils.clients.request.ProposalToBeClassified;
import br.com.zupacademy.enricco.proposta.utils.clients.response.ClassifiedProposal;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import org.apache.tomcat.util.json.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.net.URI;

@Component
public class ClassifyProposalEventListener {
    private Logger logger = LoggerFactory.getLogger(ClassifyProposalEventListener.class);
    @Autowired
    private ClientProposalRepository repository;

    @Autowired
    private TransactionClient httpClient;

    @Value("${classify.url}")
    private String url;

    @EventListener
    @Async
    public void onApplicationEvent(ClassifyProposalEvent classifyProposalEvent) throws JsonProcessingException {
        ClientProposal proposal = classifyProposalEvent.getProposal();
        ClassifiedProposal classifiedProposal;
        try {
            classifiedProposal = httpClient.classifyProposal(URI.create(url),new ProposalToBeClassified(proposal));
        }catch (FeignException.FeignClientException e){

            if(e.status()!=422){
                logger.error(e.getMessage());
                return;
            }

            ObjectMapper objectMapper = new ObjectMapper();
            classifiedProposal = objectMapper.readValue( e.contentUTF8(), ClassifiedProposal.class);

        }catch (Exception e){
            logger.error(e.getMessage());
            return;
        }

        ClientProposal classified = classifiedProposal.toModel(repository);
        repository.save(classified);
    }
}
