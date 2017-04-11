package cn.fanyetu.design.behavior.command.queue;

import cn.fanyetu.design.behavior.command.concrete.Command;
import cn.fanyetu.design.behavior.command.concrete.HelpHandlerCommand;
import cn.fanyetu.design.behavior.command.concrete.WindowHandlerCommand;

/**
 * Created by zhanghaonan on 2017/4/11.
 */
public class Client {

	public static void main(String[] args) {
		FBSettingWindow fbSettingWindow = new FBSettingWindow("功能键设置");

		BatchFunctionButton functionButton = new BatchFunctionButton("批处理功能键");

		Command command1,command2;
		command1 = new WindowHandlerCommand();//真实环境下应该通过外部配置等实现
		command2 = new HelpHandlerCommand();

		//创建命令队列
		CommandQueue commandQueue = new CommandQueue();
		commandQueue.addCommand(command1);
		commandQueue.addCommand(command2);

		//为批处理请求者类添加具体的命令类
		functionButton.setCommandQueue(commandQueue);

		fbSettingWindow.addFunctionButton(functionButton);

		fbSettingWindow.display();

		//调用请求者的请求方法
		functionButton.onClick();
	}
}
