package pl.put.poznan.transformer.transformation;

public class IdentityTextTransformation extends TextTransformation {
    public static final String NAME = "identity";

    public IdentityTextTransformation() {
        super();
    }

    public IdentityTextTransformation(final TextTransformation textTransformation) {
        super(textTransformation);
    }

    @Override
    public String transform(final String text) {
        return text;
    }
}
