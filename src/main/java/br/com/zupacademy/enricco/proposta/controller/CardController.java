package br.com.zupacademy.enricco.proposta.controller;

import br.com.zupacademy.enricco.proposta.controller.request.NewTravelNoticeRequest;
import br.com.zupacademy.enricco.proposta.models.Block;
import br.com.zupacademy.enricco.proposta.models.PaymentCard;
import br.com.zupacademy.enricco.proposta.models.TravelNotice;
import br.com.zupacademy.enricco.proposta.utils.clients.CardClient;
import br.com.zupacademy.enricco.proposta.utils.clients.request.ResponsableSystem;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/paymentcard")
public class CardController {
    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private CardClient client;

    @PostMapping("/{id}/block")
    @Transactional
    public ResponseEntity<?> blockCard(@PathVariable("id") String cardDigit,
                                       Authentication auth,
                                       HttpServletRequest request
                                       ){
        PaymentCard card = PaymentCard.getOrThrow404(manager, cardDigit);

        Jwt jwt = (Jwt) auth.getPrincipal();

        if(!card.belongsTo(jwt.getSubject())){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Você não tem permissão pra adicionar bloqueio a esse cartão.");
        }

        Block block = card.addBlock(request.getRemoteAddr(),request.getHeader("user-agent"));

        try {
            client.blockCard(card.getNumber(),new ResponsableSystem(block));
        }catch (FeignException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Não foi possível bloquear o cartão");
        }

        manager.merge(card);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/travel-notice")
    @Transactional
    public ResponseEntity<?> travelNotice(  @PathVariable("id") String cardDigit,
                                            @RequestBody @Valid NewTravelNoticeRequest requestBody,
                                            Authentication auth,
                                            HttpServletRequest request){
        PaymentCard card = PaymentCard.getOrThrow404(manager, cardDigit);

        Jwt jwt = (Jwt) auth.getPrincipal();

        if(!card.belongsTo(jwt.getSubject())){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Você não tem permissão pra adicionar aviso viagem a esse cartão.");
        }

        TravelNotice notice = requestBody.toModel(request.getRemoteAddr(),request.getHeader("user-agent"),card);

        manager.persist(notice);

        return ResponseEntity.ok().build();
    }
}
