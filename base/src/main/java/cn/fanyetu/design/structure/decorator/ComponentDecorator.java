package cn.fanyetu.design.structure.decorator;

/**
 * 构件装饰类，抽象装饰类
 *
 * Created by Administrator on 2017/4/6.
 */
public class ComponentDecorator extends Component{

    private Component component;

    public ComponentDecorator(Component component) {
        this.component = component;
    }

    @Override
    public void display() {
        component.display();
    }
}
