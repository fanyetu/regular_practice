package cn.fanyetu.design.create.factoryMethod;

public class Client {
	
	public static void main(String[] args) {
		BasketCreator creator = new LiningBasketballCreator();
		Basketball basketball = creator.createBasketball();
		basketball.brand();
	}

}
