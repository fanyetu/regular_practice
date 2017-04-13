package cn.fanyetu.design.behavior.interpreter.simple;

/**
 * Created by zhanghaonan on 2017/4/13.
 */
public class Client {

	public static void main(String[] args) {
		String instruction = "up move 5 and down run 10 and left move 4 and right run 15";
		InstructionHandler instructionHandler = new InstructionHandler();

		instructionHandler.handler(instruction);
		System.out.println(instructionHandler.output());
	}
}
