package cn.fanyetu.design.behavior.command.log;

/**
 * 具体命令类
 *
 * Created by zhanghaonan on 2017/4/11.
 */
public class DeleteCommand extends Command {

	public DeleteCommand(String name) {
		super(name);
	}

	@Override
	public void execute(String args) {
		this.args = args;
		this.configOperator.delete(args);
	}

	@Override
	public void execute() {
		this.configOperator.delete(this.args);
	}
}