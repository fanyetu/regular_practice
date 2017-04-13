package cn.fanyetu.design.behavior.interpreter.context;

/**
 * 测试
 * <p>
 * Created by zhanghaonan on 2017/4/13.
 */
public class Client {

	public static void main(String[] args) {
		String text = "LOOP 2 PRINT 杨过 SPACE SPACE PRINT 小龙女 " +
				"BREAK END PRINT 郭靖 SPACE SPACE PRINT 黄蓉";
		Context context = new Context(text);

		Node node = new ExpressionNode();
		node.interpret(context);
		node.execute();
	}
}
