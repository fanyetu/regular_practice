package cn.fanyetu.design.structure.proxy.concrete;

/**
 * 日志类
 * <p>
 * Created by zhanghaonan on 2017/4/10.
 */
public class Logger {

	public void log(String username) {
		System.out.println("更新数据库，用户" + username + "查询次数加1");
	}

}
