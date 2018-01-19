import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;

import java.awt.*;
import java.util.HashMap;

/**
 * stores all unique numbers/values required for any translation
 */
final class UniqueValuesLibrary extends Container {
    // A HashMap of all unique numbers/values
    private final static HashMap<Integer, String> values = new HashMap<Integer, String>() {{
        put(0, "zero");
        put(1, "one");
        put(2, "two");
        put(3, "three");
        put(4, "four");
        put(5, "five");
        put(6, "six");
        put(7, "seven");
        put(8, "eight");
        put(9, "nine");
        put(10, "ten");
        put(11, "eleven");
        put(12, "twelve");
        put(13, "thirteen");
        put(14, "fourteen");
        put(15, "fifteen");
        put(16, "sixteen");
        put(17, "seventeen");
        put(18, "eighteen");
        put(19, "nineteen");
        put(20, "twenty");
        put(30, "thirty");
        put(40, "forty");
        put(50, "fifty");
        put(60, "sixty");
        put(70, "seventy");
        put(80, "eighty");
        put(90, "ninety");
        put(100, "hundred");
        put(1000, "thousand");
        put(1000000, "million");
        put(1000000000, "billion");
    }};

    /**
     * returns the english translation of any unique number
     *
     * @param number unique number to be translated
     * @return english translation of number
     * @throws ValueException thrown if number is not unique
     */
    public static String getWordValueOf(int number) throws ValueException {
        if (values.containsKey(number)) {
            return values.get(number);
        } else {
            throw new ValueException("given value is not unique");
        }
    }

    public static boolean contains(int number) {
        return values.containsKey(number);
    }
}
