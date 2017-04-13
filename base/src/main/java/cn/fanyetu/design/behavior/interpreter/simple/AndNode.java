package cn.fanyetu.design.behavior.interpreter.simple;

/**
 * 非终结符表达式 and
 *
 * Created by zhanghaonan on 2017/4/13.
 */
public class AndNode implements AbstractNode {

	private AbstractNode left;
	private AbstractNode right;

	public AndNode(AbstractNode left, AbstractNode right) {
		this.left = left;
		this.right = right;
	}

	@Override
	public String interpret() {
		return left.interpret() + "再" + right.interpret();
	}
}
