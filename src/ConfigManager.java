import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager {
    private static final String LOCAL_CONFIG = "src/.Config"; // Caminho do arquivo de configuração

    // para obter a chave da API
    public static String getApiKey() {
        Properties propriedades = new Properties();

        try (FileInputStream input = new FileInputStream(LOCAL_CONFIG)) {
            propriedades.load(input);
            return propriedades.getProperty("API_KEY");
        } catch (IOException e) {
            System.out.println("Erro ao carregar o arquivo de configuração: " + e.getMessage());
            return null; // Retorna null se houver erro
        }
    }
}
