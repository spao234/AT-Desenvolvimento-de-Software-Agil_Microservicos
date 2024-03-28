package Spotify.Application.Streaming.Dto;

import SpotifyLike.Domain.Streaming.ValueObject.MusicDto;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AlbumDto {
    private UUID id;
    private UUID bandaId;
    private String nome;
    private List<MusicDto> musicas = new ArrayList<>();

    // Getter e Setter para o ID
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    // Getter e Setter para o ID da Banda
    public UUID getBandaId() {
        return bandaId;
    }

    public void setBandaId(UUID bandaId) {
        this.bandaId = bandaId;
    }

    // Getter e Setter para o nome do álbum
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    // Getter e Setter para a lista de músicas
    public List<MusicDto> getMusicas() {
        return musicas;
    }

    public void setMusicas(List<MusicDto> musicas) {
        this.musicas = musicas;
    }
}

package Spotify.Application.Streaming.Dto;

import java.util.UUID;

public class MusicDto {
    private UUID id;
    private String nome;
    private int duracao;

    // Getter e Setter para o ID da música
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    // Getter e Setter para o nome da música
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    // Getter e Setter para a duração da música
    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }
}
