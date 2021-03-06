// --== CS400 File Header Information ==--
// Name: Jack Abraham
// Email: jtabraham@wisc.edu
// Team: HC
// TA: Na Li
// Lecturer: Florian
// Notes to Grader: <optional extra notes>

import java.util.NoSuchElementException;

/**
 * This class models a HashTable with different functions using an array (pairs) with linked nodes
 * called PairNode
 * @param pairs - an array of PairNode objects that contain Key and value pairs as data
 * @param capacity - the amount of indices (holding space) of the pairs hash array 
 * @param found - a stored PairNode that is searched for and used in get and containsKey methods
 * @param current - a PairNode that is used for searching through linked lists
 * @author Jack Abraham
 */
public class HashTableMap<KeyType, ValueType> implements MapADT<KeyType, ValueType> {


	private PairNode<KeyType, ValueType>[] pairs;
	private int capacity;
	private PairNode<KeyType, ValueType> found;
	private PairNode<KeyType, ValueType> current;
	
	/**
	 * Constructor class that sets the length of the pairs array
	 * @param capacity - number of available spaces in the array
	 * @param pairs - array of PairNodes
	 */
	@SuppressWarnings("unchecked")
	public HashTableMap(int capacity) {
		this.pairs = new PairNode[capacity];
		this.capacity = capacity;
	}
	/**
	 * Constructor class that sets the length of the pairs array to 10 if no specified capacity
	 * @param capacity - number of available spaces in the array
	 * @param pairs - array of PairNodes
	 */
	@SuppressWarnings("unchecked")
	public HashTableMap() {
		pairs = new PairNode[10];
		capacity = 10;
	}


	/**
	 * Puts the PairNode into the pairs hash array at the hash index if it is null, if the space is
	 * already filled, then it adds the PairNode to the spot and links the PairNode that was already
	 * in the spot. If we reach load factor threshold (.8) then resize is called to create space in
	 * the hash table.
	 * @param index - hash index to use, generated by |hashcode| % capacity
	 * @param current - used to iterate through links
	 * @return true if key was added, false if not
	 */
	@Override
	public boolean put(KeyType key, ValueType value) {
		if (containsKey(key)) return false; //if the key is already stored, then we will not add it
		int index = (Math.abs(key.hashCode())%(capacity));
		if (pairs[index] == null) {
			PairNode<KeyType, ValueType> p = new PairNode<KeyType, ValueType> (key, value);
			pairs[index] = p;
		}
		else {
			current = pairs[index];
			while(current.hasLink()) {
				current = current.getLinked();
			}
			PairNode<KeyType, ValueType>  p = new PairNode<KeyType, ValueType> (key, value);
			current.setLink(p);
		}
		if ((size()/(double) capacity) >= .8) {
			resize();
		}
		return true;
	}
	/**
	 * Creates a new hashtable (bigger) that is twice the size of the original, then adds the key
	 * and value pairs back using put (which essentially rehashes). Searches for linked pairs also 
	 * to ensure everything in the hash table is added.
	 * @param bigger - hash table that is twice the capacity of the original table
	 */
	private void resize() {
		HashTableMap<KeyType, ValueType> bigger = new HashTableMap<KeyType, ValueType> (capacity*2);
		for (int i = 0; i< capacity; i++) {
			if (pairs[i] == null) { //if spot is empty, go to next index in for loop
				continue;
			}
			else {
				bigger.put(pairs[i].getKey(), pairs[i].getValue());
				current = pairs[i];
				while (current.hasLink()) {
					current = current.getLinked();
					bigger.put(current.getKey(), current.getValue());
				}
			}
		}
		this.pairs = bigger.pairs;
		this.capacity = bigger.capacity;
	}

	/**
	 * Finds a key in the hash table using containsKey method and a private PairNode (found). If key
	 * is found then returns the Value of the Key. If not, NoSuchElementException is thrown.
	 * @returns found.getValue() - the value associated with the found key
	 */
	@Override
	public ValueType get(KeyType key) throws NoSuchElementException {
		if (!containsKey(key)) throw new NoSuchElementException("key is not in hash table");
		return found.getValue();
	}

	/**
	 * Checks the size of the HashTable. That is the amount of actual PairNodes in the table not the 
	 * capacity. Adds to size for each PairNode. It searches through links to account for all Nodes
	 * @param size - number of PairNodes in array
	 * @param current - PairNode that is used for checking through the linked list
	 * @return size - the number of PairNodes in the pairs array
	 */
	public int size() {
		int size = 0;
		for (int i =0; i< capacity; i++) {
			if (pairs[i] == null) { //if spot is empty, go to next index in for loop
				continue;
			}
			else {
				++size;
				PairNode<KeyType, ValueType> current = pairs[i];
				while (current.hasLink()) {
					++size;
					current = current.getLinked();
				}
			}
		}
		return size;
	}

	/**
	 * Checks the HashTable for a certain key. Iterates through the entire table. It searches 
	 * through links to account for all Nodes. Once key is found, it is set to found variable and
	 * returns true if found.
	 * @param found - private PairNode variable that keeps track of found key-value pairs
	 * @param current - PairNode that is used for checking through the linked list
	 * @return true if found false if not
	 */
	@Override 
	public boolean containsKey(KeyType key) {
		for (int i = 0; i <capacity; i++) {
			if (pairs[i] == null) { //if spot is empty, go to next index in for loop
				continue;
			}
			if (pairs[i].getKey().equals(key)) {
				found = pairs[i];
				return true;
			}
			if (pairs[i].hasLink()) {
				PairNode<KeyType, ValueType> current = pairs[i];
				while (current.hasLink()) {
					current = current.getLinked();
					if (current.getKey().equals(key))  {
						found = current;
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * Removes a key from the hash table by setting the node/spot to null. If the key to remove
	 * has a linked list behind it, then the index will take on the spot of the first linked key.
	 * @param current - PairNode that is used for checking through the linked list and setting the 
	 * newly emptied index
	 * @return removed - null if not found, if removed, it is the Value type of the pair that was 
	 * removed
	 */
	@Override // search linked list
	public ValueType remove(KeyType key) {
		ValueType removed = null;
		for (int i = 0; i <capacity; i++) {
			if (pairs[i] == null) { //if spot is empty, go to next index in for loop
				continue;
			}
			if (pairs[i].getKey().equals(key)) {
				removed = pairs[i].getValue();
				if (pairs[i].hasLink()) {
					current = pairs[i].getLinked();
					pairs[i] = current;
					break;
				}
				pairs[i] = null;
				break;
			}
			if (pairs[i].hasLink()) {
				current = pairs[i];
				while (current.hasLink()) {
					if (current.getKey().equals(key)) {
						removed = current.getValue();
						current = null;
						break;
					}
					current = current.getLinked();
				}
			}
		}
		return removed;
	}

	/**
	 * Sets all values in hashTable to null
	 */
	@Override
	public void clear() {
		for (int i = 0; i<capacity; i++) {
			pairs[i] = null;
		}
		
	}

	
}
