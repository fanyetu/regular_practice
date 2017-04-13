package cn.fanyetu.design.behavior.interpreter.context;

/**
 * 循环命令节点类，非终结符表达式
 * <p>
 * Created by zhanghaonan on 2017/4/13.
 */
public class LoopCommandNode implements Node {

	private int number;//循环次数
	private Node node;//循环中的表达式

	@Override
	public void interpret(Context context) {
		context.nextToken();//跳过loop
		number = context.currentNumber();
		context.nextToken();//跳过number
		node = new ExpressionNode();
		node.interpret(context);
	}

	@Override
	public void execute() {
		for (int i=0;i<number;i++)
			node.execute();
	}
}
