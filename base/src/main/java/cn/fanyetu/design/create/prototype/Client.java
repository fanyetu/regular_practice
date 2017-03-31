package cn.fanyetu.design.create.prototype;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 原型模式
 * 
 * 理解java中的原型模式，只需要理解java中的浅拷贝和深拷贝即可
 * @author zhanghaonan
 *
 */
public class Client {
	

	public static void main(String[] args) throws CloneNotSupportedException, IOException, ClassNotFoundException {
		
		ConcretePrototype p1 = new ConcretePrototype();
		
		p1.setName("test1");		
		ArrayList<String> list1 = new ArrayList<String>();
		list1.add("1");
		list1.add("2");
		p1.setNickNames(list1);
		
		System.out.println(p1);
		
		ConcretePrototype p2 = (ConcretePrototype)p1.clone();//浅拷贝，引用的对象任然是同一个地址
		p2.setName("test2");
		List<String> list2 = p2.getNickNames();
		list2.add("3");
		list2.add("4");
		
		System.out.println(p2);
		System.out.println(p1);
		
		ConcretePrototype p3 = (ConcretePrototype) p1.deepClone();//深拷贝，引用的对象不再是同一个地址
		p3.setName("test3");
		List<String> list3 = p3.getNickNames();
		list3.add("5");
		list3.add("6");
		
		System.out.println(p3);
		System.out.println(p2);
		System.out.println(p1);
		
	}

}
