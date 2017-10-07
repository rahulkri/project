package com.capgemini.uas.dto;

public class ParticipantBean {
	
	private String rollNo;
	private int applicationId;
	private String emailId;
	private String scheduledProgramId;
	
	public ParticipantBean() {
	}
	public ParticipantBean(String rollNo, int applicationId, String emailId,
			String scheduledProgramId) {
		super();
		this.rollNo = rollNo;
		this.applicationId = applicationId;
		this.emailId = emailId;
		this.scheduledProgramId = scheduledProgramId;
	}
	public String getRollNo() {
		return rollNo;
	}
	public void setRollNo(String rollNo) {
		this.rollNo = rollNo;
	}
	public int getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getScheduledProgramId() {
		return scheduledProgramId;
	}
	public void setScheduledProgramId(String scheduledProgramId) {
		this.scheduledProgramId = scheduledProgramId;
	}
	@Override
	public String toString() {
		return "ParticipantBean [rollNo=" + rollNo + ", applicationId="
				+ applicationId + ", emailId=" + emailId
				+ ", scheduledProgramId=" + scheduledProgramId + "]";
	}
	
	

}
