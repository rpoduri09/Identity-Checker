// Name: Isaac Hu
// Email: iyhu@wisc.edu
// Team: HA
// TA: Na Li
// Lecturer: Gary Dahl
// Notes to Grader: N/A

package IdentityChecker;

import java.util.LinkedList;
import java.util.NoSuchElementException;

public class HashTableMap<KeyType, ValueType> implements MapADT<KeyType, ValueType> {
  
  private int capacity;
  private int size;
  private LinkedList<Pair<KeyType, ValueType>>[] hashTable;
  
  public HashTableMap() {
    this(10);
  }
  
  @SuppressWarnings("unchecked")
  public HashTableMap(int capacity) {
    this.size = 0;
    this.capacity = capacity;
    hashTable = new LinkedList[capacity];
  }

  @Override
  public boolean put(KeyType key, ValueType value) {
    
    // If the key already exists, return false without doing anything
    if(findKey(key) != -1)
      return false;
    
    // Insert the item into the correct LinkedList, making one if necessary
    int index = hash(key);
    Pair<KeyType, ValueType> item = new Pair<KeyType, ValueType>(key, value);
    if(hashTable[index] == null) {
      hashTable[index] = new LinkedList<Pair<KeyType, ValueType>>();
    }
    else {
      
      // Set the previously last item's next reference if possible/necessary
      hashTable[index].getLast().setNext(item);
    }
    hashTable[index].add(item);
    
    // Increment size, then grow the array if the capacity exceeds the limit of 0.8
    size++;
    if((double) size / (double) capacity >= 0.8)
      grow();
    return true;
  }
  
  // Doubles the array size, and rehashes all items it contained
  public void grow() {
    
    // Increase capacity twofold
    capacity = capacity * 2;
    
    // Store old hash table in a separate variable, then clear the old reference
    LinkedList<Pair<KeyType, ValueType>>[] oldTable = hashTable;
    clear();
    
    // Transfer each item to the new hash table
    for(LinkedList<Pair<KeyType, ValueType>> pairList : oldTable) {
      if(pairList != null) {
        for(Pair<KeyType, ValueType> pair : pairList)
          put(pair.getKey(), pair.getValue());
      }
    }
  }

  // Attempts to fetch the ValueType specified by key, throwing a NoSuchElementException if the
  // key does not exist in the hash table
  @Override
  public ValueType get(KeyType key) throws NoSuchElementException {
    
    // Attempt to locate the specified item
    int location = findKey(key);
    if(location == -1)
      throw new NoSuchElementException("The specified key does not exist");
    
    // Return the ValueType associated with the key
    int index = hash(key);
    Pair<KeyType, ValueType> item = hashTable[index].getFirst();
    boolean first = true;
    do {
      if(!first) item = item.getNext();
      if(item.getKey().equals(key)) return item.getValue();
      first = false;
    } while(item.hasNext());
    
    // Should never happen, but throw exception if item was found by findKey, but was not found
    throw new NoSuchElementException("The specified key does not exist");
  }

  // Returns the current size of the array
  @Override
  public int size() {
    return size;
  }
  
  // Returns the current capacity of the array
  public int getCapacity() {
    return capacity;
  }

  // Checks whether or not the specified key exists in the hash table
  @Override
  public boolean containsKey(KeyType key) {
    return findKey(key) != -1;
  }
  
  // Finds the index of a key in the hash table, returning -1 if it cannot be found
  public int findKey(KeyType key) {
    
    // Locate index of array it should be at
    int index = hash(key);
    if(hashTable[index] != null) {
      
      // Loop through the LinkedList
      Pair<KeyType, ValueType> item = hashTable[index].getFirst();
      int counter = 0;
      do {
        if(counter != 0) item = item.getNext();
        if(item.getKey().equals(key)) return counter;
        counter++;
      } while(item.hasNext());
    }
    return -1;
  }

  // Removes an item from the hash table by key, returning it, or null if it was not found
  @Override
  public ValueType remove(KeyType key) {
    
    // Attempt to locate the specified item
    int location = findKey(key);
    if(location == -1) return null;
    
    // Decrement size and remove/return the item
    size--;
    int index = hash(key);
    Pair<KeyType, ValueType> item = hashTable[index].getFirst();
    
    // If it is the first item, simply remove and return it
    if(item.getKey().equals(key)) {
      hashTable[index].remove(item);
      return item.getValue();
    }
    
    // If it is not the first item, the next references must be fixed before returning
    Pair<KeyType, ValueType> ret = hashTable[index].getFirst();
    while(item.hasNext()) {
      if(item.getNext().getKey().equals(key)) {
        ret = item.getNext();
        item.setNext(item.getNext().getNext());
        hashTable[index].remove(ret);
        return ret.getValue();
      }
      item = item.getNext();
    }
    
    return null;
  }

  // Removes all items in the hash table, and sets size to 0
  @SuppressWarnings("unchecked")
  @Override
  public void clear() {
    hashTable = new LinkedList[capacity];
    size = 0;
    
  }
  
  // Applys a modulus to the key's hashcode and makes sure it is positive
  public int hash(KeyType key) {
    return key.hashCode() < 0 ? ((0 - key.hashCode()) % capacity) : (key.hashCode() % capacity);
  }
}
