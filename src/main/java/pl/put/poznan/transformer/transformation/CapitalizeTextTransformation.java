package pl.put.poznan.transformer.transformation;

import java.util.Arrays;
import java.util.stream.Collectors;

public class CapitalizeTextTransformation implements TextTransformation{
    public static final String NAME = "capitalize";

    @Override
    public String apply(String s) {
        if ("".equals(s)) {
            return s;
        }

        return Arrays.asList(s.split(" "))
                .stream()
                .map(el -> {
                    if ("".equals(el)) {
                        return el;
                    }
                    return el.substring(0, 1).toUpperCase() + el.substring(1);
                })
                .collect(Collectors.joining(" "));
    }
}
