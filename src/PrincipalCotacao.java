import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Scanner;
import java.util.Locale;

public class PrincipalCotacao {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.US); // Força o ponto como separador decimal

        // Capturar o valor a ser convertido com validação
        double valor = capturarValor(scanner, TextoFormatado.ObterTexto(3));

        // Configurando a formatação com vírgula como separador decimal
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
        symbols.setDecimalSeparator(',');
        DecimalFormat formatador = new DecimalFormat("#,##0.00", symbols);
        String valorFormatado = formatador.format(valor);

        // Criar OBJ da lista de moedas
        ListadeMoedas listaDeMoedas = new ListadeMoedas();

        // Capturar moeda de origem com validação
        Object[] moedaOrigemInfo = capturarMoedaValida(scanner, listaDeMoedas, TextoFormatado.ObterTexto(1), TextoFormatado.obterTextoMoedas());
        int moeda1Numero = (int) moedaOrigemInfo[0];
        String moeda1 = (String) moedaOrigemInfo[1];

        // Capturar moeda de destino com validação
        Object[] moedaDestinoInfo = capturarMoedaValida(scanner, listaDeMoedas, TextoFormatado.ObterTexto(2), null);
        int moeda2Numero = (int) moedaDestinoInfo[0];
        String moeda2 = (String) moedaDestinoInfo[1];

        // Obter a chave da API
        String apiKey = ConfigManager.getApiKey();
        if (apiKey == null) {
            System.out.println("Erro: Chave da API não encontrada.");
            return;
        }

        // Criar instância da classe da API
        ExchangeRateAPI api = new ExchangeRateAPI();

        // Buscar cotação diretamente com a classe ExchangeRateAPI
        String resposta = api.getExchangeRates(moeda1, moeda2, apiKey);

        // Parsear a resposta JSON
        Gson gson = new Gson();
        JsonObject jsonResposta = gson.fromJson(resposta, JsonObject.class);

        // Extrair a taxa
        if (jsonResposta.has("conversion_rate")) {
            double taxa = jsonResposta.get("conversion_rate").getAsDouble();

            // Calcular o valor convertido
            double valorConvertido = valor * taxa;

            // Responder ao usuário
            System.out.println("Taxa de câmbio: " + CoresTerminal.VERDE + taxa + CoresTerminal.RESET);
            String textoFinalFormatado = TextoFormatado.Resposta(valorFormatado, moeda1, moeda2, valorConvertido);
            System.out.println(textoFinalFormatado);
        } else {
            System.out.println("Erro: Taxa de câmbio não encontrada na resposta.");
        }
    }

    private static Object[] capturarMoedaValida(Scanner scanner, ListadeMoedas listaDeMoedas, String mensagem, String opcoesMoedas) {
        int numeroMoeda = -1; // Valor ainda não foi definido ou não é válido
        String moedaSelecionada;

        do {
            try {
                System.out.println(mensagem);
                if (opcoesMoedas != null) {
                    System.out.println(opcoesMoedas);
                }

                numeroMoeda = scanner.nextInt(); // Captura o número digitado pelo usuário
                listaDeMoedas.setMoedaSelecionada(numeroMoeda); // Envia o número para a lista de moedas
                moedaSelecionada = listaDeMoedas.buscarMoeda(); // Obtém a moeda correspondente

                if (moedaSelecionada.equals("erro")) {
                    System.out.println("Número inválido. Por favor, tente novamente.");
                } else if (moedaSelecionada.equals("SAIR")) {
                    System.out.println("Saindo...");
                    System.exit(0);
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("Entrada inválida. Digite apenas números.");
                scanner.nextLine(); // Limpa a entrada inválida
                moedaSelecionada = "erro"; // Define como erro para repetir o loop
            }
        } while (moedaSelecionada.equals("erro")); // Continua até obter uma moeda válida

        return new Object[]{numeroMoeda, moedaSelecionada}; // Retorna número e nome da moeda
    }

    private static double capturarValor(Scanner scanner, String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String entrada = scanner.nextLine();

            // Substituir múltiplos pontos por um único
            entrada = entrada.replaceAll("\\.+", ".");
            try {
                // Converte o valor corrigido para double
                return Double.parseDouble(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Erro: Por favor, insira um número válido no formato correto (exemplo: 100.00).");
            }
        }
    }
}
