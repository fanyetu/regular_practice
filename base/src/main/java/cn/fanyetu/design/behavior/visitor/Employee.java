package cn.fanyetu.design.behavior.visitor;

/**
 * 员工类，抽象元素类
 *
 * Created by Administrator on 2017/5/24.
 */
public interface Employee {

    void accept(Department handler);//接受一个抽象访问者访问
}
