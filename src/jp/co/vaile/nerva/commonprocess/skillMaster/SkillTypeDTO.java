package jp.co.vaile.nerva.commonprocess.skillMaster;

public class SkillTypeDTO {
	private String skillTypeId;
	private String skillTypeName;
	private boolean yearsDateOfAcquisition;
	
	public String getSkillTypeId() {
		return skillTypeId;
	}
	public void setSkillTypeId(String skillTypeId) {
		this.skillTypeId = skillTypeId;
	}
	public String getSkillTypeName() {
		return skillTypeName;
	}
	public void setSkillTypeName(String skillTypeName) {
		this.skillTypeName = skillTypeName;
	}
	public boolean isYearsDateOfAcquisition() {
		return yearsDateOfAcquisition;
	}
	public void setYearsDateOfAcquisition(boolean yearsDateOfAcquisition) {
		this.yearsDateOfAcquisition = yearsDateOfAcquisition;
	}
}
