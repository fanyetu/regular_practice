package cn.fanyetu.design.create.builder;

public class AsusMainboard implements Mainboard {

	@Override
	public Mainboard getMainboard() {
		return new AsusMainboard();
	}
	
	@Override
	public String toString() {
		return "asusMainboard";
	}

}
