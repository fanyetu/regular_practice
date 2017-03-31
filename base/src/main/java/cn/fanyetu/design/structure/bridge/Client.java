package cn.fanyetu.design.structure.bridge;

/**
 * 桥接模式客户端测试类
 *
 * Created by Administrator on 2017/3/30.
 */
public class Client {

    public static void main(String[] args) {

        Image image = new JPGImage();
        ImageImpl image1 = new WindowsImageImpl();

        image.setImageImpl(image1);

        image.parseFile("d:/tom.jpg");
    }
}
