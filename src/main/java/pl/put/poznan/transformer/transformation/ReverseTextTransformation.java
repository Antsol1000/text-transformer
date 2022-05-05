package pl.put.poznan.transformer.transformation;

public class ReverseTextTransformation implements TextTransformation {
    public static final String NAME = "reverse";

    @Override
    public String apply(final String s) {
        final char[] given = s.toCharArray();
        final char[] t = new StringBuilder(s).reverse().toString().toCharArray();
        for (int i = 0; i < s.length(); i++) {
            if (Character.isUpperCase(given[i])) {
                t[i] = Character.toUpperCase(t[i]);
            } else if (Character.isLowerCase(given[i])) {
                t[i] = Character.toLowerCase(t[i]);
            }
        }
        return String.valueOf(t);
    }
}
