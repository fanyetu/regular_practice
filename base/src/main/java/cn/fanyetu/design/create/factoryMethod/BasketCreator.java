package cn.fanyetu.design.create.factoryMethod;

public abstract class BasketCreator {
	
	private Basketball basketball;
	
	public Basketball createBasketball(){
		basketball = new LiningBasketball();
		return basketball;
	}

}
