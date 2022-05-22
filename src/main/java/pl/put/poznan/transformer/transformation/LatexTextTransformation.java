package pl.put.poznan.transformer.transformation;

/**
 * Class proving transformation of & and $ into latex format
 * @author Nina Zukowska
 * @version 1.6
 */

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

    /**
     * Method applying the transformation
     * @param text The text to which transformation will be applied
     * @return transformed text
     */
    @Override
    public String transform(final String text) {
        return latex(super.transform(text));
    }
}

