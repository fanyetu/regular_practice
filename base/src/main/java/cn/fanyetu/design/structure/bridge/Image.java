package cn.fanyetu.design.structure.bridge;

/**
 * 抽象图像类
 *
 * Created by Administrator on 2017/3/30.
 */
public abstract class Image {

    protected ImageImpl imageImpl;

    public void setImageImpl(ImageImpl imageImpl){
        this.imageImpl = imageImpl;
    }

    public abstract void parseFile(String fileName);
}
