package Spotify.Application.Streaming;

import Spotify.Application.Streaming.Dto.AlbumDto;
import Spotify.Application.Streaming.Dto.BandaDto;
import Spotify.Domain.Streaming.Aggregates.Album;
import Spotify.Domain.Streaming.Aggregates.Banda;
import Spotify.Repository.Repository.BandaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BandaService {

    private BandaRepository bandaRepository;
    private ModelMapper modelMapper;

    @Autowired
    public BandaService(BandaRepository bandaRepository, ModelMapper modelMapper) {
        this.bandaRepository = bandaRepository;
        this.modelMapper = modelMapper;
    }

    public BandaDto criar(BandaDto dto) {
        Banda banda = modelMapper.map(dto, Banda.class);
        bandaRepository.save(banda);
        return modelMapper.map(banda, BandaDto.class);
    }

    public BandaDto obter(UUID id) {
        Banda banda = bandaRepository.getById(id);
        return modelMapper.map(banda, BandaDto.class);
    }

    public List<BandaDto> obter() {
        List<Banda> bandas = bandaRepository.getAll();
        return bandas.stream()
                .map(banda -> modelMapper.map(banda, BandaDto.class))
                .collect(Collectors.toList());
    }

    public AlbumDto associarAlbum(AlbumDto dto) {
        Banda banda = bandaRepository.getById(dto.getBandaId());
        if (banda == null) {
            throw new RuntimeException("Banda não encontrada");
        }
        Album novoAlbum = albumDtoParaAlbum(dto);
        banda.adicionarAlbum(novoAlbum);
        bandaRepository.update(banda);
        return albumParaAlbumDto(novoAlbum);
    }

    public AlbumDto obterAlbumPorId(UUID idBanda, UUID id) {
        Banda banda = bandaRepository.getById(idBanda);
        if (banda == null) {
            throw new RuntimeException("Banda não encontrada");
        }
        Album album = banda.getAlbums().stream()
                .filter(a -> a.getId().equals(id))
                .findFirst()
                .orElse(null);
        if (album == null) {
            throw new RuntimeException("Álbum não encontrado");
        }
        AlbumDto result = albumParaAlbumDto(album);
        result.setBandaId(banda.getId());
        return result;
    }

    public List<AlbumDto> obterAlbum(UUID idBanda) {
        Banda banda = bandaRepository.getById(idBanda);
        if (banda == null) {
            throw new RuntimeException("Banda não encontrada");
        }
        List<AlbumDto> result = new ArrayList<>();
        for (Album album : banda.getAlbums()) {
            result.add(albumParaAlbumDto(album));
        }
        return result;
    }

    private Album albumDtoParaAlbum(AlbumDto dto) {
        Album album = new Album();
        album.setNome(dto.getNome());
        album.setMusicas(dto.getMusicas().stream()
                .map(musicDto -> new Musica(musicDto.getNome(), musicDto.getDuracao()))
                .collect(Collectors.toList()));
        return album;
    }

    private AlbumDto albumParaAlbumDto(Album album) {
        AlbumDto dto = new AlbumDto();
        dto.setId(album.getId());
        dto.setNome(album.getNome());
        dto.setMusicas(album.getMusicas().stream()
                .map(musica -> new MusicDto(musica.getNome(), musica.getDuracao()))
                .collect(Collectors.toList()));
        return dto;
    }
}
