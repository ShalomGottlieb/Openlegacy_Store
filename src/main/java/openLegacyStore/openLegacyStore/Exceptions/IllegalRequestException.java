package openLegacyStore.openLegacyStore.Exceptions;

public class IllegalRequestException extends Exception{
    public IllegalRequestException() {
        super("Illegal Request");
    }

    public IllegalRequestException(String message) {
        super(message);
    }
}
