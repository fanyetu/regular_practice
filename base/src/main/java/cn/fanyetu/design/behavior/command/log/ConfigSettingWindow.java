package cn.fanyetu.design.behavior.command.log;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 配置文件设置窗口，请求发起类
 *
 * Created by zhanghaonan on 2017/4/11.
 */
public class ConfigSettingWindow {

	//定义一个列表存储每次操作的命令对象
	private List<Command> commands = new ArrayList<>();

	private Command command;

	//注入具体的命令类
	public void setCommand(Command command) {
		this.command = command;
	}

	/**
	 * 执行配置文件修改命令，同时将执行命令添加到命令列表中
	 * @param args
	 */
	public void call(String args){
		this.command.execute(args);
		this.commands.add(command);
	}

	/**
	 * 记录请求日志，生成日志文件，将命令集合写入到日志文件中
	 */
	public void save(){
		FileUtil.writeCommandsToFile(this.commands);
	}

	/**
	 * 从日志文件中提取命令集合，并执行execute方法
	 */
	public void recover(){
		List<Command> commands = FileUtil.readCommandFromFile();
		if (commands != null) {
			for (Command command : commands){
				command.execute();
			}
		}
	}
}
