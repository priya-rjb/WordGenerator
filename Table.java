import java.util.Iterator;
import structure5.*;
/**
* A Table holds a collection of strings, each of which has an
* associated FrequencyTable
*/
public class Table {
  protected Hashtable<String, FrequencyTable>  mainTable;
  /** Construct an empty table */
  public Table() {
    mainTable = new Hashtable<>();
  }

  /**
  * Updates the table as follows
  * If key already exists in the table, update its FrequencyTable
  * by adding value to it
  * Otherwise, create a FrequencyTable for key and add value to it
  * @param key is the desired k-letter sequence
  * @param value is the character to add to the FrequencyTable of key
  */
  public void add(String key, char value) {
    if (!mainTable.containsKey(key)) {
      mainTable.put(key, new FrequencyTable());
    }
    mainTable.get(key).add(value);

  }

  /**
  * If key is in the table, return one of the characters from
  * its FrequencyTable with probability equal to its relative frequency
  * Otherwise, determine a reasonable value to return
  * @param key is the k-letter sequence whose frequencies we want to use
  * @return a character selected from the corresponding FrequencyTable
  */
  public char choose(String key) {
    if (!mainTable.containsKey(key)) {
      System.exit(1);
    }
    return mainTable.get(key).choose();
  }
  /** Produce a string representation of the Table
  * @return a String representing this Table
  */
  public String toString() {
    Iterator<String> kIterator = mainTable.keys();
    String result = "";
    while (kIterator.hasNext()) {
      String prefixKey = kIterator.next();
      result += "[ " + prefixKey + ", " + mainTable.get(prefixKey).toString() + " ] \n";
    }
    return result;
  }
  /**
   * // Use main to test your Table class
   * @param args
   */
  public static void main(String[] args) {
    // Table t = new Table();
    // t.add("pri", 'w');
    // t.add("pri", 'a');
    // t.add("pri", 'w');
    
    // System.out.println(t.toString());
    // System.out.println(t.choose("pri"));
  }

}
