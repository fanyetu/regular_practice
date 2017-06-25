package cn.fanyetu.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zhanghaonan on 2017/6/25.
 */
public class HelloScheduler {

	public static void main(String[] args) throws SchedulerException {
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

		//创建一个jobDetail实例，将该实例与HelloJob绑定
		JobDetail jobDetail = JobBuilder.newJob(HelloJob.class)
				.withIdentity("myJob", "group")
				.usingJobData("message", "hello myJob")//设置JobDataMap的值
				.usingJobData("FloatJobValue", 3.141592654F)
				.build();

		Date startTime = new Date();
		startTime.setTime(startTime.getTime() + 3000);

		Date endTime = new Date();
		endTime.setTime(endTime.getTime() + 6000);

		//jobDetail的属性
		System.out.println("jobDetail's name " + jobDetail.getKey().getName());
		System.out.println("jobDetail's group " + jobDetail.getKey().getGroup());
		System.out.println("jobDetail's className " + jobDetail.getJobClass());

		//

		//创建一个Trigger，定义该job立即执行，并且每隔两秒重复执行一次
		Trigger trigger = TriggerBuilder.newTrigger().withIdentity("myTrigger", "group")
				.usingJobData("message", "hello myJob1")
				.usingJobData("DoubleTriggerValue", 2.0D)
//				.startNow()
				//设置开始时间和结束时间
				.startAt(startTime)
				.endAt(endTime)
				.withSchedule(SimpleScheduleBuilder
						.simpleSchedule()
						.withIntervalInSeconds(2)
						.repeatForever()).build();

		//创建Scheduler实例
		SchedulerFactory schedulerFactory = new StdSchedulerFactory();
		Scheduler scheduler = schedulerFactory.getScheduler();
		scheduler.start();
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		scheduler.scheduleJob(jobDetail, trigger);
	}
}