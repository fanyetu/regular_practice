package cn.fanyetu.design.behavior.command.concrete;

/**
 * 具体的命令类,窗口最小化命令类
 * Created by zhanghaonan on 2017/4/11.
 */
public class WindowHandlerCommand extends Command {

	//维持请求接收者的引用
	private WindowHandler windowHandler;

	public WindowHandlerCommand() {
		windowHandler = new WindowHandler();
	}

	//命令执行方法，将调用请求接收者的业务方法
	@Override
	public void execute() {
		windowHandler.minimize();
	}
}
