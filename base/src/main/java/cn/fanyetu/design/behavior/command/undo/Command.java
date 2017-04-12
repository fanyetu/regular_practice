package cn.fanyetu.design.behavior.command.undo;

/**
 * 抽象命令类
 *
 * Created by zhanghaonan on 2017/4/11.
 */
public interface Command {

	int execute(int value);
	int undo();
}
