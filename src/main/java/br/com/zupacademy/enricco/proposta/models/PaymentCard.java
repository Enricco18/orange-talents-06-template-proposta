package br.com.zupacademy.enricco.proposta.models;

import br.com.zupacademy.enricco.proposta.utils.clients.response.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class PaymentCard {
    @Id
    private String number;
    private LocalDateTime createdOn;
    private String holder;
    @OneToOne
    private ClientProposal proposal;
    @OneToMany(mappedBy = "card", cascade = CascadeType.MERGE)
    private List<Block> blocks = new ArrayList<>();
    @OneToMany(mappedBy = "card", cascade = CascadeType.MERGE)
    private List<TravelNotice> notices = new ArrayList<>();
    @OneToMany(mappedBy = "card", cascade = CascadeType.MERGE)
    private List<DigitalWallet> wallets = new ArrayList<>();
    @OneToMany(mappedBy = "card", cascade = CascadeType.MERGE)
    private List<Parcel> parcels = new ArrayList<>();
    @OneToOne(cascade = CascadeType.MERGE)
    private Renegotiation renegotiation;
    @OneToOne(cascade = CascadeType.MERGE)
    private DueDate dueDate;
    @OneToOne(mappedBy = "card")
    private Biometric biometric;
    private Integer cardLimit;

    @Deprecated
    private PaymentCard() {
    }

    public PaymentCard(String id,
                       LocalDateTime emitidoEm,
                       String titular,
                       ClientProposal idProposta,
                       Renegociacao renegociacao,
                       Vencimento vencimento,
                       Integer limite,
                       List<Bloqueio> bloqueios,
                       List<AvisoViagem> avisos,
                       List<CarteiraDigital> carteiras,
                       List<Parcela> parcelas) {
        this.number = id;
        this.createdOn = emitidoEm;
        this.holder = titular;
        this.proposal = idProposta;
        this.renegotiation = renegociacao==null? null:renegociacao.toModel(this);
        this.dueDate = vencimento== null? null:vencimento.toModel(this);
        this.cardLimit = limite;
        this.blocks.addAll(
                bloqueios.stream().map(bloqueio -> {
                    return new Block(   bloqueio.getId(),
                                        bloqueio.getBloqueadoEm(),
                                        bloqueio.getSistemaResponsavel(),
                                        bloqueio.getAtivo(),this);
                }).collect(Collectors.toList())
        );
        this.notices.addAll(
                avisos.stream().map(aviso -> {
                    return new TravelNotice(    aviso.getValidoAte(),
                                                aviso.getDestino(),
                                                this);
                }).collect(Collectors.toList())
        );
        this.wallets.addAll(
                carteiras.stream().map(carteira -> {
                    return new DigitalWallet(   carteira.getId(),
                                                carteira.getEmail(),
                                                carteira.getAssociadaEm(),
                                                carteira.getEmissor(),
                                                this);
                }).collect(Collectors.toList())
        );
        this.parcels.addAll(
                parcelas.stream().map(parcela -> {
                    return new Parcel(          parcela.getId(),
                                                parcela.getQuantidade(),
                                                parcela.getValor(),
                                                this);
                }).collect(Collectors.toList())
        );
    }

    public static PaymentCard getOrThrow404(EntityManager manager, String cardDigit) {
        PaymentCard card = manager.find(PaymentCard.class,cardDigit);

        if(card==null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        return card;
    }

    public String getNumber() {
        return number;
    }
}