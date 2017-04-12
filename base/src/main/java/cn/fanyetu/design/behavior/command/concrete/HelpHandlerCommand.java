package cn.fanyetu.design.behavior.command.concrete;

/**
 * 帮助文档命令类，具体的命令类
 *
 * Created by zhanghaonan on 2017/4/11.
 */
public class HelpHandlerCommand extends Command{

	private HelpHandler helpHandler;

	public HelpHandlerCommand() {
		helpHandler = new HelpHandler();
	}

	//命令处理方法
	@Override
	public void execute() {
		helpHandler.help();
	}
}
