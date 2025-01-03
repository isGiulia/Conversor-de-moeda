import java.util.HashMap;
import java.util.Map;

public class ListadeMoedas {
    private int moedaSelecionada;
    private static final Map<Integer, String> moedas = new HashMap<>();

    // Inicializa o mapa de moedas
    static {
        moedas.put(1, "USD");
        moedas.put(2, "EUR");
        moedas.put(3, "GBP");
        moedas.put(4, "BRL");
        moedas.put(5, "JPY");
        moedas.put(6, "AUD");
        moedas.put(7, "SAIR");
    }

    public void setMoedaSelecionada(int moeda) {
        this.moedaSelecionada = moeda;
    }

    public String buscarMoeda() {
        return moedas.getOrDefault(this.moedaSelecionada, "erro");
    }
}
