package Employee_Management;

public class Developer extends Employee {
		int overTimeHours;
		
		public Developer(double basesalary , int attendanceDays , int rating ,int overTimeHours) {
			super(basesalary,attendanceDays,rating);
			this.overTimeHours=overTimeHours;
		}
		@Override
		double calculateGrossSalary() {
			
			return basesalary+(overTimeHours *500);
		}
		
		
}
