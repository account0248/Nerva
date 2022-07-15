package jp.co.vaile.nerva.managementattedance;

public class HolidayPeriodDTO {

	private String holidayId;
	private String holidayName;
	private String holidayDays;
	private boolean holidayDaysFlg;
	private String holidayTime;
	private boolean holidayTimeFlg;
	private String lateTime;
	private boolean lateTimeFlg;
	
	public String getHolidayName() {
		return holidayName;
	}
	
	public void setHolidayName(String holiday_name) {
		this.holidayName = holiday_name;
	}
	
	public String getHolidayDays() {
		return holidayDays;
	}
	
	public void setHoliday_days(String holiday_days) {
		this.holidayDays = holiday_days;
	}
	
	public boolean isHolidayDaysFlg() {
		return holidayDaysFlg;
	}
	
	public void setHoliday_days_flg(boolean holiday_days_flg) {
		this.holidayDaysFlg = holiday_days_flg;
	}
	
	public String getHolidayTime() {
		return holidayTime;
	}
	
	public void setHoliday_time(String holiday_time) {
		this.holidayTime = holiday_time;
	}
	
	public String getLateTime() {
		return lateTime;
	}
	
	public void setLate_time(String late_time) {
		this.lateTime = late_time;
	}
	
	public boolean isLateTimeFlg() {
		return lateTimeFlg;
	}
	
	public void setLate_time_flg(boolean late_time_flg) {
		this.lateTimeFlg = late_time_flg;
	}

	public boolean isHolidayTimeFlg() {
		return holidayTimeFlg;
	}

	public void setHoliday_time_flg(boolean holiday_time_flg) {
		this.holidayTimeFlg = holiday_time_flg;
	}

	public String getHolidayId() {
		return holidayId;
	}

	public void setHolidayId(String holidayId) {
		this.holidayId = holidayId;
	}
}
