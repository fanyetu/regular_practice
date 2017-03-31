package cn.fanyetu.design.structure.observer;

import java.util.ArrayList;
import java.util.List;

public class Subject {
	
	private List<Observer> observerList = new ArrayList<Observer>();
	
	private String subjectState;
	
	public void register(Observer observer){
		observerList.add(observer);
	}
	
	public void cancel(Observer observer){
		observerList.remove(observer);
	}
	
	public void notifyObserver(){
		
	}

	public String getSubjectState() {
		return subjectState;
	}

	public void setSubjectState(String subjectState) {
		this.subjectState = subjectState;
	}
	
	
	

}
