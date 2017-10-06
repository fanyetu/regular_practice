package cn.fanyetu.util.poi.excel;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by zhanghaonan on 2017/9/26.
 */
public class ExcelReaderTest {

	/*
	java 的值传递和引用传递
	 */
	public static void change(AtomicInteger a, AtomicInteger b){
		Integer temp = a.get();
		a.set(b.get());
		b.set(temp);
	}

	@Test
	public void test1(){
		AtomicInteger a = new AtomicInteger(5);
		AtomicInteger b = new AtomicInteger(3);

		change(a,b);

		System.out.println(a);
		System.out.println(b);
	}
}
