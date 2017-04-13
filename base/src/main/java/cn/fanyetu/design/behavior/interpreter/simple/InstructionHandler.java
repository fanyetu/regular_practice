package cn.fanyetu.design.behavior.interpreter.simple;

import java.util.Stack;

/**
 * 指令处理工具类
 * <p>
 * Created by zhanghaonan on 2017/4/13.
 */
public class InstructionHandler {
	private AbstractNode node;

	/**
	 * 处理字符串指令
	 *
	 * @param instruction
	 */
	public void handler(String instruction) {
		AbstractNode left, right;
		AbstractNode direction, action, distance;

		String[] words = instruction.split(" ");//通过空格分隔

		Stack<AbstractNode> stack = new Stack<>();

		for (int i = 0; i < words.length; i++) {
			if ("and".equalsIgnoreCase(words[i])) {
				//如果是and
				left = stack.pop();//将之前的表达式取出

				//取出and之后的3个指令
				String word1 = words[++i];
				direction = new DirectionNode(word1);
				String word2 = words[++i];//对i进行递增
				action = new ActionNode(word2);
				String word3 = words[++i];
				distance = new DistanceNode(word3);

				right = new SentenceNode(direction,action,distance);

				stack.push(new AndNode(left,right));//将and表达式压入栈中

			} else {
				//如果不是and
				String word1 = words[i];
				direction = new DirectionNode(word1);
				String word2 = words[++i];//对i进行递增
				action = new ActionNode(word2);
				String word3 = words[++i];
				distance = new DistanceNode(word3);

				left = new SentenceNode(direction,action,distance);

				stack.push(left);//将新表达式压入栈中
			}
		}

		this.node = stack.pop();//将表达式全部从栈中取出
	}

	/**
	 * 输出处理结果
	 * @return
	 */
	public String output(){
		return this.node.interpret();
	}
}
