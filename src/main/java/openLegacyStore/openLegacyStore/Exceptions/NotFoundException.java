package openLegacyStore.openLegacyStore.Exceptions;

/**
 * I didnt use it at the end, but left it here becuse it can be useful..
 */

public class NotFoundException extends Exception{
    public NotFoundException() {
        super("Not found. incorrect entry");
    }

    public NotFoundException(String message) {
        super(message);
    }
}
