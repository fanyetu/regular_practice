package cn.fanyetu.design.create.builder;

public class InterCpu implements Cpu {
	
	/**
	 * �ڻ�ȡcpu��ʱ����Խ����ع���ʹ�ù���
	 */
	@Override
	public Cpu getCpu() {
		return new InterCpu();
	}
	
	@Override
	public String toString() {
		return "interCpu";
	}

}
