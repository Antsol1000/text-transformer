package pl.put.poznan.transformer.transformation;

import java.util.StringJoiner;

/**
 * Class proving transformation of random words by making the casing alternating, this is often an expression
 * of poking fun at somebody. The words of which the casing is modified are chosen randomly.
 * @author Nina Zukowska
 * @version 2.3
 */

public class GoofyTextTransformation extends TextTransformation {
    public static final String NAME = "goofy";

    public GoofyTextTransformation() {
        super();
    }

    public GoofyTextTransformation(final TextTransformation previousTransformation) {
        super(previousTransformation);
    }

    private String getAlternatingCases(String word){
        StringBuilder s = new StringBuilder();
        final boolean[] alternate = {Character.isLowerCase(word.charAt(0))};
        word.chars()
                .forEach( i -> {
                    char c = (char) i;
                    s.append( alternate[0] ? Character.toUpperCase(c)
                            : Character.toLowerCase(c));
                    alternate[0] = !alternate[0];
                });
        return s.toString();
    }

    /**
     * Method randomly picking words that get transformed.
     * @param text The text from which words to be transformed will be picked from and passed to getAlternatingCases method.
     * @return transformed text
     * @see GoofyTextTransformation#getAlternatingCases(String)
     */
    private String goofy(final String text) {
        String[] arr = text.split(" ");
        StringJoiner sj = new StringJoiner(" ", "", "");
        for(String word: arr){
            sj.add( Math.random() > 0.7 ? this.getAlternatingCases(word) : word);
        }
        return sj.toString();
    }

    /**
     * Method applying the transformation
     * @param text The text to which the transfrmation will be applied to, meaning words from this text will be picked to get
     *             upper-lowercase transofrmation applied to them.
     * @return transformed text
     */
    @Override
    public String transform(final String text) {
        return goofy(super.transform(text));
    }
}

