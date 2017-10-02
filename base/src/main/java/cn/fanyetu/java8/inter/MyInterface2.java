package cn.fanyetu.java8.inter;

/**
 * Created by zhanghaonan on 2017/10/2.
 */
public interface MyInterface2 {

	default String show(){
		return "running in MyInterface2";
	}
}
