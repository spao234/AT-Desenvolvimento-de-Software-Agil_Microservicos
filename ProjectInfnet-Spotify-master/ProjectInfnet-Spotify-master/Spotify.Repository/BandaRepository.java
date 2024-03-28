import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.UUID;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BandaRepository {
    private HttpClient httpClient;

    public BandaRepository() {
        this.httpClient = HttpClient.newHttpClient();
    }

    public Musica obterMusica(UUID id) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://localhost:7218/api/Banda/musica/" + id))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            return null;
        }

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(response.body(), Musica.class);
    }
}
