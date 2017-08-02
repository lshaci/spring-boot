package com.lshaci.schedule.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lshaci.schedule.annotation.TimerTask;
import com.lshaci.schedule.domain.ScheduleConfig;
import com.lshaci.schedule.mapper.ScheduleConfigMapper;
import com.lshaci.schedule.service.ScheduleConfigService;

@Service
public class ScheduleConfigServiceImpl implements ScheduleConfigService {
	
	@Autowired
	private ScheduleConfigMapper mapper;

	@Override
	@Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRED)
	public boolean judgeCurrentTask(TimerTask timerTask) {
		String taskName = timerTask.value();
		ScheduleConfig scheduleConfig = mapper.selectByName(taskName);
		if (scheduleConfig == null) {
			scheduleConfig = new ScheduleConfig(taskName, 1);
			mapper.save(scheduleConfig);
			return true;
		} else if (scheduleConfig.getStatus() == 0) {
			scheduleConfig.setStatus(1);
			mapper.updateByTaskName(scheduleConfig);
			return true;
		}
		return false;
	}

	@Override
	@Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRED)
	public void update(String taskName, Integer status) {
		ScheduleConfig scheduleConfig = new ScheduleConfig(taskName, status);
		mapper.updateByTaskName(scheduleConfig);
	}
	
	

}
