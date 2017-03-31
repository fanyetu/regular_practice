package cn.fanyetu.scripts;

import java.util.List;

import javax.script.Compilable;
import javax.script.CompiledScript;
import javax.script.Invocable;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Test {
	
	public static void main(String[] args) {
		
		//在脚本中引入java中的类
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("js");
		String script = "var list = java.util.ArrayList();list.add('a');println(list.get(0))";
		try {
			engine.eval(script);
		} catch (ScriptException e) {
			e.printStackTrace();
		}
		
		long begin = System.currentTimeMillis();
		System.out.println("begin:"+begin);
		
		//将脚本编译，加快执行速度，可多次执行
		Compilable compEngine = (Compilable) engine;
		try {
			//编译执行传递参数还是通过invokeFunction实现的，编译的脚本是不能像invokeFunction这样动态传递参数的，只能使用原始的
			//engine.put()方式传递参数
			//没看出来这个编译执行的优势，可能就是速度快一点吧(也没看出来速度快勒多少。。。)
			CompiledScript compileScript = compEngine.compile("function max_num(a,b){return (a>b)?a:b;}");
			compileScript.eval();
//			engine.eval("function max_num(a,b){return (a>b)?a:b;}");
			Invocable invoke = (Invocable) engine;  
            Object maxNum = invoke.invokeFunction("max_num",4,6);  
            System.out.println(maxNum); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		long end = System.currentTimeMillis();
		System.out.println("end:"+end);
		System.out.println("cost:"+(end - begin));
	}

	public static void main1(String[] args) {
		//显示java支持的脚本类型
		ScriptEngineManager manager = new ScriptEngineManager();
		List<ScriptEngineFactory> factories = manager.getEngineFactories();
		for (ScriptEngineFactory f : factories) {
			System.out.println("egine name:" + f.getEngineName() + ",engine version:" + f.getEngineVersion()
					+ ",language name:" + f.getLanguageName() + ",language version:" + f.getLanguageVersion()
					+ ",names:" + f.getNames() + ",mime:" + f.getMimeTypes() + ",extension:" + f.getExtensions());
		}
		
		//执行脚本
		ScriptEngine engine = manager.getEngineByName("js");
		String script = "print('hello world')";
		try {
			engine.eval(script);
		} catch (ScriptException e) {
			e.printStackTrace();
		}
		
		//传递参数
		engine.put("a", 4);
		engine.put("b", 6);
		script = "function maxnum(a,b){return (a>b)?a:b;} maxnum(a,b);";
		try {
			Object maxNum = engine.eval(script);
			System.out.println("\nmaxNum:"+maxNum);
		} catch (ScriptException e) {
			e.printStackTrace();
		}
		
		//获取参数
		Object a = engine.getBindings(ScriptContext.ENGINE_SCOPE).get("a");//对应当前这个engine的作用域
		System.out.println(a);
		
		//动态调用，通过Invocable这个接口实现
		Invocable invocable = (Invocable) engine;
		try {
			Object result = invocable.invokeFunction("maxnum", 2,3);
			System.out.println("maxnum:"+result);
			result = invocable.invokeFunction("maxnum", 10,3);
			System.out.println("maxnum:"+result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//脚本实现接口
		JsLib jsLib = invocable.getInterface(JsLib.class);
		int maxnum = jsLib.maxnum(11, 13);
		System.out.println("interface maxnum:"+maxnum);
		
		
	}

}
