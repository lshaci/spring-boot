package com.lshaci.schedule.service;

import com.lshaci.schedule.annotation.TimerTask;

public interface ScheduleConfigService {

	boolean judgeCurrentTask(TimerTask timerTask);
	
	void update(String taskName, Integer status);
}
