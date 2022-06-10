import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

class Node {
	String info;
	Node left;
	Node right;
	int level;
}


public class VocabularyBST extends Thread {

	public Node root;
	VocabularyBST bt;
	
public void run() {
	
synchronized(this) {	
	
	bt= new VocabularyBST();
	root = null;
	
	
	try {
		
	
	      File myObj = new File("vocabulary.txt");
	      Scanner myReader = new Scanner(myObj);
	      while (myReader.hasNextLine()) {
	        String data = myReader.nextLine();
	       root= bt.insert(root, data);
	      }
	      myReader.close();
	    } catch (FileNotFoundException e) {
	      System.out.println("An error occurred.");
	      e.printStackTrace();
	    }
	

		this.notify();
//	
//	bt.traverse(root);
//	
//	Node abc=bt.search(root, "Play");
//	System.out.println(abc.info);
	
}

}


Node create(String n) {

	Node treeval = new Node();
	treeval.info = n;
	treeval.left = null;
	treeval.right = null;

	return treeval;
}

public Node insert(Node root, String n) {
	

	
	
	
	if (root == null) {
		root = create(n);
		root.level = 0;
	}else if(root!=null) {
		int val = n.compareTo(root.info);
		
	
			if (val <= 0) {
				root.left = insert(root.left, n);
			}
			else {
				root.right = insert(root.right, n);
			}
			
	}
	return root;
}

String searchWord(String word) {

	Node abc=search(this.root, word);
	if (abc!=null)
		return abc.info;
	
	return "aa";

}


Node search(Node root, String data) {

	

	if (root == null) {
		return root;
	}

	if (root.info.equals(data)) {
		return root;
	}

	int val = data.compareTo(root.info);
	
	if (val < 0) {
		return search(root.left, data);
	}
	else {
		return search(root.right, data);
	}
}



void print() {
	traverse(root);
}

void traverse(Node n) {
if (n == null) {
	System.out.println("Empty");
	return;
}

if (n.left != null) {
	traverse(n.left);
}

System.out.println(n.info + "\n");


if (n.right != null) {
	traverse(n.right);
}
}

}