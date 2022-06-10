package pl.put.poznan.transformer.transformation;

import java.util.Optional;

public abstract class TextTransformation {

    private final Optional<TextTransformation> previousTransformation;

    public TextTransformation() {
        this.previousTransformation = Optional.empty();
    }

    public TextTransformation(final TextTransformation previousTransformation) {
        this.previousTransformation = Optional.of(previousTransformation);
    }

    public String transform(final String text) {
        return previousTransformation.map(x -> x.transform(text)).orElse(text);
    }
}
