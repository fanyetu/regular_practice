package cn.fanyetu.java8.lambda;

/**
 * Created by zhanghaonan on 2017/9/4.
 */
public class Employee {

	private String name;
	private Integer age;
	private Double salary;
	private Status status;

	public Employee(Integer age, String name) {
		this.age = age;
		this.name = name;
	}

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                ", status=" + status +
                '}';
    }

    public Employee(Integer age){
		this.age = age;
	}

	public Employee(String name, Integer age, Double salary) {
		this.name = name;
		this.age = age;
		this.salary = salary;
	}

    public Employee(String name, Integer age, Double salary, Status status) {
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.status = status;
    }

    public Employee() {

	}

	public String getName() {

		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public enum Status{
	    FREE,
        BUSY,
        VOCATION
    }
}
