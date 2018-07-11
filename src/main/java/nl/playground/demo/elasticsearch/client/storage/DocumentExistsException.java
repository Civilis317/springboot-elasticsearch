package nl.playground.demo.elasticsearch.client.storage;

public class DocumentExistsException extends RuntimeException {

    public DocumentExistsException(String message) {
        super(message);
    }

    public DocumentExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
