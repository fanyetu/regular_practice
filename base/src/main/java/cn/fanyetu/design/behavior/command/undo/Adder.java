package cn.fanyetu.design.behavior.command.undo;

/**
 * 加法类，请求接收者
 *
 * Created by zhanghaonan on 2017/4/11.
 */
public class Adder {

	private int num = 0;

	public int add(int value){
		num += value;
		return num;
	}
}
