package cn.fanyetu.design.create.builder;

public interface ComputerBuilder {

	public void buildCpu();
	
	public void buildMemory();
	
	public void buildMainboard();
	
	public Computer getComputer();
	
}
