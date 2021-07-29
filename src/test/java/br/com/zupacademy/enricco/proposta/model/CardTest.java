package br.com.zupacademy.enricco.proposta.model;

import br.com.zupacademy.enricco.proposta.models.Block;
import br.com.zupacademy.enricco.proposta.models.ClientProposal;
import br.com.zupacademy.enricco.proposta.models.PaymentCard;
import br.com.zupacademy.enricco.proposta.models.Renegotiation;
import br.com.zupacademy.enricco.proposta.utils.clients.response.*;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class CardTest {
    public static ClientProposal proposal;
    public static PaymentCard paymentCard;

    public static String owner_id="1";
    public static String notOwner_id="2";

    @BeforeAll
    public static void setup(){
        proposal = new ClientProposal(   "360.109.987-09",
                                                        "anonimo@lindinho.com",
                                                        "Naruto",
                                                        "Vila da folha, Rua da luta, 64",
                                                        BigDecimal.valueOf(5000.00),
                                                        owner_id);
        paymentCard = new PaymentCard(  "1",
                                                    LocalDateTime.now(),
                                                    "Naruto",
                                                    proposal,
                                                    null,
                                                    null,
                                                    1000,
                                                    new ArrayList<Bloqueio>(),
                                                    new ArrayList<AvisoViagem>(),
                                                    new ArrayList<CarteiraDigital>(),
                                                    new ArrayList<Parcela>());



    }
    @Test
    @DisplayName("Cartão com bloqueio ativo não pode ser bloqueado")
    public void testCardAlreadyBlockedCannotBeBlocked(){
        paymentCard.addBlock("127.0.0.1","Chrome");
        Assert.assertTrue(paymentCard.isBlocked()==true);

        Assert.assertThrows(ResponseStatusException.class,()->{paymentCard.addBlock("127.0.0.1","Mozilla");});

        paymentCard.getBlocks().stream().forEach(block -> {
            block.setActive(false);
        });

        paymentCard.addBlock("127.0.0.1","IE");
        Assert.assertTrue(paymentCard.isBlocked()==true);

        Assert.assertTrue(paymentCard.getBlocks().size()==2);

        Block block1 = new Block("Chrome","127.0.0.1",paymentCard);
        Block block2 = new Block("IE","127.0.0.1",paymentCard);
        Block[] blocks = {block1,block2};
        for (int i=0; i<paymentCard.getBlocks().size();i++){
            Block block = paymentCard.getBlocks().get(i);

            Assert.assertTrue(block.getBlockedBy().equals(blocks[i].getBlockedBy()));
        }
    }

    @Test
    @DisplayName("Testando validação de usuário dono")
    public void testBelongsTo() {
        Assert.assertTrue(!paymentCard.belongsTo(notOwner_id));
        Assert.assertTrue(paymentCard.belongsTo(owner_id));
    }

}
