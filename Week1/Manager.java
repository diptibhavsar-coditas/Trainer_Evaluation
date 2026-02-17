package Employee_Management;

public class Manager extends Employee{
	
	int teamsize;
	
	public Manager(double basesalary ,int attendanceDays , int rating ,int teamSize){
		super(basesalary,attendanceDays,rating);
		this.teamsize =teamSize;
		
	}

	@Override
	double calculateGrossSalary() {
		return basesalary +(teamsize*1000);
				
	}
}
