package pl.put.poznan.transformer.transformation;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class proving transformation of numbers up from (-1000,1000) into text form
 * @author Nina Zukowska
 * @version 1.5
 */
public class NumbersTextTransformation implements TextTransformation {
    public static final String NAME = "numbers";

    private static final String[] ONE_DIGIT = new String[]{"zero", "one", "two", "three", "four", "ive", "six", "seven"
            , "eight", "nine"};
    private static final String[] TWO_DIGITS = new String[]{"ten", "eleven", "twelve", "thirteen", "fourteen",
            "fifteen", "sixteen", "seventeen", "sixteen", "nineteen"};
    private static final String[] MULTIPLE_OF_TENS = new String[]{"", "", "twenty", "thirty", "forty", "fifty", "sixty"
            , "seventy", "eighty", "ninety"};
    private static final String[] POWER_OF_TENS = new String[]{"hundred", "thousand"};

    private String getDigits(char[] num, int length) {
        return ONE_DIGIT[Character.getNumericValue(num[length - 1])];
    }

    private String getTwoDigit(char[] num, int length) {
        if (Character.getNumericValue(num[length - 2]) == 1) {
            return " " + TWO_DIGITS[Character.getNumericValue(num[length - 1])];
        }
        if (num[length - 2] == '0') {
            if (num[length - 1] == '0') {
                return "";
            }
            return ONE_DIGIT[Character.getNumericValue(num[length - 1])];
        }
        return " " + MULTIPLE_OF_TENS[Character.getNumericValue(num[length - 2])] + " " + this.getDigits(num, length);
    }

    //get rid of zeros at front
    private String getThreeDigit(char[] num) {
        int length = num.length;
        if (Character.getNumericValue(num[length - 3]) == 0) {
            return this.getTwoDigit(num, length).substring(1);
        }
        return ONE_DIGIT[Character.getNumericValue(num[length - 3])] + " " + POWER_OF_TENS[0] + this.getTwoDigit(num,
                length);
    }
    /** Picks up floating point numbers and transforms them into text, if in the given range (-1000, 1000)
     * @param s The string to which the transformation will be applied to
     * @return Transformed string
     */
    private String getFloat(String s) {
        String[] splitted = s.split("\\.");
        String new_s = splitted[0];
        if (s.charAt(0) == '-') {
            new_s = splitted[0].substring(1);
        }
        if (new_s.length() > 3) {
            return s;
        }
        splitted[1] = String.format("%-3s", splitted[1]).replace(' ', '0').substring(0, 3);
        return this.getInt(splitted[0])
                + " and " + this.getThreeDigit(splitted[1].toCharArray())
                + " thousandths";
    }

    /** Picks up intigers and transforms them into text, if in the given range (-1000, 1000)
     * @param s The string to which the transformation will be applied to
     * @return Transformed string
     */
    private String getInt(String s) {
        String res = "";
        String new_s = s;
        if (s.charAt(0) == '-') {
            new_s = s.substring(1);
            res = res + "negative ";
        }
        new_s = String.format("%3s", new_s).replace(' ', '0');
        if (new_s.length() > 3) {
            return s;
        }
        return res + this.getThreeDigit(new_s.toCharArray());
    }

    private String getFloats(String s) {
        ArrayList<String> listOfFloats = this.findFloats(s);
        return listOfFloats.stream()
                .map(toRem -> (Function<String, String>) s1 -> s1.replaceAll(toRem, this.getFloat(toRem)))
                .reduce(Function.identity(), Function::andThen)
                .apply(s);
    }

    private String getInts(String s) {
        List<String> listDistinct = this.findIntegers(s); //make it distinct

        return listDistinct.stream()
                .map(toRem -> (Function<String, String>) s1 -> s1.replaceAll(toRem, this.getInt(toRem)))
                .reduce(Function.identity(), Function::andThen)
                .apply(s);
    }

    private List<String> findIntegers(String stringToSearch) {
        Pattern integerPattern = Pattern.compile("-?\\d+");
        Matcher matcher = integerPattern.matcher(stringToSearch);

        List<String> integerList = new ArrayList<>();
        while (matcher.find()) {
            integerList.add(matcher.group());
        }
        return integerList;
    }

    private ArrayList<String> findFloats(String stringToSearch) {
        Pattern integerPattern = Pattern.compile("(?:-?\\d+(?:\\.\\d+)|-?\\.\\d+)");
        Matcher matcher = integerPattern.matcher(stringToSearch);
        ArrayList<String> floatsList = new ArrayList<String>();
        while (matcher.find()) {
            floatsList.add(matcher.group());
        }
        return floatsList;
    }

    /** Applies
     * @param s The string to which the transformation will be applied to
     * @return Transformed string, with floats and ints as text
     * @see NumbersTextTransformation#getInts(String)
     * @see NumbersTextTransformation#getFloats(String)
     */
    @Override
    public String apply(String s) {
        return this.getInts(this.getFloats(s));
    }
}

