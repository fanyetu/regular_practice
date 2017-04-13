package cn.fanyetu.design.behavior.interpreter.simple;

/**
 * 距离，终结符表达式
 *
 * Created by zhanghaonan on 2017/4/13.
 */
public class DistanceNode implements AbstractNode{

	private String distance;

	public DistanceNode(String distance) {
		this.distance = distance;
	}

	@Override
	public String interpret() {
		return this.distance;
	}
}
