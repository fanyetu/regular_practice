package cn.fanyetu.design.behavior.interpreter.context;

/**
 * 抽象表达式类
 *
 * Created by zhanghaonan on 2017/4/13.
 */
public interface Node {

	void interpret(Context context);
	void execute();
}
