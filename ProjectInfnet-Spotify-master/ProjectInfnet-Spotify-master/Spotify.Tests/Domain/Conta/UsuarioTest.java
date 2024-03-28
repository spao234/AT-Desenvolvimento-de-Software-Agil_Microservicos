package Spotify.Tests.Domain.Conta;

import Spotify.Domain.Conta.Agreggates.Cartao;
import Spotify.Domain.Conta.Agreggates.Plano;
import Spotify.Domain.Conta.Agreggates.Usuario;
import Spotify.Domain.Conta.Exception.CPFException;
import Spotify.Domain.Conta.Exception.CartaoException;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

public class UsuarioTest {

    @Test
    public void naoCriaUsuarioComCpfInvalido() {
        Plano plano = new Plano(UUID.randomUUID(), "Aqui você encontra todo tipo de música.", "Stars", new BigDecimal("19.90"));
        Cartao cartao = new Cartao(UUID.randomUUID(), false, new BigDecimal("1500"), "1234567890");
        String cpf = "0123456789";
        String nome = "Léia";
        Usuario usuario = new Usuario();
        assertThrows(CPFException.class, () -> usuario.gerarUsuario(nome, cpf, plano, cartao));
    }

    @Test
    public void naoCriaUsuarioComCartaoInvalido() {
        Plano plano = new Plano(UUID.randomUUID(), "Lorem ipsum", "star", new BigDecimal("19.90"));
        Cartao cartao = new Cartao(UUID.randomUUID(), false, new BigDecimal("1000"), "1234567890");
        String cpf = "774.646.510-22";
        String nome = "Léia";
        Usuario usuario = new Usuario();
        assertThrows(CartaoException.class, () -> usuario.gerarUsuario(nome, cpf, plano, cartao));
    }
}
