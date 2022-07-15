
package jp.co.vaile.nerva.managementattedance.updateWorkSchedule;

import java.util.List;

import jp.co.vaile.nerva.managementattedance.WorkScheduleDTO;

public class UpdateWSDTO {

	private String year;
	private String month;
	private String employeeId;
	private String userId;
	private List<WorkScheduleDTO> WorkScheduleDTOList;
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public List<WorkScheduleDTO> getWorkScheduleDTOList() {
		return WorkScheduleDTOList;
	}
	public void setWorkScheduleDTOList(List<WorkScheduleDTO> workScheduleDTOList) {
		WorkScheduleDTOList = workScheduleDTOList;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}


}
