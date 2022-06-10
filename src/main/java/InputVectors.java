import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.io.FilenameFilter;
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.StringTokenizer;
import java.util.Vector;

public class InputVectors extends Thread {
	
	private String fname;
	Vector<String> myVector;
	
	public InputVectors(String fn) {
		this.fname=fn;
	}
	
	public void print() {
		System.out.println(myVector);
	}
	
	public void run() {
		
		
synchronized(this){		
		
		myVector= new Vector<String>(50);
		int count=0;
		

		try {
			
		
		      File myObj = new File(fname);
		      Scanner myReader = new Scanner(myObj);
		      while (myReader.hasNextLine()) {
		        String data = myReader.nextLine();
		        //System.out.println(data);
		        data=data.replaceAll("[^a-zA-Z0-9]", " ");
		        
		        StringTokenizer st = new StringTokenizer(data," ");  
		        while (st.hasMoreTokens()) {
		        	myVector.add(st.nextToken());
		            count++;
		        }  
		       
		      }
		      myReader.close();
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		
			
		this.notify();
	}
			
	}

}
