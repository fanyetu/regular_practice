package cn.fanyetu.design.behavior.interpreter.simple;

/**
 * 一个句子的非终结符表达式
 * <p>
 * Created by zhanghaonan on 2017/4/13.
 */
public class SentenceNode implements AbstractNode {

	private AbstractNode directionNode;
	private AbstractNode actionNode;
	private AbstractNode distanceNode;

	public SentenceNode(AbstractNode directionNode, AbstractNode actionNode, AbstractNode
			distanceNode) {
		this.directionNode = directionNode;
		this.actionNode = actionNode;
		this.distanceNode = distanceNode;
	}

	@Override
	public String interpret() {
		return directionNode.interpret() + actionNode.interpret() + distanceNode.interpret();
	}
}
