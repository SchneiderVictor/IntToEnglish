package Conversion;

import java.util.ArrayList;

/**
 * translates integers into their english meanings
 */
public class ValuesTranslator {
    private static String getWordValueOf(int number) {
        return UniqueValuesLibrary.getWordValueOf(number);
    }

    /**
     * takes grouped sub-integers and translates them
     * into simple grouped meanings
     *
     * @param values grouped sub-integers
     * @return an array of grouped sub-integers, translated into english
     */
    private static String[] groupTranslator(Integer[] values) {
        String[] translatedValues = new String[values.length];

        for (int i = 0; i < values.length; i++) {
            translateGroup(i, values, translatedValues);
        }

        return translatedValues;
    }

    /**
     * translates one group of sub-integers
     *
     * @param index            group index
     * @param values           grouped sub-integers
     * @param translatedValues growing array of translations
     */
    private static void translateGroup(int index, Integer[] values, String[] translatedValues) {
        // current sub-integers
        int value = values[index];

        // if the 2nd sub-integer from the right is a 1;
        // we will need to delete the first one for a special translation
        if (index == 1) {
            if (value == 1) {
                translatedValues[index - 1] = "";
            }
        }

        if (index < 2) { // figures out how to translate the first 2 sub-integers
            if (index == 1 && value == 1) { // if the second sub-integer is a 1, we can do a direct translation
                int newInputValue = values[0] + (values[1] * 10);
                translatedValues[index] = getWordValueOf(newInputValue);
            } else if (value != 0) { // else a compound translation
                int nextInputValue = (int) (value * Math.pow(10, index));
                translatedValues[index] = getWordValueOf(nextInputValue);
            } else { // else forget the 2nd translation
                translatedValues[index] = "";
            }
        } else { // translates multiples of 100
            String prefix = getWordValueOf(value);
            String suffix = getWordValueOf((int) Math.pow(10, index));
            if (value != 0) {
                translatedValues[index] = prefix + " " + suffix;
            } else {
                translatedValues[index] = "";
            }
        }
    }

    /**
     * concatenates the grouped translations into one english phrase
     *
     * @param number number to be translated
     * @return string representing the final english translation
     */
    private static String translatedValue(int number) {
        ArrayList<Integer[]> groupedValues = UniqueValuesAggregator.groupedValuesOf(number);
        StringBuilder result = new StringBuilder();

        // for each group...
        for (int exponent = groupedValues.size() - 1; exponent > -1; exponent--) {
            String[] translatedGroup = groupTranslator(groupedValues.get(exponent));

            // for each sub-string/sub-translation...
            for (int i = translatedGroup.length - 1; i > -1; i--) {
                result.append(translatedGroup[i]);
                if (!translatedGroup[i].equals("") && i != 0) { // append a space if
                    result.append(" ");                          // the corresponding sub-integer was ignored
                }
            }

            // append final suffixes for groups (exponents of 10 >= 1000)
            if (exponent != 0) {
                String exponentSuffix = getWordValueOf((int) Math.pow(10, exponent * 3));
                result.append(" ");
                result.append(exponentSuffix);
                result.append(" ");
            }
        }
        return result.toString();
    }

    /**
     * translates a number into its english meaning
     *
     * @param number number to be translated
     * @return string representing the final english translation
     */
    public static String translate(int number) {
        // allows for quick direct translations
        if (UniqueValuesLibrary.contains(number)) {
            return UniqueValuesLibrary.getWordValueOf(number);
        }
        return translatedValue(number);
    }
}
