package cn.fanyetu.design.behavior.templatemethod.hook;

/**
 * Created by Administrator on 2017/5/17.
 */
public class XmlDataViewer extends DataViewer {

    @Override
    public void getData() {
        System.out.println("从文本文件中获取数据");
    }

    @Override
    public void displayData() {
        System.out.println("显示数据");
    }

    /**
     * 覆盖钩子方法
     * @return
     */
    @Override
    public boolean isNotXmlData() {
        return false;
    }
}
