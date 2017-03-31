package cn.fanyetu.design.structure.bridge;

/**
 * Created by Administrator on 2017/3/30.
 */
public class LinuxImageImpl implements ImageImpl {
    @Override
    public void doPaint(Matrix matrix) {

        //调用Linux系统的绘制函数绘制像素矩阵
        System.out.print("在Linux操作系统中显示图像：");

    }
}
