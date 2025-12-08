package exception;


@SuppressWarnings("serial")
public class PrecoInvalidoException extends RuntimeException {
    public PrecoInvalidoException(String msg) {
        super(msg);
    }
}
