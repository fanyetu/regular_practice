package cn.fanyetu.design.behavior.command.concrete;

/**
 * 命令模式测试
 *
 * Created by zhanghaonan on 2017/4/11.
 */
public class Client {

	public static void main(String[] args) {
		FBSettingWindow fbSettingWindow = new FBSettingWindow("功能键设置");

		FunctionButton functionButton1,functionButton2;
		functionButton1 = new FunctionButton("功能键1");
		functionButton2 = new FunctionButton("功能键2");

		Command command1,command2;
		command1 = new WindowHandlerCommand();//真实环境下应该通过外部配置等实现
		command2 = new HelpHandlerCommand();

		//为请求者类添加具体的命令类
		functionButton1.setCommand(command1);
		functionButton2.setCommand(command2);

		fbSettingWindow.addFunctionButton(functionButton1);
		fbSettingWindow.addFunctionButton(functionButton2);

		fbSettingWindow.display();

		//调用请求者的请求方法
		functionButton1.onClick();
		functionButton2.onClick();

	}
}
