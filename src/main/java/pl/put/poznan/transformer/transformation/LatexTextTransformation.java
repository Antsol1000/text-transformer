package pl.put.poznan.transformer.transformation;

public class LatexTextTransformation implements TextTransformation{
    public static final String NAME = "latex";
    @Override
    public String apply(final String s) {

        String s1 = s.replace("$", "\\$");
        String s2 = s1.replace("&", "\\&");
        return s2;
    }

}

