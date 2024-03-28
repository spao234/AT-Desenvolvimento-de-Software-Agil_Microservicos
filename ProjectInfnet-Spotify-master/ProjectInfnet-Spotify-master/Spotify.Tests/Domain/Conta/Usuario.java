package SpotifyLike.Domain.Conta.Agreggates;

import SpotifyLike.Domain.Core.ValueObject.Monetario;
import SpotifyLike.Domain.Notificacao.Notificacao;
import SpotifyLike.Domain.Streaming.Aggregates.Playlist;
import SpotifyLike.Domain.Transacao.Agreggates.Assinatura;
import SpotifyLike.Domain.Transacao.Agreggates.Cartao;
import SpotifyLike.Domain.Transacao.ValueObject.Merchant;
import SpotifyLike.Domain.Transacao.ValueObject.Plano;
import SpotifyLike.Domain.Core.Extension.StringExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Usuario {

    private static final String NOME_PLAYLIST = "Favoritas";

    private UUID id;
    private String nome;
    private String email;
    private String senha;
    private Date dtNascimento;
    private List<Cartao> cartoes = new ArrayList<>();
    private List<Assinatura> assinaturas = new ArrayList<>();
    private List<Playlist> playlists = new ArrayList<>();
    private List<Notificacao> notificacoes = new ArrayList<>();

    public void criarConta(String nome, String email, String senha, Date dtNascimento, Plano plano, Cartao cartao) {
        this.nome = nome;
        this.email = email;
        this.dtNascimento = dtNascimento;

        // Criptografar a senha
        this.senha = criptografarSenha(senha);

        // Assinar um plano
        assinarPlano(plano, cartao);

        // Adicionar cartão na conta do usuário
        adicionarCartao(cartao);

        // Criar a playlist padrão do usuário
        criarPlaylist(NOME_PLAYLIST, false);
    }

    public void criarPlaylist(String nome, boolean publica) {
        playlists.add(new Playlist(nome, publica, new Date(), this));
    }

    private void adicionarCartao(Cartao cartao) {
        cartoes.add(cartao);
    }

    private void assinarPlano(Plano plano, Cartao cartao) {
        // Debitar o valor do plano no cartão
        cartao.criarTransacao(new Merchant(plano.getNome()), new Monetario(plano.getValor()), plano.getDescricao());

        // Desativar caso tenha alguma assinatura ativa
        desativarAssinaturaAtiva();

        // Adiciona uma nova assinatura
        assinaturas.add(new Assinatura(true, plano, new Date()));
    }

    private void desativarAssinaturaAtiva() {
        // Caso tenha alguma assinatura ativa, desativa ela
        for (Assinatura assinatura : assinaturas) {
            if (assinatura.isAtivo()) {
                assinatura.setAtivo(false);
                break;
            }
        }
    }

    private String criptografarSenha(String senhaAberta) {
        return senhaAberta.hashSHA256();
    }
}
