import structure5.*;
import java.util.Iterator;
import java.util.Random;

/**
 * A FrequencyTable stores a set of characters each of which has
 * an associated integer frequency
 */
public class FrequencyTable {
  protected Hashtable<Character, Integer>  hashTable;
  protected int sampleSpace;
  /** Construct an empty FrequencyTable */
  public FrequencyTable() {
    hashTable = new Hashtable<>();
    sampleSpace = 0;

  }

  /** add(char ch)
   * If ch is in the FrequencyTable, increment its associated frequency
   * Otherwise add ch to FrequencyTable with frequency 1
   * @param ch is the String to add to the FrequencyTable
   */
  public void add(char ch) {
    if (hashTable.containsKey(ch)) {
      hashTable.put(ch, hashTable.get(ch) + 1);
    } else {
      hashTable.put(ch, 1);
    }
    sampleSpace++;
  }

  /** Selects a character from this FrequencyTable with probabality equal to its relative frequency.
   * @return a character from the FrequencyTable
   */
  public char choose() {
    //Iterates through all of the letters in the frequency table
    //Randomly generates a letter proportional to its frequency
    Random r = new Random();
    int i = r.nextInt(sampleSpace) + 1;
    int cummulativeFrequency = 0;
    Iterator<Character> kIterator = hashTable.keys();
    while (kIterator.hasNext()) {
      char key = kIterator.next();
      cummulativeFrequency += hashTable.get(key);
      if (cummulativeFrequency >= i) {
        return key;
      }
    }
    return 'X';
  }

  /** Produce a string representation of the FrequencyTable
   * @return a String representing the FrequencyTable
   */
  public String toString() {
    //Iterates through the letters in the Frequency Table
    //Prints the character and the values(Frequency) associated with it
    Iterator<Character> kIterator = hashTable.keys();
    String result = "";
    while (kIterator.hasNext()) {
      char key = kIterator.next();
      result += "[ " + key + ", " + hashTable.get(key) + " ] \n";
    }
    return result;
  }
  /**
   * // Use main to test your FrequencyTable class
   * @param args
   */
  public static void main(String[] args) {
    // FrequencyTable freq = new FrequencyTable();
    // freq.add('a');
    // freq.add('b');
    // freq.add('a');

    // System.out.println(freq.toString());
    // System.out.println(freq.choose());
  }
}
