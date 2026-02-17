package Employee_Management;

public class Intern extends Employee {

	public Intern(double basesalary, int attendancedays, int rating) 
	{
		super(basesalary, attendancedays, rating);
	}

	@Override
	double calculateGrossSalary() {
		double attendancePercent =(attendancedays /30.0)*100;
		
		if(attendancePercent < 70) {
			return basesalary-(basesalary *0.20);
		}
		else 
			return basesalary;
	}
	
}
