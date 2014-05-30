import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

public class BSTBasicMap<K extends Comparable<K>, V> 
{
	public BSTnode<K, V> root;  // the root node
	public int numItems;        // the number of items in the basic map

	public BSTBasicMap ( )
	{
		root = null ;
		numItems =0 ;
	}

	// TO DO:
	// Add a no-argument constructor
	// Add your code to implement the BasicMap ADT operations using a binary
	// search tree.
	// You may use any code given in the on-line reading on BSTs.


	/**
	 * Adds the given (key, value) pair to the Basic Map if the key is not
	 * already in the Basic Map.  If the key is already in the Basic Map, a 
	 * DuplicateKeyException is thrown.
	 * @param key the key to insert into the Basic Map
	 * @param value the value associated with the key
	 * @throws DuplicateException if the key is already in the Basic Map
	 * @throws NullPointerException if the key or value is null
	 */
	public void put(K key, V value) throws DuplicateException {
		// TODO Auto-generated method stub

		this.root = insert( key , value,root);

	}

	private BSTnode<K,V> insert( K key ,V value ,BSTnode<K,V> n ) throws DuplicateException
	{
		if ( key == null || value == null)
		{
			throw new NullPointerException ();
		}

		if ( n == null)
		{
			this.numItems ++ ;
			return new BSTnode<K,V> ( key , value  );

		}

		if ( n.getKey().equals(key))
		{
			throw new DuplicateException ();
		}

		if (n.getKey().compareTo(key) < 0  )
		{
			n.setRight(insert( key,value,n.getRight()));
			return n ;
		}
		else 
		{
			n.setLeft(insert ( key ,value,n.getLeft()));
			return n ;
		}


	}


	/**
	 * Removes the (key, value) pair from the Basic Map that corresponds with 
	 * the given key.  If the key is in the Basic Map, the (key, value) pair 
	 * is deleted and true is returned.  If the key is not in the Basic Map, 
	 * the Basic Map is unchanged and false is returned. 
	 * @param key the key for the (key, value) pair to delete from the Basic 
	 *        Map
	 * @return true if the removal is successful (i.e., the key was in the 
	 *         Basic Map and has been removed), false otherwise (i.e., the key 
	 *         was not in the Basic Map)
	 * @throws NullPointerException if the key is null
	 */
	public boolean remove(K key) {
		// TODO Auto-generated method stub
		root = remove (  this.root  , key);
		if ( this.containsKey(key))
		{
			this.numItems-- ;
			return true ;
		}
		return false ;
	}   

	private BSTnode< K , V > remove( BSTnode <K, V> n , K key )
	{
		if (n == null) {
			return null;
		}

		if (key.equals(n.getKey())) {
			// n is the node to be removed
			if (n.getLeft() == null && n.getRight() == null) {
				return null;
			}
			if (n.getLeft() == null) {
				return n.getRight();
			}
			if (n.getRight() == null) {
				return n.getLeft();
			}

			// if we get here, then n has 2 children
			K smallVal = smallest(n.getRight());
			n.setKey(smallVal);
			n.setRight( remove(n.getRight(), smallVal) );
			return n; 
		}

		else if (key.compareTo(n.getKey()) < 0) {
			n.setLeft( remove(n.getLeft(), key) );
			return n;
		}

		else {
			n.setRight( remove(n.getRight(), key) );
			return n;
		}

	}


	private K smallest ( BSTnode<K, V> n )
	{
		if ( n.getLeft() == null)
		{
			return n.getKey() ;
		}
		else
		{ 	
			return smallest ( n.getLeft ());
		}
	}
	/**
	 * Searches for the given key in the Basic Map and returns the value 
	 * associated with it.  If the key is not in the Basic Map, null is 
	 * returned.
	 * @param key the key to search for
	 * @return the value from the Basic Map corresponding to the key, if the
	 *         key is in the Basic Map; null if the key is not in the Basic Map
	 * @throws NullPointerException if the key is null
	 */

	public V get(K key) {
		// TODO Auto-generated method stub
		if ( key == null  )
		{
			throw new  NullPointerException(); 
		}

		if ( this.containsKey(key))
		{
			return getnode( key , this.root).getValue();
		}

		return null;
	}

	private BSTnode<K, V> getnode ( K key, BSTnode<K,V> n )
	{
		if ( n.getKey().equals(key))
		{
			return n;
		}

		else if ( n.getKey().compareTo(key) > 0)
		{
			return getnode ( key, n.getLeft() );
		}

		return getnode (  key , n.getRight());
	}


	public boolean containsKey(K key) {
		// TODO Auto-generated method stub
		return containskey ( key , root);

		//  return false;
	}

	private boolean containskey ( K key ,BSTnode<K,V> n)
	{
		if ( n == null )
		{
			return false;
		}
		else if ( n.getKey().equals(key))
		{
			return true;
		}
		else if ( n.getKey().compareTo(key)< 0)
		{
			return containskey ( key, n.getRight());
		}
		else 
		{
			return containskey(key,n.getLeft());
		}


	}

	/**
	 * Returns true if and only if the given value is in the Basic Map.
	 * More specifically, returns true if and only if there is at least one
	 * key k in the Basic Map whose associated value is v such that 
	 * value.equals(v)
	 * @param value the value to look for
	 * @return true if and only if the given value is in the Basic Map
	 * @throws NullPointerException if the value is null
	 */
	public boolean containsValue(V value) {
		// TODO Auto-generated method stub
		if ( value == null )
		{
			throw new NullPointerException ();
		}
		return containsValue ( value , this.root );


	}

	private boolean containsValue ( V value , BSTnode<K, V > n)
	{
		// base-case 
		if ( n == null )
		{
			return false ;
		}
		if (n.getValue().equals(value))
		{
			return true ;
		}

		else 
		{      	

			return containsValue ( value , n.getLeft()) || containsValue ( value ,n.getRight());

		}

	}

	/**
	 * Returns the number of (key, value) pairs in the Basic Map.
	 * @return the number of (key, value) pairs in the Basic Map
	 */
	public int size() {
		// TODO Auto-generated method stub
		return this.numItems ;

	}

	/**
	 * Returns true if and only if the Basic Map is empty.
	 * @return true if the Basic Map is empty, false otherwise
	 */
	public boolean isEmpty() {
		// TODO Auto-generated method stub


		return this.root == null ;
	}


	/**
	 * Returns a list of the keys in the Basic Map in order from smallest to
	 * largest.
	 * @return a list of the keys in the Basic Map in order
	 */
	public List<K> keys() {
		// TODO Auto-generated method stub
		List<K> collection = new ArrayList <K> ();

		addVal ( collection , root ) ;

		return collection ;
	}
	private void addVal ( List<K> collection , BSTnode<K, V> n)
	{
		if ( n == null  )
		{
			return ;
		}
		addVal ( collection , n.getLeft());

		collection.add(n.getKey());

		addVal ( collection , n.getRight());
	}


	/**
	 * Returns a list of the values in the Basic Map in order of the associated
	 * keys from smallest to largest.
	 * @return a list of the values in the Basic Map in key order
	 */
	public List<V> values() {
		// TODO Auto-generated method stub
		List < K >  temp = this.keys();
		List < V > collection = new ArrayList < V > ();
		for ( K ite : temp)
		{
			collection.add(this.get(ite));
		}

		return collection;
	}


	/**
	 * Returns the total path length.  The total path length is the sum of the 
	 * lengths of the paths to each (key, value) pair.
	 * @return the total path length
	 */
	public int totalPathLength() {
		// TODO Auto-generated method stub

		return this.totalPathLength ( root , 0 );

	}

	private int totalPathLength (BSTnode<K , V > n , int depth )
	{
		depth ++ ;
		if ( n == null)
		{
			return 0 ;
		}

		else 
		{
			return depth + totalPathLength (n.getLeft(),depth ) + totalPathLength ( n.getRight(),depth);
		}
	}

	public static void main (String [] args) throws DuplicateException
	{
		BSTBasicMap<Integer,String> tree = new BSTBasicMap<Integer,String>();
		tree.put(5, "a");tree.put(4, "b");tree.put(6, "c");tree.put(7, "f");tree.put(2, "ag");tree.put(8, "i");//;tree.put(4, "ag");
		Hashtable<Integer,Integer> table = new Hashtable<Integer, Integer> ();
		boolean istr =  isBinarySearch(tree.root);
		if(istr)
		{
			System.out.println("binary");
		}
		//System.out.println(table.get(7));
		ArrayList<LinkedList<BSTnode<Integer, String>>>  t12 = findLinkedList(tree.root);
		for(int i = 0 ; i<t12.size();i++)
		{
			for(BSTnode<Integer, String> node:  t12.get(i))
			{
				System.out.println("value is" + node.getValue());
			}
			System.out.println( );
		}

		if( isValidBST(tree.root))
		{
			System.out.println("it is a valid bst");
		}
	} 
	public static ArrayList<LinkedList<BSTnode<Integer, String>>> findLinkedList(BSTnode<Integer, String> root )
	{
		int depth  = 0 ;
		ArrayList<LinkedList<BSTnode<Integer, String>>> list = new ArrayList<LinkedList<BSTnode<Integer, String>>> ();
		outputLinkedList(root, list,depth);
		return list;
	}

	public static void outputLinkedList ( BSTnode<Integer, String> root,ArrayList<LinkedList<BSTnode<Integer, String>>> table, int depth)
	{
		if( root == null)
		{
			return;
		}
		if(table.size()<= depth)
		{
			LinkedList<BSTnode<Integer, String>> nnn = new LinkedList<BSTnode<Integer,String>>();
			table.add(nnn);
			nnn.add(root);
		}
		else
		{
			table.get(depth).add(root);
		}
		outputLinkedList ( root.getLeft(),table,depth+1);outputLinkedList ( root.getRight(),table,depth+1);

	}

	public static boolean IsBinarySearchTree(BSTnode<Integer, String> root,Hashtable<Integer, Integer> table)
	{

		if(root.getLeft() ==null&&root.getRight() ==null)
		{
			table.put(root.getKey(), 0);
			return true;
		}
		else if(root.getLeft()!=null && root.getRight() ==null)
		{
			boolean iuse = IsBinarySearchTree(root.getLeft(),table);
			boolean  abc = !(table.get(root.getLeft().getKey())>=1);
			table.put(root.getKey(), table.get(root.getLeft().getKey())+1);
			return iuse&&abc;
		}
		else if(root.getLeft()==null && root.getRight() !=null)
		{ 
			boolean iuse = IsBinarySearchTree(root.getRight(),table);
			boolean abc = !(table.get(root.getRight().getKey())>=1) ;

			table.put(root.getKey(), table.get(root.getRight().getKey())+1);
			return iuse&&abc;
		}
		else
		{
			boolean iuse = IsBinarySearchTree(root.getRight(),table) && IsBinarySearchTree(root.getLeft(),table) ;
			boolean abc = !(Math.abs(table.get(root.getRight().getKey())- table.get(root.getLeft().getKey()))>1);

			table.put(root.getKey(), table.get(Math.max(root.getLeft().getKey(), root.getRight().getKey())) +1);
			return iuse&&abc;

		}
	}
	public static boolean isBinarySearch (BSTnode<Integer, String> root )
	{
		int temp;

		if (( temp=calHeight ( root)) == -1)
		{
			System.out.println(temp);

			return false;
		}
		System.out.println(temp);

		return true;

	}

	public static int calHeight(BSTnode<Integer, String> root )
	{
		if ( root == null)
		{
			return 0 ;
		}
		int temp = calHeight(root.getLeft()) ; int temp1 = calHeight(root.getRight()) ;

		if(Math.abs(temp-temp1) > 1 ||  temp== -1 ||temp1==-1)
		{
			return -1 ;
		}
		return (Math.max(temp, temp1)+1);	
	}
	public static boolean isValidBST(BSTnode<Integer, String> root) {
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
				return    root.getKey()<minVal(root.getRight(),0);
			}
			else if ( root.getRight() == null && root.getLeft() != null)
			{
				return root.getKey()>minVal(root.getLeft(),1) ;
			}
			else
			{
				return isB1&&isB2&& root.getKey()<minVal(root.getRight(),0) && root.getKey()>minVal(root.getLeft(),1) ; 
			}

		}
	}
	public static int minVal(BSTnode<Integer,String>root, int minMax)
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

