package cn.fanyetu.zip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipOutputStreamDemo2 {
	public static void main(String args[]) throws Exception { // 所有异常抛出
		File file = new File("F:" + File.separator + "mldn"); // 定义要压缩的文件夹
		File zipFile = new File("F:" + File.separator + "mldndir.zip"); // 定义压缩文件名称
		InputStream input = null; // 定义文件输入流
		ZipOutputStream zipOut = null; // 声明压缩流对象
		zipOut = new ZipOutputStream(new FileOutputStream(zipFile));
		zipOut.setComment("www.mldnjava.cn"); // 设置注释
		int temp = 0;
		if (file.isDirectory()) { // 判断是否是文件夹
			File lists[] = file.listFiles(); // 列出全部文件
			for (int i = 0; i < lists.length; i++) {
				input = new FileInputStream(lists[i]); // 定义文件的输入流
				zipOut.putNextEntry(new ZipEntry(lists[i].getName())); // 设置ZipEntry对象
				while ((temp = input.read()) != -1) { // 读取内容
					zipOut.write(temp); // 压缩输出
				}
				input.close(); // 关闭输入流
			}
		}
		zipOut.close(); // 关闭输出流
	}
}
