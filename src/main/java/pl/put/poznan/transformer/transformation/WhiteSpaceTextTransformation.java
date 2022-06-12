package pl.put.poznan.transformer.transformation;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Class transforming all whitespace characters into spaces
 * @author Nina Zukowska
 * @version 2.4
 */
public class WhiteSpaceTextTransformation extends TextTransformation {
    public static final String NAME = "whitespace";

    public WhiteSpaceTextTransformation() {
        super();
    }

    public WhiteSpaceTextTransformation(final TextTransformation previousTransformation) {
        super(previousTransformation);
    }

    private String capitalize(String text) {
        return text.replaceAll("\\s+"," ");
    }

    @Override
    public String transform(final String text) {
        return capitalize(super.transform(text));
    }
}
