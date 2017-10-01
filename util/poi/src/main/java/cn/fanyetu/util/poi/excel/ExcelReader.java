package cn.fanyetu.util.poi.excel;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhanghaonan on 2017/9/26.
 */
public class ExcelReader {


	/**
	 * excel 中对应的sheet
	 */
	private Sheet sheet;

	/**
	 * 是否忽略空行
	 */
	private boolean ignoreEmptyRow = false;

	/**
	 * 构造
	 *
	 * @param sheet
	 */
	public ExcelReader(Sheet sheet) {
		this.sheet = sheet;
	}

	/**
	 * 是否忽略空行
	 *
	 * @return
	 */
	public boolean isIgnoreEmptyRow() {
		return ignoreEmptyRow;
	}

	/**
	 * 设置是否忽略空行
	 *
	 * @param ignoreEmptyRow
	 * @return
	 */
	public ExcelReader setIgnoreEmptyRow(boolean ignoreEmptyRow) {
		this.ignoreEmptyRow = ignoreEmptyRow;
		return this;
	}

	/**
	 * 读取指定sheet的行列数据
	 * @return
	 */
	public List<List<Object>> read() {
		return read(0, Integer.MAX_VALUE);
	}

	/**
	 * 读取指定sheet的行列数据
	 *
	 * @param startRowIndex 起始行，从0开始
	 * @param endRowIndex
	 * @return
	 */
	public List<List<Object>> read(int startRowIndex, int endRowIndex) {
		final List<List<Object>> results = new ArrayList<>();

		startRowIndex = Math.max(startRowIndex, sheet.getFirstRowNum());
		endRowIndex = Math.min(endRowIndex, sheet.getLastRowNum());

		List<Object> rowValues;
		for (int i = startRowIndex; i < endRowIndex; i++) {
			Row row = sheet.getRow(i);
			rowValues = readRow(row);
			if (!rowValues.isEmpty() || !ignoreEmptyRow) {
				results.add(rowValues);
			}
		}
		return results;
	}

	/**
	 * 读取行数据
	 *
	 * @param row
	 * @return
	 */
	private List<Object> readRow(Row row) {
		final List<Object> rowValues = new ArrayList<>();

		short length = row.getLastCellNum();
		for (short i = 0; i < length; i++) {
			rowValues.add(ExcelUtil.getCellValue(row.getCell(i), null));
		}

		return rowValues;
	}
}
