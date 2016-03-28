/*
 * This class defines a Word object that will be used and stored in a hash table.
 * It stores the String and number of occurences in the text file.
 * @author Joe Jensen
 * 
 */ 

public class Word
{
  /**
   * This String is the value or word stored in the Word object
   */ 
  private String value;
  /**
   * This Word is the next Word in the collision linked list
   */ 
  private Word next;
  /**
   * This int is the number of times a word occurs in the text file
   */ 
  private int occurances;
  
  /**
   * This method initializes all Word fields
   */ 
  public Word(String myValue, Word myNext, int occ)
  {
    value = myValue;
    next = myNext;
    occurances = occ;
  }
  
  /**
   * This method returns the value of the Word
   * @return String the Word's value
   */ 
  public String getValue()
  {
    return value;
  }
  
  /**
   * This method sets the value of the Word
   * @param String the intended value of the Word
   */
  public void setValue(String s)
  {
    value = s;
  }
  
  /**
   * This method returns the next Word of the current Word
   * @return Word the next Word of the current Word
   */ 
  public Word getNext()
  {
    return next;
  }
  
  /**
   * This method sets the next Word of the current Word
   * @param Word the next Word to be set
   */ 
  public void setNext(Word n)
  {
    next = n;
  }
  
  /**
   * This method returns the number of occurences of the Word
   * @return int the number of occurences of the Word
   */ 
  public int getOccurances()
  {
    return occurances;
  }
  
  /**
   * This method sets the number of occurences of the Word
   * @param int the number to be set for occurances
   */ 
  public void setOccurances(int occ)
  {
    occurances = occ;
  }
}