import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

// binary search tree storing integers
public class IntBST extends NodeBinaryTree<Integer> {

	private int size = 0;
	
	public IntBST() {	}

	public int size() {
		return size;
	}

	public void setSize(int s) { size = s; }
	
	public boolean isEmpty() {
		return size() == 0;
	}

	/**
	 * Places element e at the root of an empty tree and returns its new Node.
	 *
	 * @param e the new element
	 * @return the Node of the new element
	 * @throws IllegalStateException if the tree is not empty
	 */

	public Node<Integer> addRoot(Integer e) throws IllegalStateException {
		if (size != 0)
			throw new IllegalStateException("Tree is not empty");
		root = createNode(e, null, null, null);
		size = 1;
		return root;
	}

	/**
	 * Print a binary tree horizontally Modified version of
	 * https://stackoverflow.com/questions/4965335/how-to-print-binary-tree-diagram
	 * Modified by Keith Gutfreund
	 * 
	 * @param n Node in tree to start printing from
	 */
	
	  public void print(Node<Integer> n){ print ("", n); }
	  
	  public void print(String prefix, Node<Integer> n){
		  if (n != null){
			  print(prefix + "       ", right(n));
			  System.out.println (prefix + ("|-- ") + n.getElement());
			  print(prefix + "       ", left(n));
		  }
	  }
	  
	  
	  public void inorderPrint(Node<Integer> n) {
		if (n == null)
			return;
//		Node<Integer> n = validate(p);
		inorderPrint(n.getLeft());
		System.out.print(n.getElement() + "  ");
		inorderPrint(n.getRight());
	}

	
	public Iterable<Node<Integer>> children(Node<Integer> n) {
		List<Node<Integer>> snapshot = new ArrayList<>(2); // max capacity of 2 
		if (left(n) != null) 
			snapshot.add(left(n)); 
		if (right(n) != null)
			snapshot.add(right(n)); return snapshot; 
	}
	
	public int height(Node<Integer> n) throws IllegalArgumentException { 
		if (isExternal(n)) { return 0; } 
		int h = 0; // base case if p is external
		for (Node<Integer> c : children(n)) h = Math.max(h, height(c)); return h + 1; 
	}


	// Receives an array of integers and creates a binary search tree
	// Input: an array of integers, a; size of a is 2^k - 1, k = 1, 2, . . .
	//        integers in the array are sorted in non-decreasing order
	// Output: an IntBST, which is a binary search tree with all integers in the array a
	//        
	public static IntBST makeBinaryTree (int[] a) {
		IntBST bTree = new IntBST();
		if (null == a || a.length <= 0) {
			return bTree;
		} else {
			return makeBinaryTree(a, 0, a.length - 1);
		}
		// complete this method	
	}

	public static IntBST makeBinaryTree (int[] a, int nStart, int nEnd) {
		IntBST bTree = new IntBST();
		if (nEnd <= nStart) {
	  		bTree.addRoot(a[nStart]);
		} else {
			int nRootPos = nStart + (nEnd - nStart)/2;

	  		Integer nRoot = a[nRootPos];

	  		bTree.addRoot(nRoot);

			IntBST bTreeLeft = makeBinaryTree(a, nStart, nRootPos - 1);
			IntBST bTreeRight = makeBinaryTree(a, nRootPos + 1, nEnd);
	  		bTree.attach(bTree.root, bTreeLeft, bTreeRight);
	  		bTree.size += (bTreeLeft.size + bTreeRight.size);
		}

		return bTree;
	}

}
