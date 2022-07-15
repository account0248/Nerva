package jp.co.vaile.nerva.transferApplication;

import java.sql.Date;

public class TransferApplicationDTO {

	private String applicationNum;

	private String applicant;

	private String applicationBelongCompany;

	private String transferEmp;

	private String nowApplicationTeam;


	private String transferApplicationTeam;

	private Date transferPreferredDate;



	public String getApplicationNum() {
		return applicationNum;
	}

	public void setApplicationNum(String applicationNum) {
		this.applicationNum = applicationNum;
	}

	public String getApplicant() {
		return applicant;
	}

	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}

	public String getApplicationBelongCompany() {
		return applicationBelongCompany;
	}

	public void setApplicationBelongCompany(String applicationBelongCompany) {
		this.applicationBelongCompany = applicationBelongCompany;
	}

	public String getTransferEmp() {
		return transferEmp;
	}

	public void setTransferEmp(String transferEmp) {
		this.transferEmp = transferEmp;
	}

	public String getNowApplicationTeam() {
		return nowApplicationTeam;
	}

	public void setNowApplicationTeam(String nowApplicationTeam) {
		this.nowApplicationTeam = nowApplicationTeam;
	}


	public String getTransferApplicationTeam() {
		return transferApplicationTeam;
	}

	public void setTransferApplicationTeam(String transferApplicationTeam) {
		this.transferApplicationTeam = transferApplicationTeam;
	}

	public Date getTransferPreferredDate() {
		return transferPreferredDate;
	}

	public void setTransferPreferredDate(Date transferPreferredDate) {
		this.transferPreferredDate = transferPreferredDate;
	}

}
