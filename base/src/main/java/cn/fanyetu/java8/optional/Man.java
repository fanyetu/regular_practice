package cn.fanyetu.java8.optional;

/**
 * Created by zhanghaonan on 2017/10/2.
 */
public class Man {

	private Godness godness;

	public Man() {
	}

	@Override
	public String toString() {
		return "Man{" +
				"godness=" + godness +
				'}';
	}

	public Godness getGodness() {
		return godness;
	}

	public void setGodness(Godness godness) {
		this.godness = godness;
	}

	public Man(Godness godness) {

		this.godness = godness;
	}
}
