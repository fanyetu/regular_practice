package cn.fanyetu.java8.optional;

/**
 * Created by zhanghaonan on 2017/10/2.
 */
public class Godness {

	private String name;

	@Override
	public String toString() {
		return "Godness{" +
				"name='" + name + '\'' +
				'}';
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Godness(String name) {

		this.name = name;
	}
}
