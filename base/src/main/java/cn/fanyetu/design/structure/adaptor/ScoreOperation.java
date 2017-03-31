package cn.fanyetu.design.structure.adaptor;

/**
 * Ŀ��ӿ� ����ɼ�������
 * @author zhanghaonan
 *
 */
public interface ScoreOperation {
	
	/**
	 * �ɼ�����
	 * @param array
	 * @return
	 */
	public int[] sort(int array[]);
	
	/**
	 * �ɼ�����
	 * @param array
	 * @param key
	 * @return
	 */
	public int search(int array[],int key);

}
