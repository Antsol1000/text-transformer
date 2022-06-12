package pl.put.poznan.transformer.transformation;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Class proving transformation of expanding acronyms in the text
 * @author Mateusz Duda
 * @version 1.6
 */
public class ExpandAcronymsTextTransformation extends TextTransformation {
    public static final String NAME = "expand";

    public ExpandAcronymsTextTransformation() {
        super();
    }

    public ExpandAcronymsTextTransformation(final TextTransformation previousTransformation) {
        super(previousTransformation);
    }

    private static final Map<String, String> ACRONYMS_MAP = new HashMap<>() {{
        put("prof.", "professor");
        put("dr", "doctor");
        put("e.g.", "for example");
        put("aso", "and so on");
    }};

    /**
     * Method applying the transformation of expanding acronyms into text
     * @param text The text to which transformation will be applied
     * @return transformed text
     */
    private String expand(final String text) {
        if ("".equals(text)) {
            return text;
        }

        return Arrays.stream(text.split(" "))
                .map(el -> {
                    String word = ACRONYMS_MAP.get(el.toLowerCase(Locale.ROOT));

                    if (word == null) {
                        return el;
                    }

                    if (Character.isUpperCase(el.charAt(0))) {
                        return word.substring(0, 1).toUpperCase() + word.substring(1);
                    }

                    return word;
                })
                .collect(Collectors.joining(" "));
    }
    /**
     * Method applying the transformation of expanding acronyms into text
     * @param text The text to which transformation will be applied
     * @return transformed text
     * @see ExpandAcronymsTextTransformation#expand(String)
     */
    @Override
    public String transform(final String text) {
        return expand(super.transform(text));
    }
}
