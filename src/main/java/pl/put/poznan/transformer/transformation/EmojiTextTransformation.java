package pl.put.poznan.transformer.transformation;

import java.util.HashMap;
import java.util.Map;

import static java.util.Map.entry;

/**
 * Class proving way to express keywords as emojis: i.e.: I am so happy -> I am so :)
 * @author Nina Zukowska
 * @version 2.2
 */

public class EmojiTextTransformation extends TextTransformation {
    public static final String NAME = "emoji";

    Map<String, String> emojiMap = Map.ofEntries(
            entry("happy", ":)"),
            entry("sad", ":("),
            entry("laugh", ":D"),
            entry("meh", ":|"),
            entry("boss", "B)"),
            entry("cat", ":3"),
            entry("wink", ";‑)"),
            entry("elvis", "5:‑)"),
            entry("heart", "<3"),
            entry("surprised", "O_O"),
            entry("troubled", "(>_<)"),
            entry("tired", "(=_=)"),
            entry("headphones", "((d[-_-]b))"),
            entry("suspicious", "(p_-)")
    );

    public EmojiTextTransformation() {
        super();
    }

    public EmojiTextTransformation(final TextTransformation previousTransformation) {
        super(previousTransformation);
    }

    private String emoji( String text) {
        for(HashMap.Entry<String, String> emoji : emojiMap.entrySet())
        {
            text = text.replace(emoji.getKey(), emoji.getValue());
        }
        return text;

    }

    /**
     * Method applying the transformation
     * @param text The text to which transformation will be applied
     * @return transformed text with emojis
     * @see  EmojiTextTransformation#emoji(String)
     */
    @Override
    public String transform(final String text) {
        return emoji(super.transform(text));
    }
}

