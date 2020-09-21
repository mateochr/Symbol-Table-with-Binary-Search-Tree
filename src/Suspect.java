public class Suspect {
	public int AFM;
	String first_name, last_name;
	double savings, taxed_income;
	
	Suspect(int AFM, String first_name, String last_name, double savings, double taxed_income){
		this.AFM = AFM;
		this.first_name = first_name;
		this.last_name = last_name;
		this.savings = savings;
		this.taxed_income = taxed_income;
	}
	
	public int key() { return AFM; }//get afm.
	public String getLastName() {return last_name;}//get last name.
	public double  getSavings() { return savings;}//get savings.
	public double getTaxedInc() {return taxed_income;}//get taxed_income.
	//print all the info of a suspect.
	public String toString(){
		return "\n" + "AFM: " + AFM + "\n" 
				+ "First name: " + first_name + "\n" 
				+ "Last name: " + last_name + "\n"  
				+ "Bank account savings: " + savings + "\n" 
				+ "Taxed income: " + taxed_income + "\n";
				
	}
}