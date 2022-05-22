package pl.put.poznan.transformer.transformation;

public class LatexTextTransformation extends TextTransformation {
    public static final String NAME = "latex";

    public LatexTextTransformation() {
        super();
    }

    public LatexTextTransformation(final TextTransformation previousTransformation) {
        super(previousTransformation);
    }

    private String latex(final String text) {

        String s1 = text.replace("$", "\\$");
        return s1.replace("&", "\\&");
    }

    @Override
    public String transform(final String text) {
        return latex(super.transform(text));
    }
}

