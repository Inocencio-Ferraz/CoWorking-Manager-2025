package exception;

@SuppressWarnings("serial")
public class CustoInvalidoException extends RuntimeException {
    public CustoInvalidoException(String msg) {
        super(msg);
    }
}
