package pl.put.poznan.transformer.transformation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumbersTextTransformation implements TextTransformation{
    public static final String NAME = "numberstxt";
    private static String[] onedigit = new String[] {"zero", "one", "two", "three", "four", "ive", "six", "seven", "eight", "nine"};
    private static String[] twodigits = new String[] {"ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "sighteen", "nineteen"};
    private static String[] multipleoftens = new String[] {"",  "", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};
    private static String[] poweroftens = new String[] {"hundred", "thousand"};

    private String get_digits(char num[], int length){
        return onedigit[Character.getNumericValue(num[length - 1])];
    }
    private String get_twodigit(char num[], int length){
        if(Character.getNumericValue(num[length - 2]) == 1){
            return  " " + twodigits[Character.getNumericValue(num[length - 1])];
        }
        if(num[length - 2] == '0'){
            if(num[length - 1] == '0'){return "";}
            return onedigit[Character.getNumericValue(num[length - 1])];
        }
        return " " + multipleoftens[Character.getNumericValue(num[length - 2])] + " " + this.get_digits(num, length);
    }
    //get rid of zeros at front
    private String get_threedigit(char num[]){
        int length = num.length;
        if(Character.getNumericValue(num[length - 3]) == 0){
            return this.get_twodigit(num, length).substring(1);
        }
        return onedigit[Character.getNumericValue(num[length - 3])] + " " + poweroftens[0] + this.get_twodigit(num, length);
    }
    private String get_float(String s){
        String[] splitted = s.split("\\.");
        String new_s = splitted[0];
        if( s.charAt(0) == '-') {
            new_s = splitted[0].substring(1);
        }
        if(new_s.length() > 3){return s;}
        splitted[1] = String.format("%-3s", splitted[1] ).replace(' ', '0').substring(0, 3);
        return  this.get_int(splitted[0])
                + " and " + this.get_threedigit(splitted[1].toCharArray())
                + " thousandths";
    }

    private String get_int(String s){
        String res = "";
        String new_s = s;
        if( s.charAt(0) == '-'){
            new_s = s.substring(1);
            res = res + "negative ";
        }
        new_s =  String.format("%3s", new_s).replace(' ', '0');
        if(new_s.length() > 3){
            return s;
        }
        return res + this.get_threedigit(new_s.toCharArray());
    }

    private String get_floats(String s){
        ArrayList<String> listOfFloats = this.findFloats(s);
        String text2=listOfFloats.stream()
                .map(toRem -> (Function<String,String>)s1->s1.replaceAll(toRem, this.get_float(toRem)))
                .reduce(Function.identity(), Function::andThen)
                .apply(s);
        return text2;
    }
    private String get_ints(String s){
        List<String> listDistinct = this.findIntegers(s); //make it distinct
        String res=listDistinct.stream()
                .map(toRem-> (Function<String,String>)s1->s1.replaceAll(toRem, this.get_int(toRem)))
                .reduce(Function.identity(), Function::andThen)
                .apply(s);

        return res;}

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

    @Override
    public String apply(String s) {
        return this.get_ints(this.get_floats(s));
    }
}
