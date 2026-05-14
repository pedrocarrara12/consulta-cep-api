package consulta_cep.exceptions;

public class CepNaoEncontrado extends RuntimeException {
    public CepNaoEncontrado(String message) {
        super(message);
    }
}
