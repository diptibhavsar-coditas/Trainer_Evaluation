package Employee_Management;

public class Main {
	public static void main(String[] args) {
		System.out.println();
		System.out.println("-----Developers Salary-----");
		Employee dev = new Developer(50000,25,4,10);
		dev.calculateNetSalary();
		
		System.out.println();
		System.out.println("-----Managers Salary-----");
		Employee mgr = new Manager(80000,26,5,5);
		mgr.calculateNetSalary();
		
		System.out.println();
		System.out.println("-----Interns Salary-----");
		Employee in = new Intern(21000,18,3);
		in.calculateNetSalary();
		
		
	}
}
