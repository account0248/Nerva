package jp.co.vaile.nerva.managementattedance;

public class WorkScheduleDTO {

	private String days;
	private String startTime;
	private String endTime;
	private String rest;
	private String holidayWorking;
	private String holidayPeriod;
	private String holidayDays;
	private String holidayTime;
	private String lateTime;
	private String remarks;
	public String getDays() {
		return days;
	}
	public void setDays(String days) {
		this.days = days;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String start_time) {
		this.startTime = start_time;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String end_time) {
		this.endTime = end_time;
	}
	public String getRest() {
		return rest;
	}
	public void setRest(String rest) {
		this.rest = rest;
	}
	public String getHolidayWorking() {
		return holidayWorking;
	}
	public void setHolidayWorking(String holiday_working) {
		this.holidayWorking = holiday_working;
	}
	public String getHolidayPeriod() {
		return holidayPeriod;
	}
	public void setHolidayPeriod(String holiday_period) {
		this.holidayPeriod = holiday_period;
	}
	public String getHolidayDays() {
		return holidayDays;
	}
	public void setHolidayDays(String holiday_days) {
		this.holidayDays = holiday_days;
	}
	public String getHolidayTime() {
		return holidayTime;
	}
	public void setHolidayTime(String holiday_time) {
		this.holidayTime = holiday_time;
	}
	public String getLateTime() {
		return lateTime;
	}
	public void setLateTime(String late_time) {
		this.lateTime = late_time;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
