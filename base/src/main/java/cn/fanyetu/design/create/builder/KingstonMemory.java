package cn.fanyetu.design.create.builder;

public class KingstonMemory implements Memory {

	@Override
	public Memory getMemory() {
		return new KingstonMemory();
	}
	
	@Override
	public String toString() {
		return "kingstonMemory";
	}

}
