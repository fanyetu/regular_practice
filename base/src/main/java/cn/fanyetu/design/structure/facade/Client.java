package cn.fanyetu.design.structure.facade;

/**
 * Created by zhanghaonan on 2017/4/7.
 */
public class Client {

	public static void main(String[] args) {
		AbstractFacade simpleFacade = new SimpleFacade();
		simpleFacade.fileEncrypt("d:/source.txt","d:/target.txt");

		AbstractFacade newSimpleFacade = new NewSimpleFacade();
		newSimpleFacade.fileEncrypt("d:/source.txt","d:/target.txt");
	}
}
