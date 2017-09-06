package cn.fanyetu.java8.lambda;

/**
 * Created by zhanghaonan on 2017/9/4.
 */
public class Employee {

	private String name;
	private Integer age;
	private Double salary;

	public Employee(Integer age, String name) {
		this.age = age;
		this.name = name;
	}

	@Override
	public String toString() {
		return "Employee{" +
				"name='" + name + '\'' +
				", age=" + age +
				", salary=" + salary +
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
}
