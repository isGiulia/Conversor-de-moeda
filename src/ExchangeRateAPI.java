import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ExchangeRateAPI {
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/";

    // para buscar cotação usando a chave da API
    public String getExchangeRates(String moedaInicio, String moedaDestino, String apiKey) {
        try {
            // Construir a URL da API
            String url = BASE_URL + apiKey + "/pair/" + moedaInicio + "/"+ moedaDestino + "/";

            // Criar cliente HTTP
            HttpClient client = HttpClient.newHttpClient();

            // Construir requisição
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();

            // Enviar requisição e obter resposta
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Retornar o corpo da resposta
            return response.body();
        } catch (Exception e) {
            // Retornar mensagem de erro em caso de exceção
            return "Erro ao acessar a API: " + e.getMessage();
        }
    }
}
