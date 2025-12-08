package exception;

@SuppressWarnings("serial")
public class CapacidadeInvalidaException extends RuntimeException {
    public CapacidadeInvalidaException(String msg) {
        super(msg);
    }
}

