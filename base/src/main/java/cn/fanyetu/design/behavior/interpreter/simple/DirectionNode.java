package cn.fanyetu.design.behavior.interpreter.simple;

/**
 * 方向，终结符表达式
 *
 * Created by zhanghaonan on 2017/4/13.
 */
public class DirectionNode implements AbstractNode{

	private String direction;

	public DirectionNode(String direction) {
		this.direction = direction;
	}

	@Override
	public String interpret() {
		String result;
		switch (direction){
			case "up":
				result = "向上";
				break;
			case "down":
				result = "向下";
				break;
			case "left":
				result = "向左";
				break;
			case "right":
				result = "向右";
				break;
			default:
				result = "无效指令";
				break;
		}
		return result;
	}
}
