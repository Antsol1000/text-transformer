package pl.put.poznan.transformer.transformation;

import java.util.Locale;

public class LowerTextTransformation extends TextTransformation {
    public static final String NAME = "lower";

    public LowerTextTransformation() {
        super();
    }

    public LowerTextTransformation(final TextTransformation previousTransformation) {
        super(previousTransformation);
    }

    private String lower(final String text) {
        return text.toLowerCase(Locale.ROOT);
    }

    @Override
    public String transform(final String text) {
        return lower(super.transform(text));
    }
}
