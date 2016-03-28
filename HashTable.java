/*
 * This class defines a Hash Table which stores Words within.
 * @author Joe Jensen
 * 
 */ 

// load factor if greater or equal to 1, make new hash table length = original lenght *3/2 + 1
// keep track of find average chain length

public class HashTable
{
  /**
   * This Word Array stores the Word Linked Lists of the hash table
   */ 
  private Word [] myHash; 
  
  /**
   * This int stores the number of unique Words in the hash table
   */ 
  private int numItems;
  
  /**
   * This int stores the load factor of the hash table
   */ 
  private int loadFactor; // number of words in table / hash array length
  
  /**
   * This method initializes all of the HashTable fields
   */ 
  public HashTable()
  {
    myHash = new Word [64];
    numItems = 0;
    loadFactor = numItems / myHash.length;
  }
  
  /**
   * This method returns the number of items in the hash table
   * @return int the number of items in the hash table
   */ 
  public int getNumItems()
  {
    return numItems;
  }
  
  /**
   * This method returns the size of the hash table array
   * @return int the size of the hash table array
   */ 
  public int returnSize()
  {
    return myHash.length;
  }
  
  /**
   * This method adds a given String into the hash table
   * @param String the inputted value for the table
   */ 
  public void add(String str) 
  {
    if (loadFactor >= 1)
    {
      reSize();
    }
    int hash = Math.abs(str.hashCode() % myHash.length);
    Word temp = new Word(str, null, 1);
    boolean entered = false;
    if (myHash[hash] == null && !entered) // if hash spot is empty, add word there
    {
      myHash[hash] = temp;
      entered = true;
      numItems++;
      loadFactor = numItems / myHash.length;
    }
    else if (!entered) // add to end of linked list or increment already placed word
    {
      Word spot = myHash[hash];
      while(spot!=null && !entered) // loop through linked list at array spot
      {
        if (spot.getValue().equals(str) && !entered) // if word is already there, increment
        {
          spot.setOccurances(spot.getOccurances()+1);
          entered = true;
        }
        spot = spot.getNext();
      }
      if (!entered) // if looped through and item wasn't found, add to end of linked list
      {
        spot = temp;
        entered = true;
        numItems++;
        loadFactor = numItems / myHash.length;
      }
    }
  }
  
  /**
   * This method resizes the hash table when the load factor becomes greater than or equal to 1
   */ 
  public void reSize() // resizes array and reinputs all values
  {
    // there won't be duplicates when resizing so don't have to worry about checking and incrementing
    int newSize = (myHash.length * 3) / 2 + 1;
    Word [] temp = new Word [newSize];
    int hash = 0;
    for (int i = 0; i < myHash.length; i++) // loops through myHash table
    {
      Word spot = myHash[i];
      if (spot!=null)
      {
        while (spot.getNext()!=null) // loop through chain
        {
          // add element to temp
          hash = Math.abs(spot.getValue().hashCode() % newSize);
          if (temp[hash] == null) // if spot in table is empty, place word there
          {
            temp[hash] = new Word(spot.getValue(), null, spot.getOccurances());
          }
          else // else add to end of linked list at that spot
          {
            Word tSpot = temp[hash];
            while (tSpot.getNext() != null)
            {
              tSpot = tSpot.getNext();
            }
            tSpot.setNext(new Word(spot.getValue(), null, spot.getOccurances()));
          }
          spot = spot.getNext();
        }
        // add last element to temp
        hash = Math.abs(spot.getValue().hashCode() % newSize);
        if (temp[hash] == null) // if spot in table is empty, place word there
        {
          temp[hash] = new Word(spot.getValue(), null, spot.getOccurances());
        }
        else // else add to end of linked list at that spot
        {
          Word tSpot = temp[hash];
          while (tSpot.getNext() != null)
          {
            tSpot = tSpot.getNext();
          }
          tSpot.setNext(new Word(spot.getValue(), null, spot.getOccurances()));
        }
      }
    }
    myHash = temp;
  }
  
  /**
   * This method calculates the average length of collision linked lists in the hash table
   * @return double the average collision list length
   */ 
  public double collisionAvg()
  {
    int filledSpots = 0;
    for (int i = 0; i < myHash.length; i++)
    {
      if (myHash[i] != null)
        filledSpots++;
    }
    return (double)numItems / filledSpots;
  }
  
  /**
   * This method displays the values of the hash table
   * @return String the output with the values and occurences
   */ 
  public String display()
  {
    String output = "";
    for (int i = 0; i < myHash.length; i++)
    {
      if (myHash[i] != null)
      {
        Word spot = myHash[i];
        while (spot != null)
        {
          output += "(" + spot.getValue() + " " + spot.getOccurances() + ")";
          spot = spot.getNext();
        }
        output += " ";
      }
    }
    return output;
  }
}