package bst;

public class BinarySearchTree<E extends Comparable<? super E>> {
	BinaryNode<E> root;
	int size;

	/**
	 * Constructs an empty binary searchtree.
	 */
	public BinarySearchTree() {

	}

	/**
	 * Inserts the specified element in the tree if no duplicate exists.
	 * 
	 * @param x
	 *            element to be inserted
	 * @return true if the the element was inserted
	 */
	public boolean add(E x) {
		if (root != null) {
			if(add(x, root)) {
				size ++;
				return true;
			}
		} else {
			root = new BinaryNode<E>(x);
			size++;
			return true;
		}
		return false;
	}
	
	private boolean add(E x, BinaryNode<E> node) {
		int i = x.compareTo(node.element);
		if (i > 0) {
			if (node.right!= null) {
				return add(x, node.right);
			} else {
				node.right = new BinaryNode<E>(x);
				return true;
			}
		} else if (i < 0) {
			if (node.left != null) {
				return add(x, node.left);
			} else {
				node.left = new BinaryNode<E>(x);
				return true;
			}
		}
		return false;
	}
	

	/**
	 * Computes the height of tree.
	 * 
	 * @return the height of the tree
	 */
	public int height() {
		return height(root);
	}

	private int height(BinaryNode<E> node) {
		if (node == null) {
			return 0;
		} else if(node.left == null && node.right == null) {
			return 1;
		}
		return 1 + Math.max(height(node.right), height(node.left));
//		return node == null ? 0 : 1 + Math.max(height(node.right), height(node.left));
	}

	/**
	 * Returns the number of elements in this tree.
	 * 
	 * @return the number of elements in this tree
	 */
	public int size() {
		return size;
	}

	/**
	 * Print tree contents in inorder.
	 */
	public void printTree() {
		print(root);
		System.out.println();
	}
	
	private void print(BinaryNode<E> n) {
		if(n != null) {
			print(n.left);
			System.out.print(n.element + " ");
			print(n.right);
		}
	}

	/**
	 * Builds a complete tree from the elements in the tree.
	 */
	public void rebuild() {
		E[] a = (E[]) new Comparable[size];
		int last = toArray(root, a, 0) - 1;
		int middle = last/2;
//		root = new BinaryNode(a[middle]);
//		root.left = buildTree(a, 0, middle -1);
//		root.right = buildTree(a, middle + 1, last);
		root = buildTree(a, 0, last);
	}

	/*
	 * Adds all elements from the tree rooted at n in inorder to the array a
	 * starting at a[index]. Returns the index of the last inserted element + 1
	 * (the first empty position in a).
	 */
	private int toArray(BinaryNode<E> n, E[] a, int index) {
		if(n != null) {
			index = toArray(n.left, a, index);
			a[index] = n.element;
			index++;
			index = toArray(n.right, a, index);
		}
		return index;
	}

	/*
	 * Builds a complete tree from the elements a[first]..a[last]. Elements in
	 * the array a are assumed to be in ascending order. Returns the root of
	 * tree.
	 */
	private BinaryNode<E> buildTree(E[] a, int first, int last) {
		int middle = (first + last) / 2;
		BinaryNode<E> n = new BinaryNode<E>(a[middle]);
		if(first < middle) {
			n.left = buildTree(a, first, middle -1);
		}
		if (middle < last) {
			n.right = buildTree(a, middle +1, last);
		}
		return n;
	}

	static class BinaryNode<E> {
		E element;
		BinaryNode<E> left;
		BinaryNode<E> right;

		private BinaryNode(E element) {
			this.element = element;
		}
	}

}
