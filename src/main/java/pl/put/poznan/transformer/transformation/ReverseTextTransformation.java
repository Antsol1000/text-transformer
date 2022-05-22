package pl.put.poznan.transformer.transformation;

public class ReverseTextTransformation extends TextTransformation {
    public static final String NAME = "reverse";

    public ReverseTextTransformation() {
        super();
    }

    public ReverseTextTransformation(final TextTransformation previousTransformation) {
        super(previousTransformation);
    }

    private String reverse(final String text) {
        final char[] given = text.toCharArray();
        final char[] t = new StringBuilder(text).reverse().toString().toCharArray();
        for (int i = 0; i < text.length(); i++) {
            if (Character.isUpperCase(given[i])) {
                t[i] = Character.toUpperCase(t[i]);
            } else if (Character.isLowerCase(given[i])) {
                t[i] = Character.toLowerCase(t[i]);
            }
        }
        return String.valueOf(t);
    }

    @Override
    public String transform(final String text) {
        return reverse(super.transform(text));
    }
}
