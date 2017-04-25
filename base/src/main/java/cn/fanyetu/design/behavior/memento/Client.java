package cn.fanyetu.design.behavior.memento;

/**
 * 测试备忘录模式
 * <p>
 * Created by zhanghaonan on 2017/4/25.
 */
public class Client {

	public static void main(String[] args) {
		//创建负责人类
		ChessmanCaretaker caretaker = new ChessmanCaretaker();

		Chessman chessman = new Chessman("車",1,1);
		display(chessman);
		caretaker.setMemento(chessman.save());//保存状态

		chessman.setX(5);
		display(chessman);
		caretaker.setMemento(chessman.save());

		chessman.setY(4);
		display(chessman);

		System.out.println("悔棋=============");

		chessman.restore(caretaker.getMemento());//恢复状态
		display(chessman);

	}

	public static void display(Chessman chess) {
		System.out.println("棋子\"" + chess.getLabel() + "\"当前位置为：" + "第" + chess.getX() + "行" + "第" +
				chess.getY() + "列。");
	}
}
