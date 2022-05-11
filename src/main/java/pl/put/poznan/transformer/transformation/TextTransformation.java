package pl.put.poznan.transformer.transformation;

import java.util.Collection;
import java.util.function.Function;

public interface TextTransformation extends Function<String, String> {
    static TextTransformation composeAll(final Collection<TextTransformation> transformations) {
        return text -> {
            String result = text;
            for (TextTransformation t : transformations) {
                result = t.apply(result);
            }
            return result;
        };
    }
}
