package cn.fanyetu.design.behavior.command.concrete;

/**
 * 功能键类，请求者类
 *
 * Created by zhanghaonan on 2017/4/11.
 */
public class FunctionButton {

	//功能键的名称
	private String name;

	private Command command;//维持抽象命令类的引用

	public FunctionButton(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	//注入抽象命令类
	public void setCommand(Command command) {
		this.command = command;
	}

	//发送请求的方法
	public void onClick(){
		System.out.println("点击功能键:"+this.getName());
		command.execute();
	}
}
