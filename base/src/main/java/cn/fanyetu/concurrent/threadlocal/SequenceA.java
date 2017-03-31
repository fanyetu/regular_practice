package cn.fanyetu.concurrent.threadlocal;

public class SequenceA implements Sequence{

	/**
	 * �߳�֮�乲���static�����޷���֤���ڲ�ͬ���̶߳����ǰ�ȫ��
	 */
	private static int number = 0;
	
	@Override
	public int getNumber() {
		number = number + 1;
		return number;
	}
	
	public static void main(String[] args) {
		Sequence sequence = new SequenceA();
		ClientThread thread1 = new ClientThread(sequence);
		ClientThread thread2 = new ClientThread(sequence);
		ClientThread thread3 = new ClientThread(sequence);
		
		thread1.start();
		thread2.start();
		thread3.start();
		
		/*
		 * �����
		 * Thread-1=>1
			Thread-1=>4
			Thread-1=>5
			Thread-2=>3
			Thread-2=>6
			Thread-2=>7
			Thread-0=>2
			Thread-0=>8
			Thread-0=>9
		 */
	}

}
