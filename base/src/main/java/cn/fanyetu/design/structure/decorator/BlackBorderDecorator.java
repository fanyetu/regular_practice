package cn.fanyetu.design.structure.decorator;

/**
 * 黑色边框装饰类，具体的装饰类
 *
 * Created by Administrator on 2017/4/6.
 */
public class BlackBorderDecorator extends ComponentDecorator{

    public BlackBorderDecorator(Component component) {
        super(component);
    }

    @Override
    public void display() {
        this.addBlackBorder();
        super.display();
    }

    //新增的业务方法
    public void addBlackBorder(){
        System.out.println("为构件添加黑色边框!");
    }
}
