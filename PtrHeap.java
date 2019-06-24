 package algs24;

import java.util.NoSuchElementException;

import stdlib.StdIn;
import stdlib.StdOut;


/**
 *  The <tt>PtrHeap</tt> class is the priorityQ class from Question 2.4.24.
 *  It represents a priority queue of generic keys.
 *  
 *  It supports the usual <em>insert</em> and <em>delete-the-maximum</em>
 *  operations, along with methods for peeking at the maximum key,
 *  testing if the priority queue is empty, and iterating through
 *  the keys.
 *  For additional documentation, see <a href="http://algs4.cs.princeton.edu/24pq">Section 2.4</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 */

public class PtrHeap<K extends Comparable<? super K>> {
	
	static class Node<K> {
		K value;
		Node<K> parent;
		Node<K> lchild;
		Node<K> rchild;
	}
	
	private int size;
	private Node<K> root;
	
    boolean isRoot(Node<K> n){ return n == n.parent; }
    
    Node<K> find(int n){ return null; } 
    
    /****************************************************
     * Implemented Heap helper functions from Heap.java
     * I've been looking over the packages in the src directory
     * I found some functions to help out with what the assignment was asking for
     * I thought I'd implement these, If this wasn't allowed please let me know. 
     * I changed the functions to fit the parameters of this assignment
     */
	
    private boolean lessThan(Node<K> a, Node<K> b) {
    	K aV = (K) a.value;
    	K bV = (K) b.value;
    	if(aV.compareTo(bV) < 0) {
    		return true;
    	} else {
    		return false;
    	}
    }
    
    private void swim(Node<K> k) {
		while(k!=root&& lessThan(k.parent,k)) {
			exch(k,k.parent);
			k=k.parent;
		}
	}
    
    private void sink(Node<K> n) {
    	boolean lChildV = n.lchild != null;
    	boolean rChildV = n.rchild != null;
    	while(lChildV) {
    		Node<K> newKey = n.lchild;
    		if(rChildV && lessThan(newKey, n.rchild)) {
    			newKey = n.rchild;
    		}
    		if(!lessThan(n,newKey)) {
    			break;
    		}
    		exch(n,newKey);
    		n=newKey;
    		lChildV=n.lchild != null;
    		rChildV=n.rchild != null;
    	}
    }
    
	void exch(Node<K> n1, Node<K> n2) { 
	// only swap items of nodes
		K temp = n1.value;
		n1.value = n2.value;
		n2.value = temp;
	}
	
	@SuppressWarnings("unchecked")
	/** Create an empty priority queue  */
	public PtrHeap() {
		size = 0;
		root = null;
	}
    
	/** Is the priority queue empty? */
	public boolean isEmpty() { 
		return size == 0; }
	

	/** Return the number of items on the priority queue. */
	public int size() { 
		return size; }

	/**
	 * Return the largest key on the priority queue.
	 * Throw an exception if the priority queue is empty.
	 */
	public K max() {
		//return null;
		if(this.isEmpty()) {
			throw new NoSuchElementException();
		}
		return root.value;
	}

	/** Add a new key to the priority queue. */
	public void insert(K x) { 
		//return;
		Node<K> insKey = new Node<K>();
		insKey.value = x;
		size++;
		if(size==1) {
			root = insKey;
		} else {
			Node<K> parKey = find(Math.max(size/2, 1));
			if(size%2==0) {
				parKey.lchild=insKey;
				
			}else {
				parKey.rchild=insKey;
			}
			insKey.parent=parKey;
		}
		swim(insKey);
	}

	/**
	 * Delete and return the largest key on the priority queue.
	 * Throw an exception if the priority queue is empty.
	 */
	public K delMax() {
		//return null;
		if(isEmpty()) {
			throw new NoSuchElementException();
		}
		if(size==1) {
			K Val = root.value;
			root = null;
			size--;
			return Val;
		}
		K VMax = max();
		Node<K> last = find(size);
		exch(root,last);
		Node<K> parKey = last.parent;
		last.parent = null;
		if(parKey.lchild == last) {
			parKey.lchild = null;
		} else {
			parKey.rchild = null;
		}
		sink(root);
		size--;
		return VMax;
	}

	private void showHeap() { 
	    // a method to print out the heap
		// useful for debugging
		//for(int i =1;i<= N; i++)
		//	StdOut.print(value[i] + " ");
		//StdOut.println();
//		String newLine = "[]\n";
//		if(this.isEmpty()) {
//			return newLine;
//		}
//		int showL = (int) (Math.log(size)/Math.log(2));
//		int currL = 1;
//		Node<K>[] children = new Node<K>[] { root };
//		String s = "";
//		while(currL <= showL +1) {
//			Node[] gchildren = new Node[(int) Math.pow(2,  currL)];
//			int pos = 0;
//			for(int i = 0; i<children.length;i++) {
//				Node<K> currP = children[i];
//				if(currP == null) {
//					break;
//				}
//				s += currP.value+" ";
//				gchildren[pos++] = currP.lchild;
//				gchildren[pos++] = currP.rchild;
//			}
//			s+="\n";
//			children = gchildren;
//			currL++;
//		}
//		return s;
		
		
		
		//for (int i = 1; i <= size; i++) {
			//StdOut.print ([i] + " ");
			//}
		//StdOut.println ();
	}

	public static void main(String[] args) {
		PtrHeap<String> pq = new PtrHeap<>();
		StdIn.fromString("10 20 30 40 50 - - - 05 25 35 - - - 70 80 05 - - - - ");
		while (!StdIn.isEmpty()) {
			StdOut.print ("pq:  "); pq.showHeap();
			String item = StdIn.readString();
			if (item.equals("-")) StdOut.println("max: " + pq.delMax());
			else pq.insert(item);
		}
		StdOut.println("(" + pq.size() + " left on pq)");

	}

}

