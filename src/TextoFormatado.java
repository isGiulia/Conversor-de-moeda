public class TextoFormatado {
    // Declaração de variáveis
    public static final int primeiroNumero = 1;
    public static final int segundoNumero = 2;
    public static final int terceiroNumero = 3;
    public static final int quartoNumero = 4;
    public static final int quintoNumero = 5;
    public static final int sextoNumero = 6;

    // Função para retornar o texto formatado
    public static String obterTextoMoedas() {
        // Criação do texto usando variáveis
        return CoresTerminal.AMARELO + primeiroNumero + CoresTerminal.RESET + " - " +
                CoresTerminal.NEGRITO + "USD" + CoresTerminal.RESET + " (Dólar Americano)\n" +
                CoresTerminal.AMARELO + segundoNumero + CoresTerminal.RESET + " - " +
                CoresTerminal.NEGRITO + "EUR" + CoresTerminal.RESET + " (Euro)\n" +
                CoresTerminal.AMARELO + terceiroNumero + CoresTerminal.RESET + " - " +
                CoresTerminal.NEGRITO + "GBP" + CoresTerminal.RESET + " (Libra Esterlina)\n" +
                CoresTerminal.AMARELO + quartoNumero + CoresTerminal.RESET + " - " +
                CoresTerminal.NEGRITO + "BRL" + CoresTerminal.RESET + " (Real Brasileiro)\n" +
                CoresTerminal.AMARELO + quintoNumero + CoresTerminal.RESET + " - " +
                CoresTerminal.NEGRITO + "JPY" + CoresTerminal.RESET + " (Iene Japonês)\n" +
                CoresTerminal.AMARELO + sextoNumero + CoresTerminal.RESET + " - " +
                CoresTerminal.NEGRITO + "AUD" + CoresTerminal.RESET + " (Dólar Australiano)";
    }
    public static String ObterTexto(int texto){
        switch (texto) {
            case 1:
                return "*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n"
                        + CoresTerminal.NEGRITO + CoresTerminal.AZUL + "Olá, para fazer uma conversão aperte o numero da primeira "
                        + CoresTerminal.AMARELO + "moeda"
                        + CoresTerminal.AZUL + ":\n"
                        + CoresTerminal.RESET;
            case 2:
                return CoresTerminal.NEGRITO + "Agora aperte o número da " +
                        CoresTerminal.AMARELO + "moeda" +
                        CoresTerminal.NEGRITO + " de destino:\n" +
                        CoresTerminal.RESET;
            case 3:
                return CoresTerminal.NEGRITO + "\nQual valor deseja converter? " +  CoresTerminal.RESET;
            default:
                return CoresTerminal.NEGRITO + "Opção inválida. Tente novamente." + CoresTerminal.RESET;
        }

    }
    public static String Resposta(String valor, String moeda1, String moeda2, double valorConvertido) {
        return
                "😊" + CoresTerminal.FUNDOAZUL +CoresTerminal.NEGRITO + valor +
                CoresTerminal.RESET + " " + moeda1 +
                " está valendo " +
                CoresTerminal.VERDE + valorConvertido +
                " " + moeda2 +
                CoresTerminal.RESET;
    }



}
