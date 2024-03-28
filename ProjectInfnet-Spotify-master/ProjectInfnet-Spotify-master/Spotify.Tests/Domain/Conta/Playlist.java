package SpotifyLike.Domain.Conta.Agreggates;

import SpotifyLike.Domain.Streaming.Aggregates.Musica;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Playlist {
    private UUID id;
    private String nome;
    private boolean publica;
    private Usuario usuario;
    private List<Musica> musicas = new ArrayList<>();
    private Date dtCriacao;

    public Playlist(String nome, boolean publica, Date dtCriacao, Usuario usuario) {
        this.id = UUID.randomUUID();
        this.nome = nome;
        this.publica = publica;
        this.dtCriacao = dtCriacao;
        this.usuario = usuario;
    }

    public UUID getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isPublica() {
        return publica;
    }

    public void setPublica(boolean publica) {
        this.publica = publica;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Musica> getMusicas() {
        return musicas;
    }

    public void setMusicas(List<Musica> musicas) {
        this.musicas = musicas;
    }

    public Date getDtCriacao() {
        return dtCriacao;
    }

    public void setDtCriacao(Date dtCriacao) {
        this.dtCriacao = dtCriacao;
    }
}
