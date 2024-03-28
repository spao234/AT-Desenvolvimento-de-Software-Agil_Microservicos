import java.util.ArrayList;
import java.util.List;

public class ErrosHandling {
    private List<ErroeMessagem> erros = new ArrayList<>();
    private String descricaoErro = "Erro ao processar requisição!";

    public List<ErroeMessagem> getErros() {
        return erros;
    }

    public void setErros(List<ErroeMessagem> erros) {
        this.erros = erros;
    }

    public String getDescricaoErro() {
        return descricaoErro;
    }

    public void setDescricaoErro(String descricaoErro) {
        this.descricaoErro = descricaoErro;
    }
}

public class ErroeMessagem {
    private String mensagem;
    private String campo;

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getCampo() {
        return campo;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }
}
