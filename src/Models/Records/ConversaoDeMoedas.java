package Models.Records;

/**
 * O record ConversãoDeMoedas é responsável por armazenar informações sobre uma conversão de moedas
 *
 * @param moedaBase => Moeda de origem
 * @param moedaDestino => Moeda de destino
 * @param valor => Valor original a ser convertido
 * @param quantia => Quantia a ser convertida na moeda de destino
 * @param quantiaConvertida => O valor convertido após a conversão
 */

public record ConversaoDeMoedas(String moedaBase, String moedaDestino, double valor, double quantia, double quantiaConvertida) {
}
