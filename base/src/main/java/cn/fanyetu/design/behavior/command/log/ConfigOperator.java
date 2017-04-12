package cn.fanyetu.design.behavior.command.log;

import java.io.Serializable;

/**
 * 配置文件操作类：请求接收者
 * <p>
 * Created by zhanghaonan on 2017/4/11.
 */
public class ConfigOperator implements Serializable {

	private static final long serialVersionUID = 9110140043132126141L;

	public void insert(String args) {
		System.out.println("增加节点：" + args);
	}

	public void update(String args) {
		System.out.println("修改节点：" + args);
	}

	public void delete(String args) {
		System.out.println("删除节点：" + args);
	}
}
