package pl.put.poznan.transformer.transformation;

import java.util.Locale;

/**
 * Class proving transformation of text into lower case letters
 * @author Mateusz Duda
 * @version 1.5
 */
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

    /**
     * Method applying the transformation into lower case letters
     * @param text The text to which transformation will be applied
     * @return transformed text
     */
    @Override
    public String transform(final String text) {
        return lower(super.transform(text));
    }
}
