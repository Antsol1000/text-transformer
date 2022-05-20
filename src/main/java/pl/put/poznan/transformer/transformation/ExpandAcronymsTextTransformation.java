package pl.put.poznan.transformer.transformation;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

public class ExpandAcronymsTextTransformation implements TextTransformation{
    public static final String NAME = "expand";

    private Map<String, String> map = new HashMap<>() {{
        put("prof.", "professor");
        put("dr", "doctor");
        put("e.g.", "for example");
        put("aso", "and so on");
    }};

    @Override
    public String apply(String s) {
        if ("".equals(s)) {
            return s;
        }

        return Arrays.asList(s.split(" "))
                .stream()
                .map(el -> {
                    String word = map.get(el.toLowerCase(Locale.ROOT));

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
}
