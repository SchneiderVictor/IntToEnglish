package Application;

import Conversion.ValuesTranslator;

/**
 * static class to be used in java code; allows translation of integers
 * between 0 and 999999999 (inclusive)
 * to their english translations.
 */
public class IntToEngTranslator {

    private static boolean isValid(int number) {
        return number >= 0 && number <= 999999999;
    }

    public static String translate(int number) throws IllegalArgumentException {
        if (isValid(number)) {
            return ValuesTranslator.translate(number);
        } else {
            throw new IllegalArgumentException();
        }
    }
}
