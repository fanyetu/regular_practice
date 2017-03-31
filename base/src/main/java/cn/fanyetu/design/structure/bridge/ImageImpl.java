package cn.fanyetu.design.structure.bridge;

/**
 * 操作系统图像实现接口类
 *
 * Created by Administrator on 2017/3/30.
 */
public interface ImageImpl {

    /**
     * 显示像素矩阵
     * @param matrix
     */
    public void doPaint(Matrix matrix);
}
