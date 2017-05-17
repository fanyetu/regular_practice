package cn.fanyetu.design.behavior.templatemethod.hook;

/**
 * Created by Administrator on 2017/5/17.
 */
public class JsonDataViewer extends DataViewer {
    @Override
    public void getData() {
        System.out.println("从数据库文件中获取数据");
    }

    @Override
    public void displayData() {
        System.out.println("显示数据");
    }
}
