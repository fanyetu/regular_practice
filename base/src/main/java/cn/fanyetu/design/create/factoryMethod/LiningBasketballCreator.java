package cn.fanyetu.design.create.factoryMethod;

public class LiningBasketballCreator extends BasketCreator {
	
	@Override
	public Basketball createBasketball() {
		return new LiningBasketball();
	}

}
