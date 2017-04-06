package cn.fanyetu.design.structure.decorator;

/**
 * Created by Administrator on 2017/4/6.
 */
public class Client {

    public static void main(String[] args) {
        Component componentA,componentB,componentC;

        componentA = new Window();
        componentB = new ScrollBarDecorator(componentA);
        componentC = new BlackBorderDecorator(componentB);//将之前装饰的对象继续注入，进行二次装饰

        componentC.display();
    }

}
