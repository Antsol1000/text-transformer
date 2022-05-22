package pl.put.poznan.transformer.transformation;

import java.util.Locale;

/**
 * Class proving transformation into lower cases
 * @author Mateusz Duda
 * @version 1.5
 */

public class LowerTextTransformation implements TextTransformation {
    public static final String NAME = "lower";

    /** Applies the lower case transformation
     * @param s The string to which the transformation will be applied to
     * @return String in lower case letters
     */
    @Override
    public String apply(String s) {
        return s.toLowerCase(Locale.ROOT);
    }
}
