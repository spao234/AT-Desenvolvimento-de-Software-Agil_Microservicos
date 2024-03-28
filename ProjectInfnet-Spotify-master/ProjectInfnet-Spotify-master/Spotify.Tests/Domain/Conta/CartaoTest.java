package Spotify.Tests.Domain.Conta;

import Spotify.Domain.Conta.Agreggates.Cartao;
import Spotify.Domain.Conta.Exception.CartaoException;
import Spotify.Domain.Transacao.Agreggates.Transacao;
import Spotify.Domain.Transacao.ValueObject.Merchant;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

public class CartaoTest {

    @Test
    public void criaTransacaoComSucesso() {
        Cartao cartao = new Cartao(UUID.randomUUID(), true, new BigDecimal("1500"), "1234567890");
        cartao.criarTransacao("star", new BigDecimal("10"), "Pagamento plano");
        assertTrue(cartao.getTransacoes().size() > 0);
    }

    @Test
    public void naoCriaTransacaoComCartaoInativo() {
        Cartao cartao = new Cartao(UUID.randomUUID(), false, new BigDecimal("1500"), "1234567890");
        assertThrows(CartaoException.class, () -> cartao.criarTransacao("star", new BigDecimal("10"), "Pagamento plano"));
    }

    @Test
    public void naoCriaTransacaoSemLimiteNoCartao() {
        Cartao cartao = new Cartao(UUID.randomUUID(), true, new BigDecimal("20"), "1234567890");
        assertThrows(CartaoException.class, () -> cartao.criarTransacao("star", new BigDecimal("23"), "Pagamento plano"));
    }

    @Test
    public void naoCriaTransacaoDeValorDuplicado() {
        Cartao cartao = new Cartao(UUID.randomUUID(), true, new BigDecimal("2000"), "6465465466");
        cartao.getTransacoes().add(
            new Transacao(UUID.randomUUID(), LocalDateTime.now(), new Merchant("star"), new BigDecimal("19"), "Há uma nova compra!")
        );

        assertThrows(CartaoException.class, () -> cartao.criarTransacao("star", new BigDecimal("19"), "Transacao!"));
    }

    @Test
    public void naoDeveCriarTransacaoComMuitaFrequencia() {
        Cartao cartao = new Cartao(UUID.randomUUID(), true, new BigDecimal("2000"), "1234567890");
        LocalDateTime now = LocalDateTime.now();

        cartao.getTransacoes().add(
            new Transacao(UUID.randomUUID(), now.minusMinutes(1), new Merchant("star"), new BigDecimal("19"), "Há uma nova compra!")
        );

        cartao.getTransacoes().add(
            new Transacao(UUID.randomUUID(), now.minusMinutes(0.5), new Merchant("star"), new BigDecimal("19"), "Há uma nova compra!")
        );

        cartao.getTransacoes().add(
            new Transacao(UUID.randomUUID(), now, new Merchant("star"), new BigDecimal("19"), "Há uma nova compra!")
        );

        assertThrows(CartaoException.class, () -> cartao.criarTransacao("Dummy", new BigDecimal("19"), "Dummy Transacao"));
    }
}
