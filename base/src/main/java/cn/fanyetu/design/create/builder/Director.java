package cn.fanyetu.design.create.builder;

public class Director {
	
	public void construct(ComputerBuilder builder){
		builder.buildCpu();
		builder.buildMemory();
		builder.buildMainboard();
	}


}
