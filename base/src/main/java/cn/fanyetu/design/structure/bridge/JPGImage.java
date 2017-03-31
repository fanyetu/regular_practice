package cn.fanyetu.design.structure.bridge;

/**
 * Created by Administrator on 2017/3/30.
 */
public class JPGImage extends Image {

    /**
     *
     * @param fileName
     */
    @Override
    public void parseFile(String fileName) {

        Matrix matrix = new Matrix();
        imageImpl.doPaint(matrix);

        System.out.println(fileName + "，格式为JPG");

    }
}
