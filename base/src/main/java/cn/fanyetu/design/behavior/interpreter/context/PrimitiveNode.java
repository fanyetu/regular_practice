package cn.fanyetu.design.behavior.interpreter.context;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 基础命令节点类，终结符表达式
 *
 * Created by zhanghaonan on 2017/4/13.
 */
public class PrimitiveNode implements Node {

	private String name;
	private String text;

	private String[] list = {"PRINT","SPACE","BREAK"};

	@Override
	public void interpret(Context context) {
		name = context.currentToken();
		context.nextToken();
		List<String> strings = Arrays.asList(list);
		if (!strings.contains(name.toUpperCase())){
			System.out.println("非法命令");
			throw new RuntimeException();
		}

		if ("PRINT".equalsIgnoreCase(name)){
			text = context.currentToken();
			context.nextToken();
		}
	}

	@Override
	public void execute() {
		switch (name.toUpperCase()){
			case "PRINT":
				System.out.print(text);
				break;
			case "SPACE":
				System.out.print(" ");
				break;
			case "BREAK":
				System.out.println();
				break;
		}
	}
}
