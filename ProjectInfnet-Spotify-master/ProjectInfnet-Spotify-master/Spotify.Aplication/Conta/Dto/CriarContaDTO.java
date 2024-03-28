import java.util.List;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CriarContaDTO {
    private UUID id;

    @NotBlank
    private String nome;

    @NotBlank
    private String CPF;

    @NotNull
    private UUID planoId;

    private CartaoDTO cartao;

    private List<PlaylistDto> playlistDtos;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String cpf) {
        this.CPF = cpf;
    }

    public UUID getPlanoId() {
        return planoId;
    }

    public void setPlanoId(UUID planoId) {
        this.planoId = planoId;
    }

    public CartaoDTO getCartao() {
        return cartao;
    }

    public void setCartao(CartaoDTO cartao) {
        this.cartao = cartao;
    }

    public List<PlaylistDto> getPlaylistDtos() {
        return playlistDtos;
    }

    public void setPlaylistDtos(List<PlaylistDto> playlistDtos) {
        this.playlistDtos = playlistDtos;
    }
}

public class CartaoDTO {
    @NotBlank
    private String numero;

    @NotNull
    private Double limite;

    @NotNull
    private Boolean ativo;

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Double getLimite() {
        return limite;
    }

    public void setLimite(Double limite) {
        this.limite = limite;
    }

    public Boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
}

public class PlaylistDto {
    private UUID id;
    private String nome;
    private Boolean publica;
    private List<MusicaDto> musicas;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Boolean isPublica() {
        return publica;
    }

    public void setPublica(Boolean publica) {
        this.publica = publica;
    }

    public List<MusicaDto> getMusicas() {
        return musicas;
    }

    public void setMusicas(List<MusicaDto> musicas) {
        this.musicas = musicas;
    }
}

public class MusicaDto {
    private UUID id;
    private String nome;
    private int duracao;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }
}
