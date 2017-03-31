package cn.fanyetu.design.structure.adaptor;

/**
 * ����������
 * @author zhanghaonan
 *
 */
public class OperationAdapter implements ScoreOperation{
	
	private QuickSort quickSort;//����������QuickSort����  
	
	private BinarySearch binarySearch;//����������BinarySearch����  
	
	public OperationAdapter() {
		this.quickSort = new QuickSort();
		this.binarySearch = new BinarySearch();
	}

	@Override
	public int[] sort(int[] array) {
		return quickSort.quickSort(array);//������������QuickSort�����򷽷� 
	}

	@Override
	public int search(int[] array, int key) {
		return binarySearch.binarySearch(array, key);//���������ߵ�BinarySearch����������
	}
	
	

}
