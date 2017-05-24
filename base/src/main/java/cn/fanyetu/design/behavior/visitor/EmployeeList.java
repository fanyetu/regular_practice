package cn.fanyetu.design.behavior.visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * 员工列表类，对象结构
 *
 * Created by Administrator on 2017/5/24.
 */
public class EmployeeList {

    private List<Employee> list = new ArrayList<Employee>();

    public void addEmployee(Employee employee){
        list.add(employee);
    }

    /**
     * 遍历访问集合中每一个员工对象
     * @param handler
     */
    public void accept(Department handler){
        for (Employee e : list){
            e.accept(handler);
        }
    }
}
