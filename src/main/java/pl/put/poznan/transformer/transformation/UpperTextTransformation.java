package pl.put.poznan.transformer.transformation;

import java.util.Locale;

public class UpperTextTransformation implements TextTransformation {
    public static final String NAME = "upper";

    @Override
    public String apply(String s) {
        return s.toUpperCase(Locale.ROOT);
    }
}
