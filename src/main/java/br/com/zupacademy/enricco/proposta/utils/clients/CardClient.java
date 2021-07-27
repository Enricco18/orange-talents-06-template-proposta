package br.com.zupacademy.enricco.proposta.utils.clients;

import br.com.zupacademy.enricco.proposta.utils.clients.response.Card;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@FeignClient(name = "card", url = "${card.url}")
public interface CardClient {
    @RequestMapping(method = RequestMethod.GET)
    public Card getClient(@RequestParam(value="idProposta")String idProposta);

    @GetMapping("/{id}")
    public Card getClientByCardDigit(@PathVariable("id") String cardNumber);
}
