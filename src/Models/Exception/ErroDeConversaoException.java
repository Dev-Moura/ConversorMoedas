package Models.Exception;

/**
 * A classe ErroDeConversaoException é uma exceção personalizada que é lançada em situações
 * onde ocorre um erro relacionado á conversão de moedas
 */

public class ErroDeConversaoException extends RuntimeException {

    /**
     * Construtor da classe ErroDeConversaoException
     * @param message => Exibe a mensagem de erro que descreve a causa da exceção.
     */

    public ErroDeConversaoException (String message) {
        super(message);
    }
}
