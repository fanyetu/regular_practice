package cn.fanyetu.zip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipOutpuStreamDemo1 {
	
	public static void main(String[] args) throws Exception {
		File file = new File("F:" + File.separator + "�����ļ�.txt") ;  // ����Ҫѹ�����ļ�  
        File zipFile = new File("F:" + File.separator + "mldn.zip") ;   // ����ѹ���ļ�����  
        InputStream input = new FileInputStream(file) ; // �����ļ���������  
        ZipOutputStream zipOut = null ; // ����ѹ��������  
        zipOut = new ZipOutputStream(new FileOutputStream(zipFile)) ;  
        
        zipOut.putNextEntry(new ZipEntry(file.getName())) ; // ����ZipEntry����  
        zipOut.setComment("www.mldnjava.cn") ;  // ����ע��  
        
        int temp = 0 ;  
        while((temp=input.read())!=-1){ // ��ȡ����  
            zipOut.write(temp) ;    // ѹ�����  
        }  
        input.close() ; // �ر�������  
        zipOut.close() ;    // �ر������  
	}

}
