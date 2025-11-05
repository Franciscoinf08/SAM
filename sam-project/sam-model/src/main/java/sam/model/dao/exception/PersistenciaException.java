package sam.model.dao.exception;

public class PersistenciaException extends Exception {

    public PersistenciaException(String message, Exception exception) {
        super(message, exception);
    }

    public PersistenciaException(String message) {
        super(message);
    }
}
