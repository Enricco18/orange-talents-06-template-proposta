package br.com.zupacademy.enricco.proposta.controller;

import br.com.zupacademy.enricco.proposta.controller.request.NewClientProposalRequest;
import br.com.zupacademy.enricco.proposta.controller.response.ProposalDTO;
import br.com.zupacademy.enricco.proposta.events.ClassifyProposalEvent;
import br.com.zupacademy.enricco.proposta.models.ClientProposal;
import br.com.zupacademy.enricco.proposta.repositories.ClientProposalRepository;
import br.com.zupacademy.enricco.proposta.utils.clients.TransactionClient;
import br.com.zupacademy.enricco.proposta.utils.clients.request.ProposalToBeClassified;
import br.com.zupacademy.enricco.proposta.utils.clients.response.ClassifiedProposal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.TransactionManager;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/proposal")
public class ProposalController {
    private Logger logger = LoggerFactory.getLogger(ProposalController.class);
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ClientProposalRepository repository;

    @Autowired
    private ApplicationContext context;


    @PostMapping
    public ResponseEntity<?> createProposal(@RequestBody @Valid NewClientProposalRequest request, UriComponentsBuilder builder){
        logger.info("METHOD: POST | PATH: /proposal | ACTION: createProposal | BODY: " + request.toString());

        ClientProposal clientProposal = request.toModel();

        repository.save(clientProposal);

        context.publishEvent(new ClassifyProposalEvent(this,clientProposal));

        URI resourceUrl = clientProposal.buildResourceUrl(builder);

        return ResponseEntity.created(resourceUrl).body(resourceUrl);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProposalDTO> getProposal(@PathVariable("id") UUID client_id ){
        logger.info("METHOD: GET | PATH: /proposal/{id} | ACTION: getProposal | BODY: " + client_id.toString());
        ClientProposal clientProposal = ClientProposal.getOrThrow404(entityManager,client_id);

        ProposalDTO dto = new ProposalDTO(clientProposal);

        return ResponseEntity.ok(dto);
    }
}

