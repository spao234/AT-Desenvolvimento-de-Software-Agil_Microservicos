package SpotifyLike.Domain.Conta.Agreggates;

import SpotifyLike.Domain.Transacao.ValueObject.Plano;

import java.util.Date;
import java.util.UUID;

public class Assinatura {
    private UUID id;
    private Plano plano;
    private boolean ativo;
    private Date dtAtivacao;

    public Assinatura(Plano plano, boolean ativo, Date dtAtivacao) {
        this.id = UUID.randomUUID();
        this.plano = plano;
        this.ativo = ativo;
        this.dtAtivacao = dtAtivacao;
    }

    public UUID getId() {
        return id;
    }

    public Plano getPlano() {
        return plano;
    }

    public void setPlano(Plano plano) {
        this.plano = plano;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public Date getDtAtivacao() {
        return dtAtivacao;
    }

    public void setDtAtivacao(Date dtAtivacao) {
        this.dtAtivacao = dtAtivacao;
    }
}
