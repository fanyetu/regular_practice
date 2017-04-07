package cn.fanyetu.design.structure.facade;

/**
 * 文件写入类,充当子系统
 *
 * Created by zhanghaonan on 2017/4/7.
 */
public class FileWriter {

	public void write(String content,String fileNameDes){
		System.out.println("文件写入中"+ fileNameDes +".......,写入内容为"+content);
	}
}
