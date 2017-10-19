package cn.fanyetu.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class ExcelUtil {

    public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
    	Scanner input=new Scanner(System.in);
    	System.out.println("请输入日期转换格式");
    	String format = input.nextLine();
    	
    	File file = new File("d:/1.xls");
        POIFSFileSystem poifsFileSystem = new POIFSFileSystem(new       FileInputStream(file));
        HSSFWorkbook hssfWorkbook =  new HSSFWorkbook(poifsFileSystem);
        HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);

        int rowstart = hssfSheet.getFirstRowNum();
        int rowEnd = hssfSheet.getLastRowNum();
        List<Integer> warningList = new ArrayList<Integer>();
        List<Integer> errorList = new ArrayList<Integer>();
        for(int i=rowstart;i<=rowEnd;i++)
        {
            HSSFRow row = hssfSheet.getRow(i);
            if(null == row) continue;
            int cellStart = row.getFirstCellNum();
            int cellEnd = row.getLastCellNum();
            
            String start = null;
            String end = null;

            for(int k=cellStart;k<=cellEnd;k++)
            {
                HSSFCell cell = row.getCell(k);
                if(null==cell) continue;
//                System.out.print("" + k + "  ");
                //System.out.print("type:"+cell.getCellType());
                if(k == 6){
                	start = cell.getStringCellValue();
                }
                if(k == 7){
                	end = cell.getStringCellValue();
                }

//                switch (cell.getCellType())
//                {
//                    case HSSFCell.CELL_TYPE_NUMERIC: // 数字
//                                    System.out.print(cell.getNumericCellValue()
//                                + "   ");
//                        break;
//                    case HSSFCell.CELL_TYPE_STRING: // 字符串
//                        System.out.print(cell.getStringCellValue()
//                                + "   ");
//                        break;
//                    case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean
//                        System.out.println(cell.getBooleanCellValue()
//                                + "   ");
//                        break;
//                    case HSSFCell.CELL_TYPE_FORMULA: // 公式
//                        System.out.print(cell.getCellFormula() + "   ");
//                        break;
//                    case HSSFCell.CELL_TYPE_BLANK: // 空值
//                        System.out.println(" ");
//                        break;
//                    case HSSFCell.CELL_TYPE_ERROR: // 故障
//                        System.out.println(" ");
//                        break;
//                    default:
//                        System.out.print("未知类型   ");
//                        break;
//                }
            }
            
            System.out.println(start + ":" + end);
            if(i > 0){
            	try{
		            SimpleDateFormat sdf = new SimpleDateFormat(format);
		            Date e = sdf.parse(end);
		            Calendar rightNow = Calendar.getInstance();
		    		rightNow.setTime(e);
		    		rightNow.add(Calendar.YEAR, -1);// 日期减1年
		    		Date dt1 = rightNow.getTime();
		    		
		    		Date s = sdf.parse(start);
		    		
		    		if(s.getTime() - dt1.getTime() != (24 * 60 * 60 * 1000) && s.getTime() - dt1.getTime() != 0){
		    			System.out.println(i+1+"===============================warning======================");
		    			warningList.add(i+1);
		    		}
            	}catch(Exception e){
            		System.out.println(i+1+"+++++++++++++++++++error+++++++++++++++++++++");
            		errorList.add(i+1);
            	}
            }
            System.out.print("\n");
        }
        
        System.out.println("不符合条件的行号为:"+warningList);
        System.out.println("可能有错误的行号为:"+errorList);
	}
}