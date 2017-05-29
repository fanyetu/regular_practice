package cn.fanyetu.design.behavior.templatemethod.hook;

/**
 * 模板方法抽象类
 *
 * Created by Administrator on 2017/5/17.
 */
public abstract class DataViewer {

    /**
     * 抽象方法，获取数据
     */
    public abstract void getData();

    /**
     * 具体方法，转换数据
     */
    public void convertData(){
        System.out.println("将数据转换为xml格式");
    }

    /**
     * 抽象方法，显示数据
     */
    public abstract void displayData();

    /**
     * 钩子方法，判断是否为xml数据
     */
    public boolean isNotXmlData(){
        return true;
    }

    /**
     * 模板方法
     */
    public void process(){
        getData();
        if (isNotXmlData()){
            convertData();
        }
        displayData();
    }
}
