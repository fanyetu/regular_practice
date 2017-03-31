package cn.fanyetu.design.structure.adaptor;

/**
 * 目标接口 抽象成绩操作类
 * @author zhanghaonan
 *
 */
public interface ScoreOperation {
	
	/**
	 * 成绩排序
	 * @param array
	 * @return
	 */
	public int[] sort(int array[]);
	
	/**
	 * 成绩查找
	 * @param array
	 * @param key
	 * @return
	 */
	public int search(int array[],int key);

}
