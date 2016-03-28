/**
 * This class inputs a text file and creates a hash table,
 * then outputs the data of the table and creates a text file with the words and their occurences.
 * @author Joe Jensen
 * 
 */ 
import java.util.*;
import java.io.*;

public class WordCounter
{
  /**
   * This HashTable will store all the Words from the input text file
   */ 
  private static HashTable myHashTable;
  
  public static void main (String args []) throws IOException
  {
    System.out.println(wordCount(args[0],args[1]));
  }
  
  /**
   * This method calls all helper methods to implement the function of the class
   * @param String the input file name
   * @param String the output file name
   * @return String the outcome of the method and statistics of the hash table
   */ 
  public static String wordCount(String input_file, String output_file) throws IOException
  {
    myHashTable = new HashTable();
    try
    {
      
      String myFile = readFile(input_file); // reads input file and creates a large string (works)
      String myChangedFile = changeString(myFile); // changes myFile into all lower case and spaces (works)
      addToHash(myChangedFile);
      String output = myHashTable.display();
      makeFile(output_file, output);
    }
    catch(FileNotFoundException x)
    {
      return "File Not Found";
    }
    return "OK" + 
      "\nTotal Words: " + myHashTable.getNumItems() + 
      "\nHashTable Size: " + myHashTable.returnSize() + 
      "\nAverage Length of Collision Lists: " + myHashTable.collisionAvg(); 
  }    
  
  /**
   * This method converts the text file String into all lower case characters and spaces only
   * @param String the inputted String to change
   * @return String the changed String
   */ 
  public static String changeString(String word) // will change input file string from what it is to all lower case letters and spaces if not a character
  {
    String changed = "";
    for (int i = 0; i < word.length(); i++)
    {
      Character c = word.charAt(i);
      if (Character.isUpperCase(c) || Character.isLowerCase(c))
      {
        changed += Character.toLowerCase(c);
      }
      else
        changed += " ";
    }
    return changed;
  }
  
  /**
   * This method adds all of the words from the changed String into the hash table
   * @param String the inputted String to add in
   */ 
  public static void addToHash(String file)
  {
    String temp = ""; // temp String that will be eventually added to hash table multiple times
    String sub = ""; // substring always of length 1 that gets added to temp
    for (int i = 0; i < file.length(); i++)
    {
      sub = file.substring(i,i+1);
      if (sub.equals(" ") && temp.length() > 0) // if space, add what was in temp to hash table if temp isn't empty
      {
        myHashTable.add(temp);
        temp = ""; // make temp empty after adding word
      }
      else if (!sub.equals(" ")) // if it's a character, concatanate that onto the temp String
      {
        temp += sub;
      }
    }
  }
  
  /**
   * Reads the input file and creates a String
   * @param String the input file name
   * @return String the created string of the text file
   */ 
  public static String readFile(String inputFileName) throws IOException
  {
    String output = "";
    FileReader in = new FileReader(inputFileName);
    BufferedReader br = new BufferedReader(in);
    String myLine = br.readLine();
    while (myLine != null) 
    {
      output += myLine + "\n"; // adds each line into the output String
      myLine = br.readLine(); // increments
    }
    in.close();
    return output;
  }
  
  /**
   * This method makes a file with a given file name and String
   * @param String the desired name of the created file
   * @param String the String to be placed in the file
   */ 
  public static void makeFile(String fileName, String text) throws IOException
  {
    File outFile = new File(fileName);
    PrintStream stream = new PrintStream(outFile);
    for (int i = 0; i < text.length(); i++)
    {
      if (text.charAt(i) == '\n')
        stream.println();
      else
        stream.print(text.charAt(i));
    }
    stream.close();
  }
}
