package cn.fanyetu.design.create.builder;

public class AmdCpu implements Cpu {

	@Override
	public Cpu getCpu() {
		return new AmdCpu();
	}
	
	@Override
	public String toString() {
		return "amdCpu";
	}

}
