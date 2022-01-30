package openLegacyStore.openLegacyStore.Exceptions;


public class NotFoundException extends Exception{
    public NotFoundException() {
        super("Not found. incorrect entry");
    }

    public NotFoundException(String message) {
        super(message);
    }
}
