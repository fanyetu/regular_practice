package cn.fanyetu.design.create.builder;

public class GigabyteMainboard implements Mainboard {

	@Override
	public Mainboard getMainboard() {
		return new GigabyteMainboard();
	}

	@Override
	public String toString() {
		return "gigabyteMainboard";
	}
}
