package pl.put.poznan.transformer.transformation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RemoveRepetitionsTransformation extends TextTransformation {
    public static final String NAME = "repetitions";

    public RemoveRepetitionsTransformation() {
        super();
    }

    public RemoveRepetitionsTransformation(final TextTransformation previousTransformation) {
        super(previousTransformation);
    }

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

    @Override
    public String transform(final String text) {
        return removeRepetitions(super.transform(text));
    }

}
