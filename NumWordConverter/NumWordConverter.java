/**
 * runnable class accessed from command line; allows translation of integers
 * between 0 (inclusive) and Integer.MAX_VALUE (exclusive)
 * to their english translations.
 *
 * Usage:
 * > javac NumWordConverter.java
 * > java NumWordConverter <int args ...>
 * args must be between 0 (inclusive) and Integer.toString(Integer.MAX_VALUE)(exclusive)
 */
final public class NumWordConverter {
    /**
     * checks whether or not there are any input args,
     * they are all integers and they are all between 0 (inclusive)
     * and Integer.MAX_VALUE (exclusive)
     *
     * @param args String array of input arguments
     * @return boolean representing whether the args are all valid
     */
    private static boolean isValidInput(String[] args) {
        // checks if any args were passed
        boolean anyArgs = args.length != 0;
        // tracks whether each arg can translate into an integer
        boolean totalInputValid = true;
        // tracks whether each arg is between 0 (inclusive) and Integer.MAX_VALUE (exclusive)
        boolean rangesValid = true;

        // loop through every arg. any invalid arg marks the whole set of args as invalid
        for (String input : args) {
            if (!input.matches("^[0-9]+$")) {
                totalInputValid = false;
            } else {
                // only convert and check args if possible
                int number = Integer.valueOf(input);
                if (number < 0 || number == Integer.MAX_VALUE) {
                    rangesValid = false;
                }
            }
        }

        // print out the program specifics if the input args are incorrect
        if ((!anyArgs) || (!totalInputValid) || (!rangesValid)) {
            System.out.println("Usage: java NumWordConverter <int args ...>\n" +
                    "\targs between 0 (inclusive) and " + Integer.toString(Integer.MAX_VALUE) + " (exclusive)\n");
        }

        return (anyArgs && totalInputValid && rangesValid);
    }

    /**
     * launched program
     *
     * @param args input arguments from command line
     */
    public static void main(String[] args) {
        if (isValidInput(args)) {
            for (String number : args) {
                String translation = ValuesTranslator.translate(Integer.valueOf(number));
                System.out.println(translation);
            }
        }
    }
}
