package cn.fanyetu.design.structure.proxy.concrete;

/**
 * 客户端测试类
 *
 * Created by zhanghaonan on 2017/4/10.
 */
public class Client {

	public static void main(String[] args) {

		//这里应该读取配置文件之类的，通过配置的方式生成代理对象的实例
		Searcher searcher = new ProxySearcher();

		String s = searcher.doSearch("杨过", "测试");
		System.out.println(s);
	}
}
