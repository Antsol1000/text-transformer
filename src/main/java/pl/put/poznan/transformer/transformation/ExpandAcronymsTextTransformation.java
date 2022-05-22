package pl.put.poznan.transformer.transformation;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Override
    public String transform(final String text) {
        return expand(super.transform(text));
    }
}
