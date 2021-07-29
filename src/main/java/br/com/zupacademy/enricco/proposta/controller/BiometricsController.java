package br.com.zupacademy.enricco.proposta.controller;

import br.com.zupacademy.enricco.proposta.controller.request.NewBiometricRequest;
import br.com.zupacademy.enricco.proposta.models.Biometric;
import br.com.zupacademy.enricco.proposta.models.PaymentCard;
import br.com.zupacademy.enricco.proposta.utils.clients.CardClient;
import br.com.zupacademy.enricco.proposta.utils.clients.response.Card;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.TransactionManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/biometric")
public class BiometricsController {
    @PersistenceContext
    private EntityManager manager;


    @PostMapping("/{cardDigit}")
    @Transactional
    public ResponseEntity<?> createBiometric(@PathVariable("cardDigit") String cardDigit,
                                             @RequestBody @Valid NewBiometricRequest request,
                                             UriComponentsBuilder builder,
                                             Authentication auth){
        PaymentCard card = PaymentCard.getOrThrow404(manager, cardDigit);

        Jwt jwt = (Jwt) auth.getPrincipal();

        if(!card.belongsTo(jwt.getSubject())){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Você não tem permissão pra adicionar biometria a esse cartão.");
        }


        Biometric biometric = request.toModel(card);

        if(!biometric.isValid()){
            return ResponseEntity.badRequest().body("Digital fora do formato");
        }

        manager.persist(biometric);

        return ResponseEntity.created(biometric.generateURL(builder)).build();
    }

}
