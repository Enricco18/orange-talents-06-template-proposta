package br.com.zupacademy.enricco.proposta.utils.clients;

import br.com.zupacademy.enricco.proposta.utils.clients.request.ResponsableSystem;
import br.com.zupacademy.enricco.proposta.utils.clients.request.AvisoViagem;
import br.com.zupacademy.enricco.proposta.utils.clients.response.Card;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@FeignClient(name = "card", url = "${card.url}")
public interface CardClient {
    @RequestMapping(method = RequestMethod.GET)
    public Card getClient(@RequestParam(value="idProposta")String idProposta);

    @GetMapping("/{id}")
    public Card getClientByCardDigit(@PathVariable("id") String cardNumber);

    @PostMapping("/{id}/bloqueios")
    public String blockCard(@PathVariable("id") String cardNumber, @RequestBody ResponsableSystem responsableSystem);

    @PostMapping("/{id}/bloqueios")
    public String travelNotice(@PathVariable("id") String cardNumber, @RequestBody @Valid AvisoViagem avisoViagem);
}
