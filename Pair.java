package IdentityChecker;

public class Pair<KeyType, ValueType> {
  private KeyType key;
  private ValueType value;
  private Pair<KeyType, ValueType> next;
  
  public Pair(KeyType key, ValueType value) {
    this.key = key;
    this.value = value;
  }
  
  public KeyType getKey() {
    return key;
  }
  
  public ValueType getValue() {
    return value;
  }
  
  public void setNext(Pair<KeyType, ValueType> next) {
    this.next = next;
  }
  
  public Pair<KeyType, ValueType> getNext() {
    return next;
  }
  
  public boolean hasNext() {
    return next != null;
  }
}
