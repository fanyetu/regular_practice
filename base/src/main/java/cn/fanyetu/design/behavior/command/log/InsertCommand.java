package cn.fanyetu.design.behavior.command.log;

/**
 * 具体命令类
 *
 * Created by zhanghaonan on 2017/4/11.
 */
public class InsertCommand extends Command {

	public InsertCommand(String name) {
		super(name);
	}

	@Override
	public void execute(String args) {
		this.args = args;
		this.configOperator.insert(args);
	}

	@Override
	public void execute() {
		this.configOperator.insert(this.args);
	}
}
