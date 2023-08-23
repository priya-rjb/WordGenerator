import java.util.InputMismatchException;
import java.util.Scanner;
import structure5.*;
/**
 * Reads the input text, builds the table with k characters at a time, and prints out a
 * randomly-generated string based on the character sequence probabilities from the input text
 */
public class WordGen {
    public static final int OUTPUT_LENGTH = 500;
    /**
     * Reads through the entire file and generates a large String of the characters in the file.
     * @return text a String with all of the characters in the file
     */
    public String readFromFile() {
        try {
            //Iterate through the file and create a String:
            Scanner in = new Scanner(System.in);
            StringBuffer textBuffer = new StringBuffer();
            while (in.hasNextLine()) {
                String line = in.nextLine();
                textBuffer.append(line);
                textBuffer.append("\n");
            }
            String text = textBuffer.toString();
            // 'text' now contains the full contents of the input.
            return text;
        } catch (InputMismatchException e) {
            System.out.println("Invalid input, file doesn't exist. Please try again. :)");
            System.exit(1);
        }
        return null;
    }
    /**
     * Generates a table with a string with k characters and the character that follows after.
     * @param inititalTextLength integer of the initial length of the characters in the file
     * @param k integer level of analysis
     * @param newString padded String ending with the first k character of the text
     * @param t Table storing the string of k characters and the frequencytable of the characters that follow.
     * @return Table t
     */
    public Table generateTable(int inititalTextLength, int k, String newString, Table t) {
        //Create a table with every k characters as String(key) and the next character as its value;
        //Use the padded String to account for the last k characters of the initial text:
        for (int prefixIndex = 0; prefixIndex < inititalTextLength; prefixIndex++) {
            t.add(newString.substring(prefixIndex, prefixIndex + k), newString.charAt(prefixIndex + k));
        }
        return t;
    }
    /**
     * Creates a new random text with the probablities proportional to k.
     * @param k integer level of analysis
     * @param newString padded String ending with the first k character of the text
     * @param t Table storing the string of k characters and the frequencytable of the characters that follow.
     * @param text String with the characters in the file
     */
    public void useTable(int k, String newString, Table t, String text) {
        //Store the first k characters of the String:
       String result = newString.substring(0, k);
        //Iterate through every string(With k characters) and choose a random proportional character that would follow:
        //Adds the character to the new text:
        for (int prefixIndex = 0; prefixIndex < OUTPUT_LENGTH; prefixIndex++) {
            //Changed text.substring to result.substring so it randomly generates next k+1 letter based on the result b
            //eing generated instead of the original text
            result += t.choose(result.substring(prefixIndex, prefixIndex + k));
        }
       System.out.println(result);
   }

    /**
     * Reads input, builds table, and generates a random text
     * @param args integer used in a level-k analysis(looks at k characters at a time)
     */
    public static void main(String[] args) {
        Assert.pre(args.length == 1, "There must be atleast one argument (level of analysis) and the file input stream.");
        try {
            WordGen wordGenerator = new WordGen();
            String text = wordGenerator.readFromFile();

            int inititalTextLength = text.length();
            int k = Integer.parseInt(args[0]);
            Assert.condition(k > 0, "Invalid input, argument needs to be a positive integer.");
            //Padding the text with the first k characters:
            String newString = text + text.substring(0, k);
            //Creating a new table of k characters and the following character:
            Table table = new Table();
            table = wordGenerator.generateTable(inititalTextLength, k, newString, table);

            //Generating a new random text with a similar probablity as the freqeuency of character sequences.
            wordGenerator.useTable(k, newString, table, text);
        } catch (Exception e) {
            System.out.println("Invalid input, argument needs to be a positive integer, or file doesn't exist.");
        }
     }
}