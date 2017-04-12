package cn.fanyetu.design.behavior.command.queue;

/**
 * 批处理的功能键
 *
 * Created by zhanghaonan on 2017/4/11.
 */
public class BatchFunctionButton {

	private CommandQueue commandQueue;
	private String name;

	public BatchFunctionButton(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCommandQueue(CommandQueue commandQueue) {
		this.commandQueue = commandQueue;
	}

	//请求方法
	public void onClick(){
		commandQueue.execute();
	}
}
