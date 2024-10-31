package Models.Classes;

import Models.Exception.ErroDeConversaoException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * A classe Models.Classes.ExchangeRateApi é responsável por interagir com a API de conversão de moedas, obter as taxas e realizar a conversão.
 */

public class ExchangeRateApi {
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/";
    private final String API_KEY;

    /**
     * O construtor da classe Models.Classes.ExchangeRateApi inicializa a classe com a chave API que deve ser configurada na variável de ambiente EXCHANGE_RATE_API_KEY.
     * Se a chave não estiver disponível, uma exception será lançada, avisando que não foi possivel encontrar a chave AonPI na variável de ambiente do seu sistema operacional
     * </br>
     * <b>(O nome da variável de ambiente pode ficar a sua escolha, eu decidi deixar como EXCHANGE_RATE_API_KEY apenas para facilitar a leitura de código)</b>
     */
    public ExchangeRateApi() {
        this.API_KEY = System.getenv("API_KEY");
        if(API_KEY == null || API_KEY.isEmpty()) {
            throw new IllegalStateException("Não foi possível encontrar a sua chave API. MOTIVO: Configure a variável de ambiente EXCHANGE_RATE_API_KEY");
        }
    }


    /**
     * O metodo converterMoeda() é responsável por converter uma quantia da moeda base escolhida para a moeda de destino, ou seja, você tem 6 opções de escolha,
     * entre elas tem a opção de fazer uma conversão de USD (dolar) para BRL (reais)
     * </br>
     * @param moedaBase => É a moeda de origem (Ex: "USD")
     * @param moedaDestino => É a moeda que receberá o valor convertido (Ex: "BRL")
     * @param quantia => É o valor que o usuário escolherá para ser convertido no console
     * @return Retornará o valor convertido da moeda de destino
     * @throws IOException => Uma exceção que ocorrerá caso aconteça um erro na API
     * @throws InterruptedException => Ocorrerá caso a operação for interrompida
     * @throws ErroDeConversaoException => Uma exceção personalizada, serve para ser lançada caso a moeda de destino não for encontrada
     */

    public double converterMoeda (String moedaBase, String moedaDestino, double quantia) throws IOException, InterruptedException {
        String urlString = API_URL + API_KEY  + "/latest/" + moedaBase;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(urlString)).GET().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if(response.statusCode() != 200) {
            throw new IOException("Erro ao converter moeda: " + response.statusCode());
        }
        JsonObject objetoJson = JsonParser.parseString(response.body()).getAsJsonObject();

        JsonObject rates = objetoJson.getAsJsonObject("conversion_rates");
        if (rates == null || !rates.has(moedaDestino)) {
            throw new ErroDeConversaoException("Taxa de câmbio para " + moedaDestino + " não encontrada.");
        }

        double taxa = objetoJson.getAsJsonObject("conversion_rates").get(moedaDestino).getAsDouble();
        return taxa * quantia;
    }


}
