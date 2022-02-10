// --== CS400 File Header Information ==--
// Name: Jack Abraham
// Email: jtabraham@wisc.edu
// Team: HC
// TA: Na Li
// Lecturer: Florian
// Notes to Grader: <optional extra notes>

/**
 * This class models a PairNode object with different access methods and a setter
 * @param key - key associated with pairnode
 * @param value - value associated with pairnode
 * @param linked - a node that is linked to another node in the case of collision
 * @author Jack Abraham
 */
public class PairNode<KeyType, ValueType> {

	private KeyType key;
	private ValueType value;
	private PairNode<KeyType, ValueType> linked;
	
	/**
	 * Constructs the PairNode and sets key and value
	 * @param key - key associated with pairnode
	 * @param value - value associated with pairnode
	 * @param linked - set to null initially as there are no links currently
	 */
	public PairNode(KeyType key, ValueType value) {
		this.key = key;
		this.value = value;
		this.linked = null;
	}
	
	/**
	 * Gets the key of the pair
	 * @return key - key of pairnode
	 */
	public KeyType getKey() {
		return key;
	}
	
	/**
	 * Gets the value of the pair
	 * @return value - value of pairnode
	 */
	public ValueType getValue() {
		return value;
	}
	
	/**
	 * Gets the linked pairnode of the pairnode
	 * @return linked - node that is linked to this node
	 */
	public PairNode<KeyType, ValueType> getLinked() {
		return linked;
	}
	
	/**
	 * Sets the link to the node
	 */
	public void setLink(PairNode<KeyType, ValueType> newLink) {
		this.linked = newLink;
	}
	
	/**
	 * Checks for a link in a node
	 * @return true if there is a link, false if not
	 */
	public boolean hasLink() {
		if (linked == null) return false;
		else return true;
	}
}
