package cn.fanyetu.design.behavior.command.undo;

/**
 *
 * Created by zhanghaonan on 2017/4/11.
 */
public class Client {

	public static void main(String[] args) {
		CalculatorForm calculatorForm = new CalculatorForm();

		Command command = new AddCommand();

		calculatorForm.setCommand(command);//向请求发送者注入命令对象

		calculatorForm.compute(10);
		calculatorForm.compute(5);
		calculatorForm.compute(1);

		calculatorForm.undo();
	}
}
