package br.com.zupacademy.enricco.proposta.controller;

import br.com.zupacademy.enricco.proposta.models.PaymentCard;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

@RestController
@RequestMapping("/paymentcard")
public class CardController {
    @PersistenceContext
    private EntityManager manager;

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

        card.addBlock(request.getRemoteAddr(),request.getHeader("user-agent"));

        manager.merge(card);

        return ResponseEntity.ok().build();
    }
}
