package br.com.zupacademy.enricco.proposta.utils.clients;

import br.com.zupacademy.enricco.proposta.utils.clients.request.ProposalToBeClassified;
import br.com.zupacademy.enricco.proposta.utils.clients.response.ClassifiedProposal;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "transaction", url = "localhost:9999/api")
public interface TransactionClient {
    @RequestMapping(method = RequestMethod.POST, value = "/solicitacao")
    public ClassifiedProposal classifyProposal(ProposalToBeClassified proposal);
}
