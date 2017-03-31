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
		
		//�ڽű�������java�е���
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
		
		//���ű����룬�ӿ�ִ���ٶȣ��ɶ��ִ��
		Compilable compEngine = (Compilable) engine;
		try {
			//����ִ�д��ݲ�������ͨ��invokeFunctionʵ�ֵģ�����Ľű��ǲ�����invokeFunction������̬���ݲ����ģ�ֻ��ʹ��ԭʼ��
			//engine.put()��ʽ���ݲ���
			//û�������������ִ�е����ƣ����ܾ����ٶȿ�һ���(Ҳû�������ٶȿ��ն��١�����)
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
		//��ʾjava֧�ֵĽű�����
		ScriptEngineManager manager = new ScriptEngineManager();
		List<ScriptEngineFactory> factories = manager.getEngineFactories();
		for (ScriptEngineFactory f : factories) {
			System.out.println("egine name:" + f.getEngineName() + ",engine version:" + f.getEngineVersion()
					+ ",language name:" + f.getLanguageName() + ",language version:" + f.getLanguageVersion()
					+ ",names:" + f.getNames() + ",mime:" + f.getMimeTypes() + ",extension:" + f.getExtensions());
		}
		
		//ִ�нű�
		ScriptEngine engine = manager.getEngineByName("js");
		String script = "print('hello world')";
		try {
			engine.eval(script);
		} catch (ScriptException e) {
			e.printStackTrace();
		}
		
		//���ݲ���
		engine.put("a", 4);
		engine.put("b", 6);
		script = "function maxnum(a,b){return (a>b)?a:b;} maxnum(a,b);";
		try {
			Object maxNum = engine.eval(script);
			System.out.println("\nmaxNum:"+maxNum);
		} catch (ScriptException e) {
			e.printStackTrace();
		}
		
		//��ȡ����
		Object a = engine.getBindings(ScriptContext.ENGINE_SCOPE).get("a");//��Ӧ��ǰ���engine��������
		System.out.println(a);
		
		//��̬���ã�ͨ��Invocable����ӿ�ʵ��
		Invocable invocable = (Invocable) engine;
		try {
			Object result = invocable.invokeFunction("maxnum", 2,3);
			System.out.println("maxnum:"+result);
			result = invocable.invokeFunction("maxnum", 10,3);
			System.out.println("maxnum:"+result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//�ű�ʵ�ֽӿ�
		JsLib jsLib = invocable.getInterface(JsLib.class);
		int maxnum = jsLib.maxnum(11, 13);
		System.out.println("interface maxnum:"+maxnum);
		
		
	}

}
