package br.com.zupacademy.enricco.proposta.utils.clients.request;

import br.com.zupacademy.enricco.proposta.models.Block;

public class ResponsableSystem {
    private String sistemaResponsavel;

    public ResponsableSystem(Block block) {
        this.sistemaResponsavel = block.getBlockedBy();
    }

    public String getSistemaResponsavel() {
        return sistemaResponsavel;
    }
}
