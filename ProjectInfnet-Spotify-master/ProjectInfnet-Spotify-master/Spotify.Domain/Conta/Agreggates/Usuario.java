import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Usuario {
    private UUID id;
    private String nome;
    private CPF cpf;
    private List<Cartao> cartoes;
    private List<Playlist> playlists;
    private List<Assinatura> assinaturas;

    public Usuario() {
        this.playlists = new ArrayList<>();
        this.assinaturas = new ArrayList<>();
        this.cartoes = new ArrayList<>();
    }

    public void gerarUsuario(String nome, String cpf, Plano plano, Cartao cartao) {
        this.cpf = new CPF(cpf);
        this.nome = nome;

        assinarPlano(plano, cartao);
        adicionarCartao(cartao);
        criarPlaylist();
    }

    public void criarPlaylist(String nome) {
        this.playlists.add(new Playlist(UUID.randomUUID(), nome, false, this));
    }

    public void favoritar(Musica musica) {
        Playlist favoritas = this.playlists.stream().filter(p -> p.getNome().equals("Favoritas")).findFirst().orElse(null);
        if (favoritas != null) {
            favoritas.adicionarMusica(musica);
        }
    }

    private void adicionarCartao(Cartao cartao) {
        this.cartoes.add(cartao);
    }

    public void assinarPlano(Plano plano, Cartao cartao) {
        cartao.criarTransacao(plano.getNome(), plano.getValor(), plano.getDescricao());

        Assinatura planoAtivo = this.assinaturas.stream().filter(Assinatura::isAtivo).findFirst().orElse(null);
        if (planoAtivo != null) {
            planoAtivo.setAtivo(false);
        }
        
        Assinatura novaAssinatura = new Assinatura();
        novaAssinatura.setId(UUID.randomUUID());
        novaAssinatura.setPlano(plano);
        novaAssinatura.setAtivo(true);
        novaAssinatura.setDataAssinatura(LocalDateTime.now());
        this.assinaturas.add(novaAssinatura);
    }
}
