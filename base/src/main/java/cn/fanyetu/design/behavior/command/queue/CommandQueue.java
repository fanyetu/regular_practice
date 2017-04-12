package cn.fanyetu.design.behavior.command.queue;

import cn.fanyetu.design.behavior.command.concrete.Command;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhanghaonan on 2017/4/11.
 */
public class CommandQueue {

	//定义一个list来存储队列(这里使用的数据接口都是可以改变的)
	private List<Command> commands = new ArrayList<>();

	public void addCommand(Command command){
		this.commands.add(command);
	}

	public void removeCommand(Command command){
		this.commands.remove(command);
	}

	//循环调用每一个命令对象的execute()方法
	public void execute(){
		for (Command command : commands){
			command.execute();
		}
	}
}
