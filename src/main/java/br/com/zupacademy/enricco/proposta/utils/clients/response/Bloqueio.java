package br.com.zupacademy.enricco.proposta.utils.clients.response;

import java.time.LocalDateTime;

public class Bloqueio {
    private String id;
    private LocalDateTime bloqueadoEm;
    private String sistemaResponsavel;
    private Boolean ativo;

    public String getId() {
        return id;
    }

    public LocalDateTime getBloqueadoEm() {
        return bloqueadoEm;
    }

    public String getSistemaResponsavel() {
        return sistemaResponsavel;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    //    {
//        "id": "string",
//            "bloqueadoEm": "2021-07-26T10:29:28.838Z",
//            "sistemaResponsavel": "string",
//            "ativo": true
//    }
}
