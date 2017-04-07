package cn.fanyetu.design.structure.decorator;

/**
 * 滚动条装饰类，具体的装饰类
 *
 * Created by Administrator on 2017/4/6.
 */
public class ScrollBarDecorator extends ComponentDecorator {

    public ScrollBarDecorator(Component component) {
        super(component);
    }

    @Override
    public void display() {
        this.setScrollBar();
        super.display();
    }

    //新增的业务方法
    public void setScrollBar(){
        System.out.println("为构件增加滚动条!");
    }
}
