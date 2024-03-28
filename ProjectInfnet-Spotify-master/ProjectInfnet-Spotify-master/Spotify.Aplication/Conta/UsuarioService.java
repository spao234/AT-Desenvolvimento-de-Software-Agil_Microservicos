import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final PlanoRepository planoRepository;
    private final UsuarioRepository usuarioRepository;
    private final BandaRepository bandaRepository;

    @Autowired
    public UsuarioService(PlanoRepository planoRepository, UsuarioRepository usuarioRepository, BandaRepository bandaRepository) {
        this.planoRepository = planoRepository;
        this.usuarioRepository = usuarioRepository;
        this.bandaRepository = bandaRepository;
    }

    public CriarContaDTO criarConta(CriarContaDTO conta) {
        Plano plano = planoRepository.obterPlano(conta.getPlanoId());
        if (plano == null) {
            throw new BussinesException(new BusinessValidation("Plano n�o encontrado", "criarConta"));
        }

        Cartao cartao = new Cartao();
        cartao.setAtivo(conta.getCartao().isAtivo());
        cartao.setNumero(conta.getCartao().getNumero());
        cartao.setLimite(conta.getCartao().getLimite());

        Usuario usuario = new Usuario();
        usuario.gerarUsuario(conta.getNome(), conta.getCPF(), plano, cartao);

        usuarioRepository.incluirUsuario(usuario);
        conta.setId(usuario.getId());

        return conta;
    }

    public void favoritarMusica(UUID id, UUID idMusica) {
        Usuario usuario = usuarioRepository.obtemUsuario(id);
        if (usuario == null) {
            throw new BussinesException(new BusinessValidation("Usuário não encontrado", "favoritarMusica"));
        }

        Musica musica = bandaRepository.obterMusica(idMusica);
        if (musica == null) {
            throw new BussinesException(new BusinessValidation("Música não encontrada", "favoritarMusica"));
        }

        usuario.favoritar(musica);
        usuarioRepository.atualizar(usuario);
    }

    public CriarContaDTO obtemUsuario(UUID id) {
        Usuario usuario = usuarioRepository.obtemUsuario(id);
        if (usuario == null) {
            return null;
        }

        CriarContaDTO result = new CriarContaDTO();
        result.setId(usuario.getId());
        result.setNome(usuario.getNome());
        result.setCPF(usuario.getCPF().getNumeroFormatado());
        result.setCartao(new CartaoDTO(usuario.getCartoes().get(0).isAtivo(), "xxxxxxxx", usuario.getCartoes().get(0).getLimite()));

        List<PlaylistDTO> playlistDTOs = usuario.getPlaylists().stream().map(playlist -> {
            PlaylistDTO playlistDTO = new PlaylistDTO();
            playlistDTO.setId(playlist.getId());
            playlistDTO.setNome(playlist.getNome());
            playlistDTO.setPublica(playlist.isPublica());

            List<MusicaDTO> musicasDTO = playlist.getMusicas().stream().map(musica -> {
                MusicaDTO musicaDTO = new MusicaDTO();
                musicaDTO.setId(musica.getId());
                musicaDTO.setNome(musica.getNome());
                musicaDTO.setDuracao(musica.getDuracao());
                return musicaDTO;
            }).collect(Collectors.toList());

            playlistDTO.setMusicas(musicasDTO);
            return playlistDTO;
        }).collect(Collectors.toList());

        result.setPlaylistDtos(playlistDTOs);
        return result;
    }
}
