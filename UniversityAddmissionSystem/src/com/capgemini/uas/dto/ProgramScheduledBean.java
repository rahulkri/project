package com.capgemini.uas.dto;

import java.time.LocalDate;
import java.util.Date;

public class ProgramScheduledBean {
	private String scheduledProgramId;
	private String programName;
	private String location;
	private Date startDate;
	private Date endDate;
	private int sessionPerWeek;
	
	public ProgramScheduledBean() {
	}
	public ProgramScheduledBean(String scheduledProgramId, String programName,
			String location, Date startDate, Date endDate, int sessionPerWeek) {
		super();
		this.scheduledProgramId = scheduledProgramId;
		this.programName = programName;
		this.location = location;
		this.startDate = startDate;
		this.endDate = endDate;
		this.sessionPerWeek = sessionPerWeek;
	}
	public String getScheduledProgramId() {
		return scheduledProgramId;
	}
	public void setScheduledProgramId(String scheduledProgramId) {
		this.scheduledProgramId = scheduledProgramId;
	}
	public String getProgramName() {
		return programName;
	}
	public void setProgramName(String programName) {
		this.programName = programName;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public int getSessionPerWeek() {
		return sessionPerWeek;
	}
	public void setSessionPerWeek(int sessionPerWeek) {
		this.sessionPerWeek = sessionPerWeek;
	}
	@Override
	public String toString() {
		return "ProgramScheduledBean [scheduledProgramId=" + scheduledProgramId
				+ ", programName=" + programName + ", location=" + location
				+ ", startDate=" + startDate + ", endDate=" + endDate
				+ ", sessionPerWeek=" + sessionPerWeek + "]";
	}
	
	
}
