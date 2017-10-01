package cn.fanyetu.util.poi.excel;

import org.apache.poi.ss.usermodel.Cell;

/**
 * Created by zhanghaonan on 2017/9/26.
 */
public interface CellEditor {

	Object edit(Cell cell, Object value);
}
