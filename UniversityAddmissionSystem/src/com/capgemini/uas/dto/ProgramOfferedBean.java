package com.capgemini.uas.dto;

public class ProgramOfferedBean {
	private String programName;
	private String description;
	private String applicantEligiblity;
	private int duration;
	private String degreeCertificate;
	
	public ProgramOfferedBean() {
	}
	
	public ProgramOfferedBean(String programName, String description,
			String applicantEligiblity, int duration, String degreeCertificate) {
		super();
		this.programName = programName;
		this.description = description;
		this.applicantEligiblity = applicantEligiblity;
		this.duration = duration;
		this.degreeCertificate = degreeCertificate;
	}

	public String getProgramName() {
		return programName;
	}
	public void setProgramName(String programName) {
		this.programName = programName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getApplicantEligiblity() {
		return applicantEligiblity;
	}
	public void setApplicantEligiblity(String applicantEligiblity) {
		this.applicantEligiblity = applicantEligiblity;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public String getDegreeCertificate() {
		return degreeCertificate;
	}
	public void setDegreeCertificate(String degreeCertificate) {
		this.degreeCertificate = degreeCertificate;
	}
	@Override
	public String toString() {
		return "ProgramOffered [programName=" + programName + ", description="
				+ description + ", applicantEligiblity=" + applicantEligiblity
				+ ", duration=" + duration + ", degreeCertificate="
				+ degreeCertificate + "]";
	}
	
	
}
