import io.javalin.Javalin;
import io.javalin.http.Context;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import java.util.ArrayList;
import java.util.List;

class ErrosHandling {
    private List<Erro> erros;

    public ErrosHandling() {
        this.erros = new ArrayList<>();
    }

    public List<Erro> getErros() {
        return erros;
    }

    public void setErros(List<Erro> erros) {
        this.erros = erros;
    }
}

class Erro {
    private String nomeErroDefaul;
    private String mensagemErro;

    public Erro(String nomeErroDefaul, String mensagemErro) {
        this.nomeErroDefaul = nomeErroDefaul;
        this.mensagemErro = mensagemErro;
    }

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

class ErroeMessagem {
    private String campo;
    private String mensagem;

    public ErroeMessagem(String campo, String mensagem) {
        this.campo = campo;
        this.mensagem = mensagem;
    }

    public String getCampo() {
        return campo;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}

class ErrorResponse {
    private String error;

    public ErrorResponse(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}

public class Main {
    public static void main(String[] args) {
        Javalin app = Javalin.create().start(7000);

        // Configure the HTTP request pipeline
        app.exception(Exception.class, (exception, context) -> {
            if (exception instanceof Spotify.Core.Exception.BussinesException) {
                Spotify.Core.Exception.BussinesException businessException = (Spotify.Core.Exception.BussinesException) exception;
                ErrosHandling errorResponse = new ErrosHandling();
                for (Erro item : businessException.getErros()) {
                    errorResponse.getErros().add(new Erro(item.getNomeErroDefaul(), item.getMensagemErro()));
                }
                context.status(422).json(errorResponse);
            } else {
                context.status(500).json(new ErrorResponse(exception.getMessage()));
            }
        });

        app.get("/", ctx -> ctx.result("Hello World"));
    }
}
