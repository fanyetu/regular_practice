package cn.fanyetu.design.behavior.memento;

/**
 * 棋子备忘录管理类，充当负责人类
 *
 * Created by zhanghaonan on 2017/4/25.
 */
public class ChessmanCaretaker {

	private ChessmanMemento memento;

	public ChessmanMemento getMemento() {
		return memento;
	}

	public void setMemento(ChessmanMemento memento) {
		this.memento = memento;
	}
}
