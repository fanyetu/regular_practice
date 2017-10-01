package cn.fanyetu.util.poi.excel;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;

/**
 * excel工具类
 * <p>
 * Created by zhanghaonan on 2017/9/26.
 */
public class ExcelUtil {

	public static Object getCellValue(Cell cell, CellEditor cellEditor) {
		return getCellValue(cell, cell.getCellTypeEnum(), cellEditor);
	}

	public static Object getCellValue(Cell cell, CellType cellType, CellEditor cellEditor) {
		if (null == cell) {
			return null;
		}

		// 单元格的类型
		if (null == cellType) {
			cellType = cell.getCellTypeEnum();
		}

		Object value;
		switch (cellType) {
			case NUMERIC:
				value = null;
				break;
			case BOOLEAN:
				value = cell.getBooleanCellValue();
				break;
			case FORMULA:
				value = getCellValue(cell, cell.getCachedFormulaResultTypeEnum(), cellEditor);
				break;
			case BLANK:
				value = StringUtils.EMPTY;
				break;
			default:
				value = cell.getStringCellValue();
				break;
		}

		return null == cellEditor ? value : cellEditor.edit(cell,value);
	}
}
