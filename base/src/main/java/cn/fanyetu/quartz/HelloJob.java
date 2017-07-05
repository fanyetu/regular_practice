package cn.fanyetu.quartz;

//import org.quartz.*;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zhanghaonan on 2017/6/25.
 */
//public class HelloJob implements Job {
public class HelloJob{
	private String message;
	private float FloatJobValue;
	private double DoubleTriggerValue;

//	@Override
//	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
//
//		//编写具体的业务逻辑
//		System.out.println("==================");
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		System.out.println(sdf.format(new Date()));
//		System.out.println("hello quartz");
//
//		//通过JobExecutionContext获取JobDetail和trigger的相关信息
//		JobKey key = jobExecutionContext.getJobDetail().getKey();
//		System.out.println("job name:" + key.getName() + ",job group:" + key.getGroup());
//
//		TriggerKey triggerKey = jobExecutionContext.getTrigger().getKey();
//		System.out.println("trigger name:" + triggerKey.getName() + ",trigger group:" + triggerKey
//				.getGroup());
//
//		/*
//		获取任务调用时传入的参数信息的两种方式
//		 */
//		//通过手动获取jobDataMap的方式获取传入的信息
////		JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
////		JobDataMap jobDataMap1 = jobExecutionContext.getTrigger().getJobDataMap();
////		String message = jobDataMap.getString("message");
////		float floatJobValue = jobDataMap.getFloat("FloatJobValue");
////		String message1 = jobDataMap1.getString("message");
////		double doubleTriggerValue = jobDataMap1.getDouble("DoubleTriggerValue");
////
////		System.out.println("job:" + message + ":" + floatJobValue);
////		System.out.println("trigger:" + message1 + ":" + doubleTriggerValue);
////
////		JobDataMap mergedJobDataMap = jobExecutionContext.getMergedJobDataMap();
////		String message2 = mergedJobDataMap.getString("message");
////		float floatJobValue1 = mergedJobDataMap.getFloat("FloatJobValue");
////		double doubleTriggerValue1 = mergedJobDataMap.getDouble("DoubleTriggerValue");
////		System.out.println("merged map:" + message2 + "-" + floatJobValue1 + "-" +
////				doubleTriggerValue1);
//
//		//通过job的setter方法获取传入的信息
//		System.out.println("set value:" + message + "-" + FloatJobValue + "-" +
//				DoubleTriggerValue);
//
//		//获取trigger的开始时间和结束时间
//		Trigger trigger = jobExecutionContext.getTrigger();
//		Date startTime = trigger.getStartTime();
//		Date endTime = trigger.getEndTime();
//		System.out.println("startTime:" + sdf.format(startTime));
//		System.out.println("endTime:" + sdf.format(endTime));
//
//		System.out.println("==================");

//	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setFloatJobValue(float floatJobValue) {
		FloatJobValue = floatJobValue;
	}

	public void setDoubleTriggerValue(double doubleTriggerValue) {
		DoubleTriggerValue = doubleTriggerValue;
	}
}
