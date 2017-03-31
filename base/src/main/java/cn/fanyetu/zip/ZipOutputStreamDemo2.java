package cn.fanyetu.zip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipOutputStreamDemo2 {
	public static void main(String args[]) throws Exception { // �����쳣�׳�
		File file = new File("F:" + File.separator + "mldn"); // ����Ҫѹ�����ļ���
		File zipFile = new File("F:" + File.separator + "mldndir.zip"); // ����ѹ���ļ�����
		InputStream input = null; // �����ļ�������
		ZipOutputStream zipOut = null; // ����ѹ��������
		zipOut = new ZipOutputStream(new FileOutputStream(zipFile));
		zipOut.setComment("www.mldnjava.cn"); // ����ע��
		int temp = 0;
		if (file.isDirectory()) { // �ж��Ƿ����ļ���
			File lists[] = file.listFiles(); // �г�ȫ���ļ�
			for (int i = 0; i < lists.length; i++) {
				input = new FileInputStream(lists[i]); // �����ļ���������
				zipOut.putNextEntry(new ZipEntry(lists[i].getName())); // ����ZipEntry����
				while ((temp = input.read()) != -1) { // ��ȡ����
					zipOut.write(temp); // ѹ�����
				}
				input.close(); // �ر�������
			}
		}
		zipOut.close(); // �ر������
	}
}
