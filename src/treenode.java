import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Stack;


//import Digraph.State;

/**
 * BSTnode implements a node for a binary search tree (BST).  Each BSTnode 
 * keeps track of a key and the value associated with that key.
 * 
 * DO NOT CHANGE THIS FILE
 * 
 * @author CS 367
 * @param <K> class representing the key, should implement the Comparable<K> 
 *            interface 
 * @param <V> class representing the value
 */
class treenode<K> {


	// Data members
	private K key;                      // the key for this node
	private  treenode <K>left, right;  // the left and right children
	/**
	 * Constructs a BST node with the given key and value and whose left and 
	 * right children are null.
	 * @param key
	 * @param value
	 */
	public treenode(K key) {
		this(key, null, null);

	}

	/**
	 * Constructs a BST node with the given key, value, left child, and right 
	 * child.
	 * @param key the key value
	 * @param leftChild the left child
	 * @param rightChild the right child
	 */
	public treenode(K key, treenode<K> leftChild, 
			treenode<K> rightChild) {
		this.key = key;
		left = leftChild;
		right = rightChild;
	}

	/**
	 * Returns the key for this BST node.
	 * @return the key
	 */
	public K getKey() { 
		return key; 
	}

	/**
	 * Returns the value for this BST node
	 * @return the value
	 */


	/**
	 * Returns the left child for this BST node.
	 * @return the left child
	 */
	public treenode<K> getLeft() { 
		return left; 
	}

	/**
	 * Returns the right child for this BST node.
	 * @return the right child
	 */
	public treenode<K> getRight() { 
		return right; 
	}

	/**
	 * Changes the key for this node to the one given.
	 * @param newK the new key  
	 */


	/**
	 * Changes the value for this node to the one given.
	 * @param newV the new value
	 */


	/**
	 * Changes the left child for this node to the one given.
	 * @param newL the new left child
	 */
	public void setLeft(treenode<K> newL) { 
		left = newL; 
		ArrayList<String> hih = new ArrayList<String> ();
		Stack<Integer> stt = new Stack<Integer>();
		stt.push(item)
	}

	/**
	 * Changes the right child for this node to the one given.
	 * @param newR the new right child
	 */
	public void setRight(treenode<K> newR) { 
		right = newR; 
	}

	public static void main (String [] args) throws DuplicateException
	{
		treenode<Integer> root = new treenode<Integer> (24);
		root.setLeft(new treenode<Integer> (-60)); root.getLeft().setLeft(new treenode<Integer> (-60)); root.getLeft().setRight(new treenode<Integer> (-6));
		if( !isValidBST(root))
		{
			System.out.println("it is a BST tree");
		}
	} 
	public static boolean isValidBST(treenode<Integer>root) {
		if ( root == null)
		{
			return true;
		}
		else
		{
			boolean isB1 = isValidBST(root.getLeft());
			boolean isB2 = isValidBST(root.getRight());
			if(root.getLeft() == null && root.getRight() == null)
			{
				return true;
			}
			else if(root.getLeft() == null && root.getRight() != null)
			{
				return    root.getKey()<minVal(root.getRight(),0) && isB2;
			}
			else if ( root.getRight() == null && root.getLeft() != null)
			{
				return root.getKey()>minVal(root.getLeft(),1)&&isB1 ;
			}
			else
			{
				if( root.getKey() == -60 && root.getLeft().getKey() == -60)
				{
					int minVal = minVal(root.getLeft(),1);
					if( minVal < root.getKey())
						System.out.println("found it" + minVal);
				}
				return isB1&&isB2&& root.getKey()<minVal(root.getRight(),0) && root.getKey()>minVal(root.getLeft(),1) ; 
			}

		}
	}
	public static int minVal(treenode<Integer>root, int minMax)
	{  
		if(minMax == 0)
		{
			while ( root.getLeft() != null)
			{
				root = root.getLeft();
			}
			return root.getKey();
		}
		else
		{
			while ( root.getRight() != null)
			{
				root = root.getRight();
			}
			return root.getKey();
		}
	}

}

