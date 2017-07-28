package com.lshaci.schedule.domain;

public class ScheduleConfig {
	
	private Long id;
	private String taskName;
	private String corn;
	private String current;
	private String next;
	private Integer status;
	
	
	public ScheduleConfig() {
	}
	
	public ScheduleConfig(String taskName, Integer status) {
		super();
		this.taskName = taskName;
		this.status = status;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCorn() {
		return corn;
	}
	public void setCorn(String corn) {
		this.corn = corn;
	}
	public String getCurrent() {
		return current;
	}
	public void setCurrent(String current) {
		this.current = current;
	}
	public String getNext() {
		return next;
	}
	public void setNext(String next) {
		this.next = next;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	@Override
	public String toString() {
		return "ScheduleConfig [id=" + id + ", taskName=" + taskName + ", corn=" + corn + ", current=" + current
				+ ", next=" + next + ", status=" + status + "]";
	}
	
	
}
