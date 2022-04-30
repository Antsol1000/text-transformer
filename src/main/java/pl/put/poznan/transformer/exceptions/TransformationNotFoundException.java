package pl.put.poznan.transformer.exceptions;

public class TransformationNotFoundException extends IllegalArgumentException {
    public TransformationNotFoundException(final String name) {
        super("Could not find transformation " + name);
    }
}
