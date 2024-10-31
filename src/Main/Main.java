/**
 * Este projeto é um desafio do programa Oracle Next Education, que visa desenvolver um conversor de moedas utilizando Java.
 * O objetivo é criar uma aplicação simples que permita aos usuários converter valores entre diferentes moedas,
 * utilizando a API ExchangeRateAPI para obter as taxas de câmbio mais recentes.
 */

package Main;
import Models.Classes.ServicoConversao;

public class Main {
    public static void main (String[] args) {
        /**
         * Aqui o metodo main é o ponto de entrada do programa.
         * Uma instância da classe Models.Classes.ServicoConversao é criada e o metodo executarConversao() é chamado para iniciar o processo de conversão de moedas
         * Toda a estrutura, design e lógica foi mantida dentro das outras classes, para que o metodo main esteja bem organizado, fácil de ler e entender
         */

        ServicoConversao conversao = new ServicoConversao();
        conversao.executarConversao();
    }
}
