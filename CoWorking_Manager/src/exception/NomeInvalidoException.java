package exception;

@SuppressWarnings("serial")
public class NomeInvalidoException extends RuntimeException {
    public NomeInvalidoException(String msg) {
        super(msg);
    }
}
