package cn.fanyetu.design.create.builder;

public class Computer {
	
	private Cpu cpu;
	private Memory memory;
	private Mainboard mainboard;
	public Cpu getCpu() {
		return cpu;
	}
	public void setCpu(Cpu cpu) {
		this.cpu = cpu;
	}
	public Memory getMemory() {
		return memory;
	}
	public void setMemory(Memory memory) {
		this.memory = memory;
	}
	public Mainboard getMainboard() {
		return mainboard;
	}
	public void setMainboard(Mainboard mainboard) {
		this.mainboard = mainboard;
	}
	@Override
	public String toString() {
		return "Computer [cpu=" + cpu + ", memory=" + memory + ", mainboard="
				+ mainboard + "]";
	}
	

}
