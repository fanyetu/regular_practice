package cn.fanyetu.design.behavior.interpreter.simple;

/**
 * 动作，终结符表达式
 * Created by zhanghaonan on 2017/4/13.
 */
public class ActionNode implements AbstractNode{

	private String action;

	public ActionNode(String action) {
		this.action = action;
	}

	/**
	 * 对动作指令进行解释
	 * @return
	 */
	@Override
	public String interpret() {
		String result;
		switch (action){
			case "move":
				result = "移动";
				break;
			case "run":
				result = "快速移动";
				break;
			default:
				result = "无效指令";
				break;
		}
		return result;
	}
}
