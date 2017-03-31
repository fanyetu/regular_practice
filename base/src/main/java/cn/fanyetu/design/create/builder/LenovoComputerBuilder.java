package cn.fanyetu.design.create.builder;

public class LenovoComputerBuilder implements ComputerBuilder {
	
	private Computer computer = new Computer();

	@Override
	public void buildCpu() {
		computer.setCpu(new InterCpu());
	}

	@Override
	public void buildMemory() {
		computer.setMemory(new KingstonMemory());
	}

	@Override
	public void buildMainboard() {
		computer.setMainboard(new AsusMainboard());
	}

	@Override
	public Computer getComputer() {
		return computer;
	}

}
