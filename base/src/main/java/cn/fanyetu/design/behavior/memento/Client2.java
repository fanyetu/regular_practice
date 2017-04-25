package cn.fanyetu.design.behavior.memento;

/**
 * 测试多次撤销
 * Created by Administrator on 2017/4/25.
 */
public class Client2 {

    private static int index = -1;//记录当前状态保持的位置
    private static ChessmanListCaretaker ck = new ChessmanListCaretaker();//创建负责人类

    public static void main(String[] args) {
        Chessman chessman = new Chessman("帅", 1, 1);

        display(chessman);

        chessman.setX(5);
        display(chessman);

        chessman.setY(4);
        display(chessman);

        undo(chessman);
        undo(chessman);

        redo(chessman);
        redo(chessman);

    }

    public static void display(Chessman chess) {
        ck.setMemento(chess.save());
        index++;
        System.out.println("棋子" + chess.getLabel() + "当前位置为：" + "第" + chess.getX() + "行" + "第" + chess.getY() + "列。");
    }

    /**
     * 悔棋
     * @param chess
     */
    public static void undo(Chessman chess){
        System.out.println("=======悔棋========");
        ChessmanMemento memento = ck.getMemento(--index);
        chess.restore(memento);

        System.out.println("棋子" + chess.getLabel() + "当前位置为：" + "第" + chess.getX() + "行" + "第" + chess.getY() + "列。");
    }

    /**
     * 撤销悔棋
     * @param chess
     */
    public static void redo(Chessman chess){
        System.out.println("=======撤销悔棋=======");
        ChessmanMemento memento = ck.getMemento(++index);
        chess.restore(memento);

        System.out.println("棋子" + chess.getLabel() + "当前位置为：" + "第" + chess.getX() + "行" + "第" + chess.getY() + "列。");
    }
}
