package cn.fanyetu.design.behavior.command.undo;

/**
 * 请求发送者
 * <p>
 * Created by zhanghaonan on 2017/4/11.
 */
public class CalculatorForm {

	private Command command;

	public void setCommand(Command command) {
		this.command = command;
	}

	public void compute(int value) {
		int result = command.execute(value);
		System.out.println("执行计算，计算结果为:" + result);
	}

	public void undo() {
		int undo = command.undo();
		System.out.println("执行撤销，撤销结果为:" + undo);
	}
}
