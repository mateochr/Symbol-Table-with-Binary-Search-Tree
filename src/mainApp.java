import java.util.Scanner;

public class mainApp {
	static private ST spct = new ST(); //spct to call methods from ST.
	public static void main(String[] args){
		int answer;
		//the menu
		do
		{	
			System.out.println("\n");
			System.out.println("If you want to search a suspect by AFM press 1.");
			System.out.println("If you want to search a suspect by last name press 2.");
			System.out.println("If you want to insert a suspect press 3.");
			System.out.println("If you want to insert a suspect at root press 4.");
			System.out.println("If you want to remove a suspect press 5.");	
			System.out.println("If you want to load a file press 6.");
			System.out.println("If you want the average savings press 7.");
			System.out.println("If you want to print top suspects press 8.");
			System.out.println("If you want to print all suspects information by AFM press 9." + "\n");
			
			Scanner scan= new Scanner(System.in);
			answer= scan.nextInt();
			//depending on the given answer above there are cases.
			switch(answer)
			{
			case 1:
				case1();
				break;
			case 2:
				case2();
				break;
			case 3:
				case3();
				break;
			case 4:
				case4();
				break;
			case 5:
				case5();
				break;
			case 6:
				case6();
				break;
			case 7:
				case7();
				break;
			case 8:
				case8();
				break;
			case 9:
				case9();
				break;
			}
		}while (answer <= 9 && answer >= 1);//go back to menu.
		

		
	
	}
	//search by afm.
	static void case1(){
		System.out.println("\n" + "Please insert the AFM you want to search for.");
		Scanner scan= new Scanner(System.in);
		int AFM = scan.nextInt(); //read the given AFM.
		Suspect a = spct.searchByAFM(AFM); //new suspect return from the called method.
		if(a==null){ //if its null then we didn't find such a suspect.
			System.out.println("\n" + "There is no suspect with AFM: " + AFM + " or the tree is empty.");
		}else{ //else we found the suspect with AFM.
			System.out.println("\n" + "There is a suspect with AFM: " + AFM + " and his information is");
			System.out.println(a);
		}
		
	}
	//search by last name.
	static void case2(){
		 System.out.println("Please insert the last name you want to search for.");
		 Scanner scan= new Scanner(System.in);
		 String lastName= scan.nextLine(); //red the given name.
		 if(spct.searchByLastname(lastName).isEmpty()){ // check if the the return list is empty.
			 System.out.println("\n" + "There is no suspect with last name: " + lastName + " or the tree is empty." + "\n");
		 }else{ //if not empty then print the list.
			 System.out.println("\n" + "There is 1 suspect or more with last name: " + lastName + " and his/their information is");
			 spct.searchByLastname(lastName).printList(System.out);
		 }
		
	}
	
	//insert new suspect.
	//this method reads the answers given from the keyboard
	//and creates a new suspect with the given info.
	//then insert it at the tree.
	static void case3(){
		Scanner scan= new Scanner(System.in);
		Scanner scan1= new Scanner(System.in);
		System.out.println("\n" + "Please insert the AFM of the new suspect.");
		int AFM = scan.nextInt();//read afm.
		
		System.out.println("\n" + "Please insert the fisrt name of the new suspect.");
		String first_name = scan1.nextLine();//read first name.
		
		System.out.println("\n" + "Please insert the last name of the new suspect.");
		String last_name = scan1.nextLine();//read last name.
		
		System.out.println("\n" + "Please insert the total savings of the new suspect.");
		double savings = scan.nextDouble();//read savings.
		
		System.out.println("\n" + "Please insert the taxed income of the new suspect.");
		double taxed_income = scan.nextDouble();//read taxed income.
		
		Suspect Nsuspect = new Suspect(AFM, first_name, last_name, savings, taxed_income);//new suspect
		spct.insert(Nsuspect);//insert new suspect.
	
	}
	
	//insert at root a new suspect.
	//this method reads given info from the keyboard
	//and creates a suspect.
	//then insert it at the tree.
	static void case4(){
		Scanner scan= new Scanner(System.in);
		Scanner scan1= new Scanner(System.in);
		
		System.out.println("\n" + "Please insert the AFM of the new suspect.");
		int AFM = scan.nextInt(); //read AFM.
		
		System.out.println("\n" + "Please insert the fisrt name of the new suspect.");
		String first_name = scan1.nextLine(); //read first name.
		
		System.out.println("\n" + "Please insert the last name of the new suspect.");
		String last_name = scan1.nextLine(); //read last name.
		
		System.out.println("\n" + "Please insert the total savings of the new suspect.");
		double savings = scan.nextDouble();//read savings.
		
		System.out.println("\n" + "Please insert the taxed income of the new suspect.");
		double taxed_income = scan.nextDouble(); //read taxed income.
		
		Suspect Rsuspect = new Suspect(AFM, first_name, last_name, savings, taxed_income);//new suspect.
		spct.insert_at_root(Rsuspect);//insert at root new suspect.
	
	}
	
	//remove suspect
	//this method removes a suspect with the AFM given from the keyboard
	static void case5(){
		System.out.println("\n" + "Please insert the AFM of the suspect that you want to remove.");
		Scanner scan= new Scanner(System.in);
		int AFM = scan.nextInt(); //read AFM
		spct.remove(AFM);//remove suspect if the afm matches.
	
	}
	
	//this method asks for the path of the file to load.
	static void case6(){
		System.out.println("\n" + "Please insert the path of the file that you want to load.");
		Scanner scan= new Scanner(System.in);
		String fileName = scan.nextLine(); //read path.
		spct.load(fileName); //load file.
		
	}
	
	//get mean savings.
	static void case7(){
		double result = spct.getMeanSavings();//call method getMeanSavings and save it at result.
		System.out.println("\n" + "Mean savings of suspects are: " + result);
	}
	
	//prints top k suspects.
	static void case8(){
		System.out.println("\n" + "Please insert the number of the top suspects that you want to be printed.");
		Scanner scan= new Scanner(System.in);
		int topSuspects = scan.nextInt();//reads k top suspects to print.
		spct.printTopSuspects(System.out, topSuspects);//calls method to print.
		
	}
	
	//print all nodes by afm.
	static void case9(){
		spct.printTreeByAFM(System.out);
	}
	
	
}
