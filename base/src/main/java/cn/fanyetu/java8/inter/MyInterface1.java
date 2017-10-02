package cn.fanyetu.java8.inter;

/**
 * Created by zhanghaonan on 2017/10/2.
 */
public interface MyInterface1 {

	default String show(){
		return "running in MyInterface1";
	}

	static void display(){
		System.out.println("MyInterface1's static method");
	}
}
