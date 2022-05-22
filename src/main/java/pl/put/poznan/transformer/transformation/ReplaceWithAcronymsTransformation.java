package pl.put.poznan.transformer.transformation;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class ReplaceWithAcronymsTransformation extends TextTransformation {
    public static final String NAME = "acronyms";

    public ReplaceWithAcronymsTransformation() {
        super();
    }

    public ReplaceWithAcronymsTransformation(final TextTransformation previousTransformation) {
        super(previousTransformation);
    }

    private static final Map<String, String> ACRONYMS_MAP = new HashMap<>() {{
        put("for example", "e.g.");
        put("among others", "i.a.");
        put("and so on", "aso");
    }};

    private String removeWithAcronyms(String text) {
        if ("".equals(text)) {
            return text;
        }
        text = text.toLowerCase(Locale.ROOT);
        for (String key : ACRONYMS_MAP.keySet()) {
            text = text.replace(key, ACRONYMS_MAP.get(key));
        }
        return text;
    }

    @Override
    public String transform(final String text) {
        return removeWithAcronyms(super.transform(text));
    }

}
