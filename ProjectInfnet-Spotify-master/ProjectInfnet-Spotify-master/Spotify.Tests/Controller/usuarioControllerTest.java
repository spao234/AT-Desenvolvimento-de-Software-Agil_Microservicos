package Spotify.Tests.Controller;

import Spotify.API.Controllers.UsuarioController;
import Spotify.Aplication.Conta.Dto.CriarContaDTO;
import Spotify.Aplication.Conta.Dto.CartaoDTO;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class UsuarioControllerTest {

    @Test
    public void chamaMetodoPostCriarUsuario() {
        CriarContaDTO criarContaDTO = new CriarContaDTO();
        criarContaDTO.setNome("Leia");
        criarContaDTO.setCPF("31194592082");
        criarContaDTO.setCartao(new CartaoDTO(true, 1000, "5349 8913 9384 1193"));
        criarContaDTO.setPlanoId(UUID.fromString("b2a55062-bc07-45dc-b4b2-6754877c1c31"));

        Logger logger = LoggerFactory.getLogger(UsuarioController.class);
        UsuarioController controller = new UsuarioController(logger);
        ResponseEntity<?> response = controller.criaConta(criarContaDTO);

        Object responseContent = response.getBody();
        assertNotNull(responseContent);
        assertTrue(responseContent instanceof CriarContaDTO);
        assertNotNull(((CriarContaDTO) responseContent).getId());
    }
}
