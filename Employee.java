
public class Employee {
	
	private int id;
	private String firstName;
    private String lastName;
    private String company;
    private double salary;
    
    
    public Employee () {
    	
    }
    
    public Employee (String firstName, String lastName,String company,double salary) {
    	
    	this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setSalary(salary);
        this.setCompany(company);
    
    }
    public Employee (int id,String firstName, String lastName,String company,double salary ) {
    	
    	this.setId(id);
    	this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setSalary(salary);
        this.setCompany(company);
    }

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}
	
	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	

}
