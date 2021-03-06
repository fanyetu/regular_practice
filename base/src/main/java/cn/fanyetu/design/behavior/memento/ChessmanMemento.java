package cn.fanyetu.design.behavior.memento;

/**
 * 棋子备忘录类，充当备忘录类
 *
 * 只有原发器能修改备忘录对象，负责人对象只负责管理
 * 其他类无法访问备忘录对象
 *
 * Created by zhanghaonan on 2017/4/25.
 */
class ChessmanMemento {

	private String lable;
	private int x;
	private int y;

	public ChessmanMemento(String lable, int x, int y) {
		this.lable = lable;
		this.x = x;
		this.y = y;
	}

	String getLable() {
		return lable;
	}

	void setLable(String lable) {
		this.lable = lable;
	}

	int getX() {
		return x;
	}

	void setX(int x) {
		this.x = x;
	}

	int getY() {
		return y;
	}

	void setY(int y) {
		this.y = y;
	}
}
