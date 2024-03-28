package Spotify.Tests.Application;

import Spotify.Aplication.Conta.UsuarioService;
import Spotify.Aplication.Conta.Dto.CriarContaDTO;
import Spotify.Core.Exception.BussinesException;
import org.junit.jupiter.api.Test;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

public class UsuarioServiceTeste {

    @Test
    public void criaContaComSucesso() {
        CriarContaDTO criarContaDTO = new CriarContaDTO();
        criarContaDTO.setNome("Paulo");
        criarContaDTO.setCPF("67375193002");
        criarContaDTO.setCartao(new CartaoDTO(true, 1000, "5899 8913 8964 1193"));
        criarContaDTO.setPlanoId(UUID.fromString("b2a65062-bc07-45dc-b4b2-6789965c1c31"));

        UsuarioService service = new UsuarioService();
        service.CriarConta(criarContaDTO);

        assertNotNull(criarContaDTO.getId());
    }

    @Test
    public void naoCriaContaComPlanoInvalido() {
        CriarContaDTO criarContaDTO = new CriarContaDTO();
        criarContaDTO.setNome("Paulo");
        criarContaDTO.setCPF("67375193002");
        criarContaDTO.setCartao(new CartaoDTO(true, 1000, "5899 8913 8964 1193"));
        criarContaDTO.setPlanoId(UUID.randomUUID());

        UsuarioService service = new UsuarioService();

        assertThrows(BussinesException.class, () -> service.CriarConta(criarContaDTO));
    }
}

class CartaoDTO {
    private boolean ativo;
    private double limite;
    private String numero;

    public CartaoDTO(boolean ativo, double limite, String numero) {
        this.ativo = ativo;
        this.limite = limite;
        this.numero = numero;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}
