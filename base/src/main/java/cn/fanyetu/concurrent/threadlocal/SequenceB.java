package cn.fanyetu.concurrent.threadlocal;

/**
 * threadLocalʵ��
 * @author Administrator
 *
 */
public class SequenceB implements Sequence {
	
	/**
	 * ��ThreadLocal������ֱ�ӵ�static����
	 */
	private static ThreadLocal<Integer> numberContainer = new ThreadLocal<Integer>(){
		protected Integer initialValue() {
			return 0;
		};
	};

	@Override
	public int getNumber() {
		numberContainer.set(numberContainer.get() + 1);
		return numberContainer.get();
	}
	
	public static void main(String[] args) {
		Sequence sequence = new SequenceB();
		ClientThread thread1 = new ClientThread(sequence);
		ClientThread thread2 = new ClientThread(sequence);
		ClientThread thread3 = new ClientThread(sequence);
		
		thread1.start();
		thread2.start();
		thread3.start();
		
		/*
		 * �����
		 * Thread-0=>1
			Thread-2=>1
			Thread-2=>2
			Thread-2=>3
			Thread-1=>1
			Thread-0=>2
			Thread-0=>3
			Thread-1=>2
			Thread-1=>3
		 */
	}

}
