package br.com.zupacademy.enricco.proposta.controller;

import br.com.zupacademy.enricco.proposta.controller.request.NewDigitalWalletRequest;
import br.com.zupacademy.enricco.proposta.models.DigitalWallet;
import br.com.zupacademy.enricco.proposta.models.PaymentCard;
import br.com.zupacademy.enricco.proposta.utils.clients.CardClient;
import br.com.zupacademy.enricco.proposta.utils.clients.request.CarteiraDigital;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/card")
public class DigitalWalletController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private CardClient client;

    @PostMapping("/{id}/digital-wallet")
    @Transactional
    public ResponseEntity<?> addDigitalWallet(@PathVariable("id") String cardDigit,
                                              @RequestBody @Valid NewDigitalWalletRequest request,
                                              Authentication auth,
                                              UriComponentsBuilder builder){
        logger.info("METHOD: POST | PATH: /cars/{id}/digital-wallet | ACTION: addDigitalWallet | BODY: " + request.toString());
        PaymentCard card = PaymentCard.getOrThrow404(manager, cardDigit);

        Jwt jwt = (Jwt) auth.getPrincipal();

        if(!card.belongsTo(jwt.getSubject())){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Você não tem permissão pra adicionar uma carteira digital a esse cartão.");
        }

        if(card.hasWalletOfType(request.getType(), manager)){
            return ResponseEntity.unprocessableEntity().body("Já existe uma carteira vinculada desse tipo!");
        }

        DigitalWallet wallet = request.toModel(card);

        try {
            client.addDigitalWallet(card.getNumber(),new CarteiraDigital(wallet));
        }catch (FeignException e){
            logger.error(e.status()+" | "+e.contentUTF8()+" | "+ e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Não foi possível adicionar carteira. Houve um problema na comunicação com o sistema legado!");
        }

        manager.persist(wallet);

        return ResponseEntity.created(wallet.buildResourceUrl(builder)).build();
    }
}
