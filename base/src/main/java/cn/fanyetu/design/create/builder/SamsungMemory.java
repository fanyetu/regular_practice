package cn.fanyetu.design.create.builder;

public class SamsungMemory implements Memory {

	@Override
	public Memory getMemory() {
		return new SamsungMemory();
	}
	
	@Override
	public String toString() {
		return "samsungMemory";
	}

}
