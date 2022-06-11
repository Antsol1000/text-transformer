package pl.put.poznan.transformer.transformation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class proving transformation of quotes in text: meaning single quotes and double quotes, considered pairwise
 * @author Nina Zukowska
 * @version 2.1
 */
public class QuoteTextTransformation extends TextTransformation {
    public static final String NAME = "frenchQuote";
    private static final String[] quotes = {"«", "»"};

    public QuoteTextTransformation() {super(); }

    public QuoteTextTransformation(final TextTransformation previousTransformation) {
        super(previousTransformation);
    }


    private String changeQuotes(final String text) {
        String[] texts = {text};
        Pattern p1 = Pattern.compile("\"");
        Pattern p2 = Pattern.compile("[']");
        Matcher m = p1.matcher(text);
        StringBuffer sb = new StringBuffer(text.length());
        int i;
        for(int j = 0; j<2; j++){
            i =0;
            while (m.find()) {
                if (i % 2 == 0) {m.appendReplacement(sb, quotes[0]);}
                else {m.appendReplacement(sb, quotes[1]);}
                ++i;
            }
            sb.append(texts[0].substring(sb.length(),texts[0].length()));
            texts[0] = sb.toString();
            m = p2.matcher(sb.toString());
            sb.delete(0, sb.length());}
        return texts[0];
    }

    /**
     * Method applying the transformation of numbers into text
     * @param text The text to which transformation will be applied
     * @return transformed text
     * @see QuoteTextTransformation#changeQuotes(String)
     */
    @Override
    public String transform(final String text) {
        return changeQuotes(super.transform(text));
    }
}
