package pl.put.poznan.transformer.transformation;

import java.util.Locale;

public class LowerTextTransformation implements TextTransformation {
    public static final String NAME = "lower";

    @Override
    public String apply(String s) {
        return s.toLowerCase(Locale.ROOT);
    }
}
