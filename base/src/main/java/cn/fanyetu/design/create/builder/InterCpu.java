package cn.fanyetu.design.create.builder;

public class InterCpu implements Cpu {
	
	/**
	 * 在获取cpu的时候可以进行重构，使用工厂
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
