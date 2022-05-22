package pl.put.poznan.transformer.transformation;
/**
 * Class proving transformation of & and $ into latex format
 * @author Nina Zukowska
 * @version 1.5
 */
public class LatexTextTransformation implements TextTransformation{
    public static final String NAME = "latex";

    /** Picks up & and $ and puts backslash bofore them
     * @param s The string to which the transformation will be applied to
     * @return Transformed string
     */
    @Override
    public String apply(final String s) {

        String s1 = s.replace("$", "\\$");
        return s1.replace("&", "\\&");
    }

}

