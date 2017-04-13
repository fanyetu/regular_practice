package cn.fanyetu.design.behavior.interpreter.context;

/**
 * 语句命令节点类，非终结符表达式
 *
 * Created by zhanghaonan on 2017/4/13.
 */
public class CommandNode implements Node {

	private Node node;

	@Override
	public void interpret(Context context) {
		//处理LOOP循环
		if ("loop".equalsIgnoreCase(context.currentToken())){
			node = new LoopCommandNode();
			node.interpret(context);
		} else {
			node = new PrimitiveNode();
			node.interpret(context);
		}
	}

	@Override
	public void execute() {
		node.execute();
	}
}
