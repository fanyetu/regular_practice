package cn.fanyetu.design.structure.facade;

/**
 * 文件读取类，充当子系统
 * <p>
 * Created by zhanghaonan on 2017/4/7.
 */
public class FileReader {

	public String read(String fileNameSrc) {
		System.out.println("读取文件" + fileNameSrc + "......");
		return "测试";//测试返回一个固定值
	}
}
