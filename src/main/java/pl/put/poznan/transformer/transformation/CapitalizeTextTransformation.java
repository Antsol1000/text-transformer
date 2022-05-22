package pl.put.poznan.transformer.transformation;

import java.util.Arrays;
import java.util.stream.Collectors;

public class CapitalizeTextTransformation extends TextTransformation {
    public static final String NAME = "capitalize";

    public CapitalizeTextTransformation() {
        super();
    }

    public CapitalizeTextTransformation(final TextTransformation previousTransformation) {
        super(previousTransformation);
    }

    private String capitalize(final String text) {
        if ("".equals(text)) {
            return text;
        }

        return Arrays.stream(text.split(" "))
                .map(el -> {
                    if ("".equals(el)) {
                        return el;
                    }
                    return el.substring(0, 1).toUpperCase() + el.substring(1);
                })
                .collect(Collectors.joining(" "));
    }

    @Override
    public String transform(final String text) {
        return capitalize(super.transform(text));
    }
}
