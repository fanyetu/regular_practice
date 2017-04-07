package cn.fanyetu.design.structure.facade;

/**
 * Created by zhanghaonan on 2017/4/7.
 */
public class Client {

	public static void main(String[] args) {
		Facade facade = new Facade();
		facade.fileEncrypt("d:/source.txt","d:/target.txt");
	}
}
