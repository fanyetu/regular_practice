package cn.fanyetu.design.behavior.command.log;

/**
 * 具体命令类
 *
 * Created by zhanghaonan on 2017/4/11.
 */
public class UpdateCommand extends Command {

	public UpdateCommand(String name) {
		super(name);
	}

	@Override
	public void execute(String args) {
		this.args = args;
		this.configOperator.update(args);
	}

	@Override
	public void execute() {
		this.configOperator.update(this.args);
	}
}
