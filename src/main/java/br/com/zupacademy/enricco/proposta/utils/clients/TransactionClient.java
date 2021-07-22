package br.com.zupacademy.enricco.proposta.utils.clients;

import br.com.zupacademy.enricco.proposta.utils.clients.request.ProposalToBeClassified;
import br.com.zupacademy.enricco.proposta.utils.clients.response.ClassifiedProposal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.net.URI;

@FeignClient(name = "transaction" ,url = "transactionUrl")
public interface TransactionClient {

    @RequestMapping(method = RequestMethod.POST)
    public ClassifiedProposal classifyProposal(URI baseURI, ProposalToBeClassified proposal);
}
