import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UsuarioRepository {
    private static List<Usuario> usuarios = new ArrayList<>();

    public void atualizar(Usuario usuario) {
        Usuario usuarioAntigo = this.obtemUsuario(usuario.getId());
        usuarios.remove(usuarioAntigo);
        usuarios.add(usuario);
    }

    public void incluirUsuario(Usuario usuario) {
        usuario.setId(UUID.randomUUID());
        usuarios.add(usuario);
    }

    public Usuario obtemUsuario(UUID id) {
        return usuarios.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
