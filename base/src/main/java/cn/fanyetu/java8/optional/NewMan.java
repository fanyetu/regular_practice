package cn.fanyetu.java8.optional;

import java.util.Optional;

/**
 * Created by zhanghaonan on 2017/10/2.
 */
public class NewMan {

	private Optional<Godness> godness = Optional.empty();

	@Override
	public String toString() {
		return "NewMan{" +
				"godness=" + godness +
				'}';
	}

	public Optional<Godness> getGodness() {
		return godness;
	}

	public void setGodness(Optional<Godness> godness) {
		this.godness = godness;
	}

	public NewMan() {

	}

	public NewMan(Optional<Godness> godness) {

		this.godness = godness;
	}
}
