package cn.fanyetu.design.structure.bridge;

/**
 * windows绘制图片实现类
 *
 * Created by Administrator on 2017/3/30.
 */
public class WindowsImageImpl implements ImageImpl {

    @Override
    public void doPaint(Matrix matrix) {
        //调用Windows系统的绘制函数绘制像素矩阵
        System.out.print("在Windows操作系统中显示图像：");
    }
}
