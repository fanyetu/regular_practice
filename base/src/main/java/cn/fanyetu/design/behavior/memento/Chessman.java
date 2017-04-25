package cn.fanyetu.design.behavior.memento;

/**
 * 棋子类，充当原发器类
 * <p>
 * Created by zhanghaonan on 2017/4/25.
 */
public class Chessman {

	private String label;
	private int x;
	private int y;

	public Chessman(String lable, int x, int y) {
		this.label = lable;
		this.x = x;
		this.y = y;
	}

	/**
	 * 保存状态
	 * @return
	 */
	public ChessmanMemento save(){
		return new ChessmanMemento(this.label,this.x,this.y);
	}

	/**
	 * 恢复状态
	 * @param memento
	 */
	public void restore(ChessmanMemento memento){
		this.label = memento.getLable();
		this.x = memento.getX();
		this.y = memento.getY();
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}
