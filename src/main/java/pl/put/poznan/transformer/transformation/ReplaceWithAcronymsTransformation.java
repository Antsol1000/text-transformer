package pl.put.poznan.transformer.transformation;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

public class ReplaceWithAcronymsTransformation extends TextTransformation{
    public static final String NAME = "acronyms";

    public ReplaceWithAcronymsTransformation() {
        super();
    }

    public ReplaceWithAcronymsTransformation(final TextTransformation previousTransformation) {
        super(previousTransformation);
    }

    private static final Map<String, String> acronymsMap = new HashMap<>() {{
        put("for example","e.g.");
        put("among others", "i.a.");
        put("and so on", "aso");
    }};

    private String expand( String text) {
        if ("".equals(text)) {
            return text;
        }
        text = text.toLowerCase(Locale.ROOT);
        for (String key: acronymsMap.keySet()) {
            text = text.replace(key, acronymsMap.get(key));
        }
        return text;
    }

    @Override
    public String transform(final String text) {
        return expand(super.transform(text));
    }

}
