import java.time.LocalDateTime;
import java.util.UUID;

public class Assinatura {
    private UUID id;
    private Plano plano;
    private boolean ativo;
    private LocalDateTime dataAssinatura;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public LocalDateTime getDataAssinatura() {
        return dataAssinatura;
    }

    public void setDataAssinatura(LocalDateTime dataAssinatura) {
        this.dataAssinatura = dataAssinatura;
    }
}
