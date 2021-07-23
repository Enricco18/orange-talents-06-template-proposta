package br.com.zupacademy.enricco.proposta.events.scheduled;

import br.com.zupacademy.enricco.proposta.models.ClientProposal;
import br.com.zupacademy.enricco.proposta.models.enums.ClientProposalType;
import br.com.zupacademy.enricco.proposta.repositories.ClientProposalRepository;
import br.com.zupacademy.enricco.proposta.utils.clients.CardClient;
import br.com.zupacademy.enricco.proposta.utils.clients.response.Card;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class AssociateCard {
    @Autowired
    private ClientProposalRepository repository;

    @Autowired
    private CardClient client;

    private Logger logger = LoggerFactory.getLogger(AssociateCard.class);

    @Scheduled(fixedDelayString = "${associate-card.delay}")
    public void handle(){
        List<ClientProposal> resultList = repository.findByStatusAndCard(ClientProposalType.ELEGIVEL,null);
        if(resultList.isEmpty()){
            return;
        }

        resultList.stream().forEach(proposal -> {
            try{
               Card card = client.getClient(proposal.getId().toString());
               proposal.setCard_id(card.getId());
               repository.save(proposal);
               logger.info("Proposta salva: " + proposal);
            }catch (Exception e){
                logger.error(e.getMessage());
            }
        });
    }
}
