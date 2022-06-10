import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Arrays;

public class Main extends Thread {

public static void main(String[] args) throws InterruptedException {
	// TODO Auto-generated method stub
	
	File dir = new File(System.getProperty("user.dir"));
	
	FilenameFilter textFilter = new FilenameFilter() {
        public boolean accept(File dir, String name) {
            return name.toLowerCase().endsWith(".txt");
        }
    };
    
    int count=0;
    String[] listing = dir.list(textFilter);
	
	if (dir.isDirectory())
	{
	System.out.println("The input Files");
	
	for (int i=0; i < listing.length; i++) {
	System.out.println(listing[i]);
	count++;
	}
	}
	
	InputVectors iv[]=new InputVectors[count];
	for (int i=0 ;i<count-1 ;i++) {
		iv[i]=new InputVectors(listing[i]);
		iv[i].setName(listing[i]);
	}
	
	VocabularyBST t1=new VocabularyBST();
	t1.setName("vocabulary.txt");
	
	t1.start();
	
	synchronized(t1){
		t1.wait();
	}
	
	
	for (int i=0 ;i<count-1 ;i++) {
		
		iv[i].start();
		
		synchronized(iv[i]) {
		iv[i].wait();
		}
	}
	

	
	boolean menu=true;
	int option;
	Scanner S=new Scanner(System.in);

	while(menu) {
		
		System.out.println("\nEnter An Option");
		System.out.println("Enter 1 to View BST");
		System.out.println("Enter 2 to View Vectors");
		System.out.println("Enter 3 to View Matches and Frequency");
		System.out.println("Enter 4 for Word Search");
		System.out.println("Enter 5 for Sentence Search");
		System.out.println("Enter 6 to Exit");
		option=S.nextInt(); 
		S.nextLine();
		
		switch(option) {
		case 1:
			t1.print();
			break;
		case 2:
			for (int i=0 ;i<count-1 ;i++) {
				iv[i].print();
			}
			break;
		case 3:
			//Matched Words
			Word words[]=new Word[100];
			String mw[]=new String[100];
			String nw[]=new String[100];
			int c=0;
			int f=0;
			
			for (int i=0 ; i<count-1 ; i++) {
				c=0;
				System.out.println("Matches in File: "+iv[i].getName());
					for (int j=0;j<iv[i].myVector.size();j++) {
						String x=t1.searchWord(iv[i].myVector.get(j));
						if (!(x.equals("aa"))) {
						nw[f]=x;	
						f++;
						System.out.println("Matched Word:"+x);
						c++;
						}
					}
				System.out.println("Total Matched Words Found in "+iv[i].getName() +":" + c +"\n");
				
			}

			mw = new HashSet<String>(Arrays.asList(nw)).toArray(new String[0]);
			
			int y=0;
		
			for (int i=0;i<mw.length;i++) {
				if(mw[i]!=null) {
					words[y]=new Word();
					words[y].setInfo(mw[i]);
					words[y].setFrequency(0);
					y++;
					//System.out.println("Word:"+ mw[i]);
				}
			}
			
			
			for (int i=0;i<y;i++) {
				for (int j=0;j<f;j++) {
					if(words[i].getInfo().equals(nw[j])) {
						words[i].incrementFrequency();
					}
				}
			}
			
			System.out.println("Matched Words And Their Frequencies");
			for (int i=0;i<y;i++) {
				System.out.println("Matched Word:"+words[i].getInfo()+" "+"Frequency:"+words[i].getFrequency());
			}
			
		break;
		
		case 4:
			int k=0;
			boolean mt=true;
			String xyz="";
			System.out.println("Enter the Word to Search");
			xyz=xyz+S.nextLine();
			
			for (int i=0;i<count-1;i++) {
				for (int j=0;j<iv[i].myVector.size();j++) {
					if(xyz.equals(iv[i].myVector.get(j))){
						System.out.println("Matched and is in File: " + iv[i].getName());
						k++;
						mt=false;
						
					}else {
						
					}
					
				}
			}
			
			if(mt==true) {
				System.out.println("No Matches");
			}else {
			 System.out.println("Frequency: "+k);
			}
			
			
			
			break;
			
		case 5:
			boolean mty=true;
			String rtz="";
			System.out.println("Enter the Sentence to Search(Add Fullstop)");
			rtz=rtz+S.nextLine();
			
			try {
				
				for (int i=0 ;i<count-1;i++) {
			      File myObj = new File(listing[i]);
			      Scanner myReader = new Scanner(myObj);
			      while (myReader.hasNextLine()) {
			      String data = myReader.nextLine();
			        
			      if(rtz.equals(data)) {
			    	  System.out.println("Matched and File is: "+ listing[i]);
			    	  mty=false;
			      }else {
			
			      }
			      
			      }
			      myReader.close();
			    } 
			}catch (FileNotFoundException e) {
			      System.out.println("An error occurred.");
			      e.printStackTrace();
			    }
			      
			if(mty==true) {
				System.out.println("No Matches");
			}
			break;
			
		case 6:
			System.out.println("Terminated");
			menu=false;
			break;
		default:
			System.out.println("Input Valid Choice");
			
	}

	
	
}

}

}
