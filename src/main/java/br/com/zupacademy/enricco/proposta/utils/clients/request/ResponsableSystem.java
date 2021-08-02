package br.com.zupacademy.enricco.proposta.utils.clients.request;

import br.com.zupacademy.enricco.proposta.models.Block;

public class ResponsableSystem {
    private String sistemaResponsavel;

    public ResponsableSystem(String thisSystem) {
        this.sistemaResponsavel = thisSystem;
    }

    public String getSistemaResponsavel() {
        return sistemaResponsavel;
    }
}
