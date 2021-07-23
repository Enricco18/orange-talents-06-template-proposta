package br.com.zupacademy.enricco.proposta.repositories;

import br.com.zupacademy.enricco.proposta.models.ClientProposal;
import br.com.zupacademy.enricco.proposta.models.enums.ClientProposalType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ClientProposalRepository extends CrudRepository<ClientProposal, UUID> {
    List<ClientProposal> findByStatusAndCard(ClientProposalType statues, UUID uuid);
}
