package com.lshaci.timerTask;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;

import com.lshaci.annotation.TimerTask;

@TimerTask
public class TimerJobTest {

	@Scheduled(cron = "0/30 * * * * ?")
	public void doJob() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = sdf.format(new Date());
		System.out.println("任务进行中。。。" + dateStr);
	}
}
