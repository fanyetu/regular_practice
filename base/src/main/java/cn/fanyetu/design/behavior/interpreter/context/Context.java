package cn.fanyetu.design.behavior.interpreter.context;

import java.util.StringTokenizer;

/**
 * 上下文环境类，提供命令执行的上下文环境，以及提供一些公共操作，减轻命令的负担
 * 用于存储和操作需要解释的语句，在本实例中每一个需要解释的单词可以称为一个动作标记(Action Token)或命令
 *
 * Created by zhanghaonan on 2017/4/13.
 */
public class Context {

	private StringTokenizer tokenizer;
	private String currentToken;//当前字符串标记

	public Context(String text) {
		//创建StringTokenizer类，默认使用的是空格作为分隔符
		this.tokenizer = new StringTokenizer(text);
		nextToken();//调用nextToken,跳转到第一个字符串
	}

	/**
	 * 读取并返回下一个标记
	 */
	public String nextToken() {
		//如果还有更多的标记
		if (this.tokenizer.hasMoreTokens()){
			currentToken = this.tokenizer.nextToken();
		}else{
			currentToken = null;
		}
		return currentToken;
	}

	/**
	 * 返回当前标记
	 * @return
	 */
	public String currentToken(){
		return currentToken;
	}

	/**
	 * 跳过一个标记
	 */
//	public void skipToken(String token){
//		if (!currentToken.equalsIgnoreCase(token)){
//			System.out.println("指令解析错误");
//		}
//		nextToken();
//	}

	/**
	 * 如果当前标记是一个数字，就返回数值
	 * @return
	 */
	public int currentNumber(){
		int number = 0;
		try {
			number = Integer.parseInt(currentToken);
		} catch (Exception e){
			System.out.println("解析错误");
			e.printStackTrace();
		}
		return number;
	}

//	public static void main(String[] args) {
//		String s = "i am coming for you";
//		Context context = new Context(s);
//		System.out.println(context.currentToken());
//		System.out.println(context.nextToken());
//	}

}
