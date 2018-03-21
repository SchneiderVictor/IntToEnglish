package Conversion;

import java.util.ArrayList;

/**
 * separates any *valid* number into a series of arrays of manageable
 * integers to be translated
 */
class UniqueValuesAggregator {
    /**
     * separates a number into a an array of "sub-integers"
     * ex.: 47683 -> [3, 8, 6, 7, 4]
     *
     * @param number original input number
     * @return input number separated into an array of sub-integers
     */
    private static Integer[] singleValuesOf(int number) {
        // the growing list of separated "sub-integers"
        ArrayList<Integer> separatedValues = new ArrayList<>();
        // the base 10 power of the sub-integer being evaluated
        int powerCounter = 1;
        // tracker of the sum of all previous values evaluated
        int previousValue = 0;

        // while 10 ^ powerCounter is not bigger than number...
        while (Math.pow(10, powerCounter) < number * 10) {
            // get the power of the current sub-integer
            int power = (int) (Math.pow(10, powerCounter));
            // remove all sub-integers with larger powers
            int newValue = number % power;
            // remove all sub-integers with smaller powers and add to separatedValues
            separatedValues.add((newValue - previousValue) / (power / 10));
            previousValue = newValue;
            powerCounter++;
        }

        return separatedValues.toArray(new Integer[0]);
    }

    /**
     * groups single values into threes, translating into
     * 0 - 999 (inclusive)
     * ex.: 46978234 -> [[4, 3, 2], [8, 7, 9], [6, 4]]
     *
     * @param number original input number
     * @return input number separated into an array of sub-integers and groups into powers of 2
     */
    static ArrayList<Integer[]> groupedValuesOf(int number) {
        // separated array of sub-integers
        Integer[] singleValues = singleValuesOf(number);
        // growing total list
        ArrayList<Integer[]> outerList = new ArrayList<>();
        // temporary inner lists
        ArrayList<Integer> innerList = new ArrayList<>();

        for (int index = 0; index < singleValues.length; index++) {
            // if wer are not at a power of 2 or we are at the first sub-integer...
            if (index % 3 != 0 || (index % 4 == 0 && index == 0)) {
                innerList.add(singleValues[index]);
            } else { // push the innerList into the outerList and reset
                outerList.add(innerList.toArray(new Integer[0]));
                innerList = new ArrayList<>();
                innerList.add(singleValues[index]);
            }
        }
        outerList.add(innerList.toArray(new Integer[0]));

        return outerList;
    }
}
