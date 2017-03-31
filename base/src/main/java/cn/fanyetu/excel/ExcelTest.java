package cn.fanyetu.excel;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;


public class ExcelTest {

	public static void main(String[] args) throws IOException {
		HSSFWorkbook book = new HSSFWorkbook();
		FileOutputStream out = new FileOutputStream("E:/xxx.xls");
		book.write(out);
		out.close();
	}
}
