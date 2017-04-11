package cn.fanyetu.design.behavior.command.log;

import java.io.Serializable;

/**
 * 抽象命令类，由于要将命令写入到日志文件中，因此实现了Serializable类
 *
 * Created by zhanghaonan on 2017/4/11.
 */
public abstract class Command implements Serializable {

	private static final long serialVersionUID = 6018129797404520435L;

	protected String name;//命令名称
	protected String args;//命令参数

	protected ConfigOperator configOperator;//这里直接在抽象命令类中维持请求接受者的引用

	public Command(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	//注入请求接收者
	public void setConfigOperator(ConfigOperator configOperator) {
		this.configOperator = configOperator;
	}

	//声明执行方法
	public abstract void execute(String args);
	public abstract void execute();
}
