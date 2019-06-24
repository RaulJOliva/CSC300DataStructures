package algs13;



import stdlib.*;

// PROBLEM 2.2.17
public class MyLinkedSort {
    static class Node {
        public Node() { }
        public double item;
        public Node next;
    }

    int N;
    Node first;
    
    public MyLinkedSort () {
        first = null;
        N = 0;
        checkInvariants ();
    }

    private void myassert (String s, boolean b) { if (!b) throw new Error ("Assertion failed: " + s); }
    private void checkInvariants() {
        myassert("Empty <==> first==null", (N == 0) == (first == null));
        Node x = first;
        for (int i = 0; i < N; i++) {
            if (x==null) {
                throw new Error ("List too short!");
            }
            x = x.next;
        }
        myassert("EndOfList == null", x == null);
    }

    public boolean isEmpty () { return first == null; }
    public int size () { return N; }
    public void add (double item) {
        Node newfirst = new Node ();
        newfirst.item = item;
        newfirst.next = first;
        first = newfirst;
        N++;
    }

    private static void print (String s, Node b) {
        StdOut.print (s + ": ");
        for (Node x = b; x != null; x = x.next)
            StdOut.print (x.item + " ");
        StdOut.println ();
    }
    private static void print (String s, Node b, double i) {
        StdOut.print (s + ": ");
        for (Node x = b; x != null; x = x.next)
            StdOut.print (x.item + " ");
        StdOut.println (": " + i);
    }
    

    static public Node sort(Node l ){ // 
	   // base case: list is of size 1. reurn
	   // otherwise use split to create two lists
	   // recursively sort each of them
	   // use merge to combine them, and return the result
	   //return null;
    	if(l == null || l.next == null)
    		return l;
    	Node l2 = l;
    	Node r2 = l.next;
    	
    	while((r2 != null) && (r2.next != null)) {
    		l = l.next;
    		r2 = (r2.next).next;
    	}
    	r2 = l.next;
    	l.next = null;
    	return merge(sort(l2), sort(r2));
	}
		 
	static public Node[] split(Node l){
      // parameter is a List
	  // it returns an array of size 2
	  // the 0th element is theleft ist
	  // the first element is the right list
		//Node temp = l;
		//int size = l.size()/2;
		return null;
		
	  }
	
	static public Node merge(Node lt, Node rt){
	// merge creates a new LinkedList
	// whose elements come from the lt and rt MyLinkedLists
		//return null;
		Node tem = new Node();
		Node stem = tem;
		Node n = stem;
		while((lt != null)&&(rt != null)) {
			if(lt.item <= rt.item) {
				n.next = lt;
				n = lt;
				lt = lt.next;
			}
			else {
				n.next = rt;
				n = rt;
				rt = rt.next;
			}
		}
		n.next = (lt == null)?rt:lt;
		return stem.next;

	}

    public static void main (String args[]) {
        int[] a1 = new int[20];
		for (int i = 0; i < a1.length; i++)
			a1[i] = i;
		StdRandom.shuffle (a1);
        MyLinkedSort b1 = new MyLinkedSort ();
        for (int i:a1) b1.add(i);
        MyLinkedSort.print("before sort",b1.first);
        Node res1 = MyLinkedSort.sort(b1.first);
        MyLinkedSort.print("after sort",res1);
        
        int[] a2 = new int[200];
        for (int i =0;i<a2.length;i++) {a2[i]=i;}
        StdRandom.shuffle(a2);
        MyLinkedSort b2 = new MyLinkedSort();
        for(int i:a2) b2.add(i);
        MyLinkedSort.print("before sort", b2.first);
        Node res2 = MyLinkedSort.sort(b2.first);
        MyLinkedSort.print("after sort", res2);
        

        // Follow the above pattern to write a doubling test
        // to calculate ratios.  Starting from size 200 and going
        // to size 200 x 4096
     
         
    }
}



