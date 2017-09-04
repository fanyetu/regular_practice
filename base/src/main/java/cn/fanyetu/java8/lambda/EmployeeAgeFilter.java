package cn.fanyetu.java8.lambda;

/**
 * Created by zhanghaonan on 2017/9/4.
 */
public class EmployeeAgeFilter implements MyFilter<Employee> {
	@Override
	public boolean test(Employee employee) {
		return employee.getAge() > 30;
	}
}
