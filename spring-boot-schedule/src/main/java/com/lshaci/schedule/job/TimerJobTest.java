package com.lshaci.schedule.job;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.lshaci.schedule.annotation.TimerTask;

@Component
public class TimerJobTest {

	@TimerTask(value="test", cron = "0/10 * * * * ?")
	public void doJob() throws InterruptedException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = sdf.format(new Date());
		System.out.println("任务进行中。。。" + dateStr);
		Thread.sleep(5000); 	// 模拟执行任务5秒钟
	}
}
