package cn.fanyetu.design.behavior.memento;

import java.util.ArrayList;
import java.util.List;

/**
 * 负责人类，通过list存储备忘录对象，实现多次撤销
 *
 * Created by zhanghaonan on 2017/4/25.
 */
public class ChessmanListCaretaker {

    private List<ChessmanMemento> list = new ArrayList<>();

    public ChessmanMemento getMemento(int index){
        return list.get(index);
    }

    public void setMemento(ChessmanMemento memento){
        list.add(memento);
    }
}
