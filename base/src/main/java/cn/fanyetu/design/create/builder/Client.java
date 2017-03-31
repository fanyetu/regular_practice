package cn.fanyetu.design.create.builder;

/**
 * 生成器模式
 * 客户端
 * @author zhanghaonan
 *
 */
public class Client {
	
	public static void main(String[] args) {
		Director director = new Director();
		
		LenovoComputerBuilder builder = new LenovoComputerBuilder();
		director.construct(builder);
		
		Computer computer = builder.getComputer();
		System.out.println(computer);
	}
}
