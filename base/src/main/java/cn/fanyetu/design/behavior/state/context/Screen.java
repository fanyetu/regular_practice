package cn.fanyetu.design.behavior.state.context;

/**
 * 屏幕类，充当环境类
 *
 * Created by zhanghaonan on 2017/5/3.
 */
public class Screen {

	//枚举所有的状态，currentState为当前状态
	private State currentState,normalState,largerState,largestState;

	public Screen() {
		this.normalState = new NormalState();
		this.largerState = new LargerState();
		this.largestState = new LargestState();
		this.currentState = normalState;//设置当前为正常状态
		this.currentState.display();
	}

	public void setCurrentState(State state){
		this.currentState = state;
	}

	//转换状态
	public void onClick(){
		if (this.currentState == normalState){
			setCurrentState(largerState);
		} else if (this.currentState == largerState){
			setCurrentState(largestState);
		} else if (this.currentState == largestState){
			setCurrentState(normalState);
		}
		this.currentState.display();
	}


}
