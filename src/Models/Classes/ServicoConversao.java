package Models.Classes; /**
 * A classe Models.Classes.ServicoConversao ficou responsável pela interação com o usuário e pela lógica de conversão de moedas
 * Interagindo com o usuário através do console
 * Foram utilizados nela o objeto Scanner, que serve para inserção de dados
 * A biblioteca GSON, que é uma biblioteca de Java desenvolvida pela Google que permite a conversão entre objetos java e JSON
 * A ExchangeRateAPI que retorna informações sobre as cotações das moedas mundiais de acordo com a nossa escolha
 */

import java.io.IOException;
import java.util.Scanner;

import Models.Exception.ErroDeConversaoException;
import com.google.gson.Gson;

public class ServicoConversao {
    private final Scanner scanner = new Scanner(System.in);
    private final Gson gson = new Gson();
    private final ExchangeRateApi apiClient = new ExchangeRateApi();

    /**
     * O metodo executarConversao() é responsável pela lógica principal de conversão de moedas
     * </br>
     * Ele apresenta o menu de opções ao usuário, pedindo para que o usuário escolha a opção desejada através da inserção de dados do teclado pelo Scanner,
     * aceitando a escolha da conversão e solicitando o valor a ser convertido, e exibindo o valor convertido.
     * Utiliza o try/catch para tratamentos de possíveis erros
     */

    public void executarConversao () {
        boolean executar = true;
        System.out.println("Bem vindo(a) ao conversor de moedas!");


        while (executar) {
            String menu = """
                @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
                Escolha uma opção para a conversão =]:
                1 - USD => ARS
                2 - ARS => USD
                3 - USD => BRL
                4 - BRL => USD
                5 - USD => COP
                6 - COP => USD
                7 - Sair
                Digite a opção que deseja executar...
                @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
                """;
            System.out.println(menu);
            int opcao = scanner.nextInt();
            String moedaBase, moedaDestino;

            if (opcao == 7) {
                executar = false;
                System.out.println("Programa encerrado!");
            }

            switch (opcao) {
                case 1 -> { moedaBase = "USD"; moedaDestino = "ARS"; }
                case 2 -> { moedaBase = "ARS"; moedaDestino = "USD"; }
                case 3 -> { moedaBase = "USD"; moedaDestino = "BRL"; }
                case 4 -> { moedaBase = "BRL"; moedaDestino = "USD"; }
                case 5 -> { moedaBase = "USD"; moedaDestino = "COP"; }
                case 6 -> { moedaBase = "COP"; moedaDestino = "USD"; }
                default -> throw new ErroDeConversaoException("Opção Inválida!");
            }

            System.out.println("Digite um valor para converter: ");
            double quantia = scanner.nextDouble();

            try {
                double valorConvertido = apiClient.converterMoeda(moedaBase, moedaDestino, quantia);
                System.out.printf("Valor convertido: %.2f %s\n", valorConvertido, moedaDestino + "\n");
            }
            catch (IOException e) {
                System.out.println("Erro ao buscar taxa de câmbio: " + e.getMessage());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        // Fechamento do scanner
        scanner.close();
    }
}
