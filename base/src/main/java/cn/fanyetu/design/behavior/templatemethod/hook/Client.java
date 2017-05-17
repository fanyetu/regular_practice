package cn.fanyetu.design.behavior.templatemethod.hook;

/**
 * Created by Administrator on 2017/5/17.
 */
public class Client {

    public static void main(String[] args) {
        DataViewer dv;
        dv = new XmlDataViewer();

        dv.process();

        dv = new JsonDataViewer();
        dv.process();
    }
}
