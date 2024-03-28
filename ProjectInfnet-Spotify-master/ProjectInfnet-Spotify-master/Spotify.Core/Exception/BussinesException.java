import java.util.ArrayList;
import java.util.List;

public class BusinessException extends RuntimeException {
    private List<BusinessValidation> erros = new ArrayList<>();

    public BusinessException() { }

    public BusinessException(BusinessValidation validation) {
        this.enviaExcecao(validation);
    }

    public void enviaExcecao(BusinessValidation validation) {
        this.erros.add(validation);
    }

    public void testeValidacao() {
        if (!erros.isEmpty()) {
            throw this;
        }
    }

    public List<BusinessValidation> getErros() {
        return erros;
    }

    public void setErros(List<BusinessValidation> erros) {
        this.erros = erros;
    }
}

public class BusinessValidation {
    private String nomeErroDefaul = "Erro de validação!";
    private String mensagemErro;

    public String getNomeErroDefaul() {
        return nomeErroDefaul;
    }

    public void setNomeErroDefaul(String nomeErroDefaul) {
        this.nomeErroDefaul = nomeErroDefaul;
    }

    public String getMensagemErro() {
        return mensagemErro;
    }

    public void setMensagemErro(String mensagemErro) {
        this.mensagemErro = mensagemErro;
    }
}
