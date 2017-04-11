package cn.fanyetu.design.behavior.command.concrete;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能键设置窗口类
 * <p>
 * Created by zhanghaonan on 2017/4/11.
 */
public class FBSettingWindow {

	private String title;//设置一个窗口标题

	//存储所有的功能键
	private List<FunctionButton> fbList = new ArrayList<>();

	public FBSettingWindow(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void addFunctionButton(FunctionButton functionButton) {
		this.fbList.add(functionButton);
	}

	public void removeFunctionButton(FunctionButton functionButton) {
		this.fbList.remove(functionButton);
	}

	//显示窗口及功能键
	public void display() {
		System.out.println("显示窗口:" + this.title);
		System.out.println("显示功能键:");
		for (FunctionButton functionButton : fbList){
			System.out.println(functionButton.getName());
		}

		System.out.println("-----------------------------");
	}
}
