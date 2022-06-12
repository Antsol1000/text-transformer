package pl.put.poznan.transformer.transformation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * Class proving transformation of removing repetitions to the text
 * @author Jakub Cichy
 * @version 1.7
 */
public class RemoveRepetitionsTransformation extends TextTransformation {
    public static final String NAME = "repetitions";

    public RemoveRepetitionsTransformation() {
        super();
    }

    public RemoveRepetitionsTransformation(final TextTransformation previousTransformation) {
        super(previousTransformation);
    }
    /**
     * Method applying the transformation removing repetitions into text
     * @param text The text to which transformation will be applied
     * @return transformed text
     */
    private String removeRepetitions(String text) {
        if ("".equals(text)) {
            return text;
        }
        String regex = "\\b(\\w+)(?:\\W+\\1\\b)+";
        Pattern pattern = Pattern.compile(regex, Pattern.UNICODE_CHARACTER_CLASS);
        Matcher match = pattern.matcher(text);
        while (match.find()) {
            text = text.replace(match.group(), match.group(1));
        }
        return text;
    }
    /**
     * Method applying the transformation of removing repetitions into text
     * @param text The text to which transformation will be applied
     * @return transformed text
     * @see RemoveRepetitionsTransformation#removeRepetitions(String) 
     */
    @Override
    public String transform(final String text) {
        return removeRepetitions(super.transform(text));
    }

}
