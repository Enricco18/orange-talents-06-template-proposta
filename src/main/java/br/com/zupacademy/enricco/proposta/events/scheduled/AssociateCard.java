package br.com.zupacademy.enricco.proposta.events.scheduled;

import br.com.zupacademy.enricco.proposta.models.ClientProposal;
import br.com.zupacademy.enricco.proposta.models.PaymentCard;
import br.com.zupacademy.enricco.proposta.models.enums.ClientProposalType;
import br.com.zupacademy.enricco.proposta.repositories.ClientProposalRepository;
import br.com.zupacademy.enricco.proposta.utils.clients.CardClient;
import br.com.zupacademy.enricco.proposta.utils.clients.response.Card;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Component
public class AssociateCard {
    @Autowired
    private ClientProposalRepository repository;

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private CardClient client;

    private Logger logger = LoggerFactory.getLogger(AssociateCard.class);

    @Scheduled(fixedDelayString = "${associate-card.delay}")
    @Transactional
    public void handle(){
        List<ClientProposal> resultList = repository.findByStatusAndCard(ClientProposalType.ELEGIVEL,null);
        if(resultList.isEmpty()){
            return;
        }

        resultList.stream().forEach(proposal -> {
            try{
               Card card = client.getClient(proposal.getId().toString());
               PaymentCard paymentCard = card.toModel(proposal);
               proposal.setCard_id(paymentCard);
               repository.save(proposal);
               logger.info("Proposta salva: " + proposal);
            }catch (Exception e){
                logger.error(e.getMessage());
            }
        });
    }
}
