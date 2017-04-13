package cn.fanyetu.design.behavior.interpreter.context;

import java.util.ArrayList;
import java.util.List;

/**
 * 表达式节点类，非终结符表达式
 *
 * Created by zhanghaonan on 2017/4/13.
 */
public class ExpressionNode implements Node {

	private List<Node> list = new ArrayList<>();//用一个list存储命令集合

	/**
	 * 解释方法
	 * @param context
	 */
	@Override
	public void interpret(Context context) {
		//循环处理上下文的标记
		while (true){
			//如果没有标记了就退出解释
			if (context.currentToken() == null){
				break;
			}else if ("end".equalsIgnoreCase(context.currentToken())){
				//如果是end标记就跳过
				context.nextToken();
				break;
			}else {
				Node commandNode = new CommandNode();
				commandNode.interpret(context);
				list.add(commandNode);//将标记添加到命令集合中
			}
		}
	}

	/**
	 * 执行方法
	 */
	@Override
	public void execute() {
		for (Node node : list){
			node.execute();
		}
	}
}
