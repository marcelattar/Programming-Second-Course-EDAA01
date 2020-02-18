package bst;

public class BinarySearchTree<E extends Comparable<? super E>> {
	BinaryNode<E> root;
    int size;
    
	/**
	 * Constructs an empty binary searchtree.
	 */
	public BinarySearchTree() {
		this.size = 0;
	}

	/**
	 * Inserts the specified element in the tree if no duplicate exists.
	 * @param x element to be inserted
	 * @return true if the the element was inserted
	 */
	public boolean add(E x) {
		if (root == null) { // Tomt träd.
			root = new BinaryNode(x);
			size++;
			return true;
		}
		return add(x, root);
	}
	private boolean add(E x, BinaryNode<E> n) {
		int comp = x.compareTo(n.element);
		if (comp == 0) {
			return false;
		} else if (comp>0) { // Nu vill jag kolla på höger sida ty inlagda elementet är större än roten.
			if (n.right == null) { // Den högra roten är null. BASFALL
				n.right = new BinaryNode(x);
				size++;
				return true;
			}
			return add(x,n.right); // Rekursion, fortsätter längre ner i trädet.
		} else {
			if (n.left == null) { //BASFALL
				n.left = new BinaryNode(x);
				size++;
				return true;
			}
			return add(x,n.left); // Rekursion
		}
	}
	
	/**
	 * Computes the height of tree.
	 * @return the height of the tree
	 */
	public int height() {
		return height(root);
	}
	
	private int height(BinaryNode<E> n) { // Min rekursiva metod.
		if (n == null) { //BASFALL
			return 0;
		}
		int counter = 1;
		
		if (n.left != null) {
			counter = 1 + height(n.left);
		}
		if (n.right != null) { //Returnerar antingen höjden av vänstra eller högra noden. 
			counter = Math.max(1+height(n.right),counter);
		}
		return counter;
	}
	
	/**
	 * Returns the number of elements in this tree.
	 * @return the number of elements in this tree
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Print tree contents in inorder. Med andra ord vill jag läsa av trädet ifrån vänster, dvs minst först. 
	 */
	public void printTree() {
		if (root != null) {
			StringBuilder sb = new StringBuilder();
			sb = printTree(root, sb);
			
			System.out.println(sb);
		}
	}
	private StringBuilder printTree(BinaryNode<E> n, StringBuilder sb) {
		if (n.left != null) {
			sb = printTree(n.left,sb);
		}
		
		sb.append(n.element + ",");
		
		if (n.right != null) {
			sb = printTree(n.right,sb);
		}
		return sb;
	}
	/** 
	 * Builds a complete tree from the elements in the tree.
	 */
	public void rebuild() {
		E[] a = (E[]) new Comparable[size];
		int index = 0;
		index = toArray(root, a, index);
		this.root = buildTree(a,0,index-1);
		
	}
	
	/*
	 * Adds all elements from the tree rooted at n in inorder to the array a
	 * starting at a[index].
	 * Returns the index of the last inserted element + 1 (the first empty
	 * position in a).
	 */
	private int toArray(BinaryNode<E> n, E[] a, int index) {
		if (n != null) {
			index = toArray(n.left,a,index);
			a[index] = n.element;
			index++;
			index = toArray(n.right,a,index);
		}
		return index;
	}
	
	/*
	 * Builds a complete tree from the elements a[first]..a[last].
	 * Elements in the array a are assumed to be in ascending order.
	 * Returns the root of tree.
	 */
	private BinaryNode<E> buildTree(E[] a, int first, int last) {
		
		if (first <= last) {
			int middle = (first + last+1)/2;
			BinaryNode<E> n = new BinaryNode(a[middle]);
			
			n.left = buildTree(a, first, middle-1);
			n.right = buildTree(a, middle+1, last);
			
			return n;
			
		} else {
			return null;
		}
	}
	


	static class BinaryNode<E> {
		E element;
		BinaryNode<E> left;
		BinaryNode<E> right;

		private BinaryNode(E element) {
			this.element = element;
		}	
	}
	
	
	public static void main(String[] args) {
		BinarySearchTree BST = new BinarySearchTree();
		BST.add(5);
		BST.add(5);
		BST.add(9);
		BST.add(3);
		BST.add(1);
		BST.add(4);
		BST.add(8);
		BST.add(7);
		BST.add(2);
		
//		BST.add(1);
//		BST.add(3);
//		BST.add(5);
//		BST.add(7);
//		BST.add(9);
//		BST.add(11);
//		BST.add(13);
		
		System.out.println("Höjd: " + BST.height());
		System.out.println("Size: " + BST.size());
		BST.printTree();
		
		BSTVisualizer viz1 = new BSTVisualizer("Hej", 600, 600);
		viz1.drawTree(BST);
		
		BST.rebuild();
		BSTVisualizer viz2 = new BSTVisualizer("Hej igen", 600, 600);
		viz2.drawTree(BST);
	}
}
