import java.time.LocalDateTime;
import java.util.UUID;

public class Transacao {
    private UUID id;
    private LocalDateTime dtTransacao;
    private double valorTransacao;
    private Merchant merchant;
    private String descricao;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDateTime getDtTransacao() {
        return dtTransacao;
    }

    public void setDtTransacao(LocalDateTime dtTransacao) {
        this.dtTransacao = dtTransacao;
    }

    public double getValorTransacao() {
        return valorTransacao;
    }

    public void setValorTransacao(double valorTransacao) {
        this.valorTransacao = valorTransacao;
    }

    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
