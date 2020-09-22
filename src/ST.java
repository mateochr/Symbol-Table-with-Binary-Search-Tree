import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Scanner;

public class ST {
	
	private class TreeNode {
		Suspect item;
		TreeNode p;
		TreeNode l;
		TreeNode r;
		int N;
		
		TreeNode(Suspect item, TreeNode p, TreeNode l, TreeNode r, int N){
			this.item = item;
			this.p = p;
			this.l = l;
			this.r = r;
			this.N = N;
		}
		
		//method used to remove a node.
		 protected void unlink() {
	            item = null;
	            p = l = r = null;
	        }
	};
	private TreeNode head; //root of tree
	private int size;     //size of tree
	private double sum = 0; //sum used to find mean savings
	List list1 = new List(); // list used in searchByLastName
	Suspect[] array = new Suspect[size]; //array used in printTopSuspects
	//root = null
	ST(){
		head = null; 
	}
	
	
	
	//recursive method to find a node with AFM 
	//if we find it return it, if not go and call
	//the same method for left or right subtree.
	Suspect searchByAFM(TreeNode p,int AFM){
		if(p==null){
			return null;
		}
		if(p.item.key()==AFM){
			return p.item;
		}
		if(p.item.key()<AFM){
			return searchByAFM(p.r,AFM);
		}else{
			return searchByAFM(p.l,AFM);
		}
			
	}
	//method called by main program.
	Suspect searchByAFM(int AFM) {		
		return searchByAFM(head,AFM); //calls method at row 
	}
	
	
	
	//recursive method to find a suspect with last_name traverses the tree like pre-order.
	//there might be more than one suspects with the same last name
	//so when we find one we put it in a list and call
	//the same method for left and right subtree.
	void searchByLastName(TreeNode p,String last_name){	
		if(p==null){
			return;
		}
		if(p.item.getLastName().equals(last_name)){
			 list1.put(p.item);
		}
		 searchByLastName(p.l,last_name);
		 searchByLastName(p.r,last_name);
		
	}
	
	//method called by main program.
	List searchByLastname(String last_name){ 
		list1= new List(); //renew the list.
		searchByLastName(head,last_name); 
		return list1; //return the new list.
	}
	
	
	
	//recursive method to insert a node as leaf, when we reach a leaf then put the node
	//and change size by one, then all the father nodes change from the root till the node we put
	//by returning the child node to the father.
	private TreeNode insert(TreeNode p, Suspect item, TreeNode parent){ 
		if(p==null){
			TreeNode node = new TreeNode( item, parent, null, null, 0);
			size++;
			return node;
		}
    	if(item.key()<p.item.key()){
    		p.l = insert(p.l,item,p);
    		p.N++;
    	}else{
    		p.r = insert(p.r,item,p);
    		p.N++;
    	}
    	return p;
    	
    }
	
	//method called from main program.
    void insert(Suspect item){ 
    	head = insert(head, item, null);
    }
    
    
    
    
    //recursive method that adds a node in the root of tree by doing rotations.
	private TreeNode insert_at_root(TreeNode p, Suspect item, TreeNode parent){ 
		if(p==null){
			TreeNode node = new TreeNode( item, parent, null, null, 1);
			size++;
			return node;
		}
    	if(item.key()<p.item.key()){
    		p.l = insert(p.l,item,p);
    		p=rotateRight(p);
    	}else{
    		p.r = insert(p.r,item,p);
    		p = rotateLeft(p);
    	}
    	return p;
    	
    }
	//method called from main program.
	void insert_at_root(Suspect item){
		head = insert_at_root(head,item,null);
	}
	
	//method that performs a right rotation.
    private TreeNode rotateRight(TreeNode node){
    	TreeNode parent = node.p;
    	TreeNode child = node.l;
    	//change N of the rotating nodes.
    	node.N=1;//counting itself
    	if(node.r != null){
    		node.N = node.N + node.r.N;
    	}
    	if(child.r != null){
    		node.N = node.N + child.r.N;
    	}
    	child.N = 1 + node.N;//counting itself + its right child
    	if(child.l != null){
    		child.N = child.N + child.l.N;
    	}
    	if(parent==null){
    		head=child;
    	}else if(parent.l==node){
    		parent.l = child;
    	}else{
    		parent.r = child;
    	}
    	child.p = node.p;
    	node.p = child;
    	node.l = child.r;
    	if(child.r != null) child.r.p = node;
    	child.r = node;
    	return child;
    	
    }
    
  //method that performs a left rotation.
    private TreeNode rotateLeft(TreeNode node){
    	TreeNode parent = node.p;
    	TreeNode child = node.r;
    	//change N of the rotating nodes.
    	node.N=1;//counting itself.
    	if(node.l != null){
    		node.N = node.N + node.l.N;
    	}
    	if(child.l != null){
    		node.N = node.N + child.l.N;
    	}
    	child.N= 1 + node.N; //counting itself + its left child.
    	if(child.r != null){
    		child.N = child.N + child.r.N;
    	}
    	
    	if(parent==null){
    		head=child;
    	}else if(parent.l==node){
    		parent.l = child;
    	}else{
    		parent.r = child;
    	}
    	child.p = node.p;
    	node.p = child;
    	node.r = child.l;
    	if(child.l != null) child.l.p = node;
    	child.l = node;
    	return child;

    }
    
    //method to change N when remove is called.
    void changeN(TreeNode p){
    	while(p != head){
    		p.p.N--;
    		p=p.p;
    	}
    	if(p==head){
    		head.N--;
    	}
    }
    
    //method called from main program.
	void remove(int AFM){
		TreeNode n = find(AFM);
		//if the is no such AFM.
		if(n==null){
			System.out.println("\n" + "There is no suspect with AFM: " + AFM + " or the tree is empty.");
		}else{
			changeN(n);
			remove(n);
			System.out.println("\n" + "The suspect with AFM: " + AFM + " has been removed.");
		}
	}
	
	//method to remove a node p.
	private void remove(TreeNode p){
		// If p has two children find its successor, then remove it.
		if(p.l != null && p.r != null){
			TreeNode succ = succ(p);
			p.item = succ.item;
			p = succ;
		}
		TreeNode parent = p.p;
		TreeNode child = p.l != null ? p.l : p.r;
		// The root is being removed.
		if(parent == null){
			head = child;
		}
		// Bypass p
		else if (p == parent.l){
			parent.l = child;
		}else {
			parent.r = child;
		}
		if(child != null){
			child.p = parent;
		}
		// Dispose p
		p.unlink(); 
		--size;
	}
	
	//method used to find a node with AFM 
	//return the node if you find it
	//if not call the same method for left or right sub tree.
	TreeNode find(TreeNode p,int AFM){
		if(p==null){
			return null;
		}
		if(p.item.key()==AFM){
			return p;
		}
		if(p.item.key()<AFM){
			return find(p.r,AFM);
		}else{
			return find(p.l,AFM);
		}
			
	}
	//method called from the method remove.
	TreeNode find(int AFM) {		
		return find(head,AFM);
	}
	
	
	private TreeNode succ (TreeNode p){
		// The successor is the leftmost leaf of q’s right subtree
		if(p.r != null){
			TreeNode node = p.r;
			while (node.l != null) node = node.l;
			return node;
		}
		// The successor is the nearest ancestor on the right
		else{
			TreeNode node = p.p;
			TreeNode h = p;
			while ( node != null && h == node.r){
				h = node;
				node = node.p;
			}
			return node;
		}
	}
	
	
	
	
	
	//method to load a file when given the path.
	//meaning this method will be inserting suspects into the tree from a given file.
	void load(String filename){
		FileInputStream file = null;
		try {
			file = new FileInputStream(filename);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
        Scanner scanner = new Scanner(file);//new scanner.
        while(scanner.hasNextLine())//while there is next line.
        {
        	 String line = scanner.nextLine();
        	 if(line != null && !line.isEmpty())
        	 {
        		 int AFM = Integer.parseInt(line.substring(0,5));
        		 int i =6;
        		 //a while to find how many characters is the first name.
        		 while (line.charAt(i) != ' ' ){ //stops when there is space
        			 i++;
        		 }
        		 String first_name =  line.substring(6,i);
        		 int j = i+1;
        		 //a while to find how many characters is the last name.
        		 while(line.charAt(j) != ' ' ){//stops when there is space
        			 j++;
        		 }
        		 String last_name = line.substring(i+1,j);
        		 int k = j+1;
        		//a while to find how many characters are the savings.
        		 while(line.charAt(k) != ' ' ){//stops when there is space
        			 k++;
        		 }
        		 double savings = Double.parseDouble(line.substring(j+1,k));
        		//taxed income is from where the space was found +1 until line.length().
        		 double taxed_income = Double.parseDouble(line.substring(k+1,line.length()));
        		 //new suspect with the above info.
        		 Suspect Nsuspect = new Suspect(AFM, first_name, last_name, savings, taxed_income);
        		 insert(Nsuspect);//insert the new suspect at the tree.
        	 }
        }
         
         scanner.close();
	}
	
	//method to return mean savings.
	//return 0 if the tree is empty.
	//if not then change sum
	//this method works like a pre-order search of the tree but in one line.
	//stop at every node and add the savings and then call the the same method 
	//for the left node until you find null then call for right node.
	double getMeanSavings(TreeNode p){
		if (p==null){
			return 0;
		}
		sum = p.item.getSavings() + getMeanSavings(p.l) + getMeanSavings(p.r);
		return sum;
		
	}
	
	//method called from main program.
	double getMeanSavings(){
		sum=0; //renew the sum every time.
		return getMeanSavings(head)/size; //this means return sum/size=mean savings of all suspects.
		
	}
	
	
	
	//method to look at all the nodes of the tree  like pre-order.
	//and insert all the suspects at an array.
	int i;
	void printTopSuspects(TreeNode p){
		if(p==null){
			return;
		}
		array[i] = p.item;
		i++;
		printTopSuspects(p.l);
		printTopSuspects(p.r);

	}
	
	//method called from main program.
	//renew i every time.
	//if head==null then tree is empty, else if the given number k is <= size then.
	//renew the array and call the above method.
	//when the above method is finished quicksort our array and print the top k suspects.
	//if k < size then we can't print more suspects than we have.
	void printTopSuspects(PrintStream stream, int k){
		i = 0;
		if(head==null){
			System.out.println("Tree is empty.");
		}else
		{
			  if(k<=size){
				  array = new Suspect[size];
				  printTopSuspects(head);
				  quicksort(0,size-1);	
				  for(i=size-1; i >= size-k; i--){
					  stream.println(array[i]);
				  }
			  }else{
				  System.out.println("\n" + "There are less suspects in the tree than " + k + " try a number <= " + size);
			  }
		}  
	}
	
	//method to sort our array from lowest to highest.
	 void quicksort(int l, int r ){
		int i = l, j=r;
		Suspect tmp;
		Suspect pivot = array[(l + r)/2];//pivot is the middle cell of the array.
		double dif3 = pivot.getSavings() - pivot.getTaxedInc();//pivot number.
		while(i<=j){
			double dif1 = array[i].getSavings() - array[i].getTaxedInc();//dif1 is the result of savings-taxed_income of each cell at the left of the pivot.
			double dif2 = array[j].getSavings() - array[j].getTaxedInc();//dif2 is the result of savings-taxed_income of each cell at the right of the pivot.
			while(dif1 < dif3){
				i++;
				dif1 = array[i].getSavings() - array[i].getTaxedInc();//we change dif1 as we move more to the right of the array.
			}
			while(dif2 > dif3){
				j--;
				dif2 = array[j].getSavings() - array[j].getTaxedInc();//we change dif2 as we move more to the left of the array.
			} 
			//swap
			if(i <= j){
				tmp = array[i];
				array[i] = array[j];
				array[j] = tmp;
				i++;
				j--;
			}
		}
		//recursion
		if (l < j)
			quicksort(l, j);
		if (i < r)
			quicksort(i, r);		
	}
	
	

	
	//method that prints the nodes of the tree by afm
	//it is an in-order traversal of the tree.
	void  printTreeByAFM(TreeNode p,PrintStream stream){
		if(p==null){
			return;
		}
		printTreeByAFM(p.l,stream);
		stream.println(p.item);
		printTreeByAFM(p.r,stream);
			
	}
	
	//method called from main program.
	void printTreeByAFM(PrintStream stream){
		if(head==null){//if its empty.
			System.out.println("Tree is empty.");
		}else{
			printTreeByAFM(head,stream);
		}
	}

}
