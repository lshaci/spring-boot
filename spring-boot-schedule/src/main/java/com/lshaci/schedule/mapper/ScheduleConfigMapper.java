package com.lshaci.schedule.mapper;

import com.lshaci.schedule.domain.ScheduleConfig;

public interface ScheduleConfigMapper {

	void save(ScheduleConfig scheduleConfig);
	
	void update(ScheduleConfig scheduleConfig);
	
	void updateByTaskName(ScheduleConfig scheduleConfig);
	
	ScheduleConfig selectByName(String taskName);
}
