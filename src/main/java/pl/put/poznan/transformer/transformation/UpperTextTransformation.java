package pl.put.poznan.transformer.transformation;

import java.util.Locale;

public class UpperTextTransformation extends TextTransformation {
    public static final String NAME = "upper";

    public UpperTextTransformation() {
        super();
    }

    public UpperTextTransformation(final TextTransformation previousTransformation) {
        super(previousTransformation);
    }

    private String upper(String text) {
        return text.toUpperCase(Locale.ROOT);
    }

    @Override
    public String transform(final String text) {
        return upper(super.transform(text));
    }
}
