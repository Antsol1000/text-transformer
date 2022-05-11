package pl.put.poznan.transformer.transformation;

public class IdentityTextTransformation implements TextTransformation {
    public static final String NAME = "identity";

    @Override
    public String apply(final String s) {
        return s;
    }

}
