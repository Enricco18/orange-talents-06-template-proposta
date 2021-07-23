package br.com.zupacademy.enricco.proposta.utils.clients.response;

import java.time.LocalDateTime;
import java.util.UUID;

public class Card {
    private String id;
    private LocalDateTime emitidoEm;
    private String titular;
    private UUID idProposta;
//    private List<Bloqueio> bloqueios;
//    private List<AvisoViagem> avisos;
//    private List<CarteiraDigital> carteiras;
//    private List<Parcela> parcelas;
//    private Renegociacao renegociacao;
//    private Vencimento vencimento;
//    private Integer limite;


    @Override
    public String toString() {
        return "Card{" +
                "id='" + id + '\'' +
                ", emitidoEm=" + emitidoEm +
                ", titular='" + titular + '\'' +
                ", idProposta=" + idProposta +
                '}';
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getEmitidoEm() {
        return emitidoEm;
    }

    public String getTitular() {
        return titular;
    }

    public UUID getIdProposta() {
        return idProposta;
    }
}
