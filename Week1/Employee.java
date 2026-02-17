package Employee_Management;

public abstract class Employee {
	double basesalary;
	int attendancedays;
	int rating;
	static final double pf_Percent =0.12;
	 
	public Employee(double basesalary, int attendancedays,int rating) {
		this.basesalary =basesalary;
		this.attendancedays=attendancedays;
		this.rating =rating;
	}
	
	abstract double calculateGrossSalary();
	
	double attendanceDeduction() {
		double dailySalary = basesalary/30;
		int absentDays = 30 - attendancedays;
		
		return absentDays*dailySalary;
		
	}
	
	double bonus(double gross) {
		double percent =0;
		
		switch(rating) {
			case 5 : percent  = 0.20;
				break;
				
			case 4 : percent  = 0.15;
				break;
		
			case 3 : percent  = 0.10;
				break;
		
			case 2 : percent  = 0.05;
				break;
		
			default : percent = 0;
		
		}
		
		return gross*percent;
	}
	
	double pf()
	{
		return basesalary*pf_Percent;
	}
	
	double tax(double taxableIncome)
	{
		if(taxableIncome <= 50000)
		{
			return taxableIncome * 0.05;
		}
		else if(taxableIncome <= 100000)
		{
			return taxableIncome * 0.10;
		}
		else if(taxableIncome <= 150000)
		{
			return taxableIncome * 0.15;
		}
		else
		{
			return taxableIncome*0.20;
		}
	}
	public void calculateNetSalary() {
		double gross = calculateGrossSalary();
		double bonus = bonus(gross);
		double tax = tax(gross + bonus);
		double pf = pf();
		double attendanceDeduction = attendanceDeduction();
		
		double netSalary = gross +bonus -tax-pf -attendanceDeduction;
		
		System.out.println("Gross Salary : "+gross);
		System.out.println("Bonus : "+bonus);
		System.out.println("Tax : "+tax);
		System.out.println("PF : "+pf);
		System.out.println("Attendance deduction : "+attendanceDeduction);
		System.out.println("Net Salary : "+netSalary);
	}
}
