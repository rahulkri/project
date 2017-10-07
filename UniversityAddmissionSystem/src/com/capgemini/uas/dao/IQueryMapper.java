package com.capgemini.uas.dao;

public interface IQueryMapper {

	// For user table
	public static final String GET_USER="SELECT * FROM Users WHERE login_id=?";
	
	// for applicant table
	public static final String ADD_APPLICANT="INSERT INTO applicants values(?,?,?,?,?,?,?,?,?,?)";
	public static final String CHECK_STATUS="SELECT * FROM applicants WHERE application_id=?";
	public static final String LIST_APPLICANTS="SELECT * FROM applicants WHERE Scheduled_program_id=?";
	public static final String LIST_APPLICANTS_ACCEPTED="SELECT * FROM applicants WHERE Scheduled_program_id=? AND date_of_interview < sysdate AND status='accepted'";
	public static final String UPDATE_APPLICANTS_STATUS="UPDATE applicants SET status=? WHERE application_id=?";
	public static final String UPDATE_APPLICANTS_DATE_OF_INTERVIEW="UPDATE applicants SET date_of_interview=? WHERE status='accepted' AND application_id=?";

	// for program_offered table
	public static final String ADD_PROGRAM="INSERT INTO program_offered values(?,?,?,?,?)";
	public static final String UPDATE_PROGRAM_OFFERED_DESC="UPDATE program_offered SET description=? WHERE program_name=?";
	public static final String UPDATE_PROGRAM_OFFERED_APP_ELIG="UPDATE program_offered SET applicant_eligibility=? WHERE program_name=?";
	public static final String UPDATE_PROGRAM_OFFERED_DUR="UPDATE program_offered SET duration=? WHERE program_name=?";
	public static final String UPDATE_PROGRAM_OFFERED_DEGC="UPDATE program_offered SET degree_certificate_offered=? WHERE program_name=?";
	public static final String INSERT_PROGRAM_OFFERED="INSERT INTO program_offered VALUES(?,?,?,?,?)";
	public static final String DELETE_PROGRAM_OFFERED="DELETE FROM program_offered WHERE program_name=?";
	
	// for program_scheduled table
	public static final String LIST_PROGRAM="SELECT * FROM program_Scheduled";
	public static final String UPDATE_PROGRAM_SCH_PROG="UPDATE program_Scheduled SET Program_Name=? WHERE Scheduled_program_id=?";
	public static final String UPDATE_PROGRAM_SCH_LOC="UPDATE program_Scheduled SET location=? WHERE Scheduled_program_id=?";
	public static final String UPDATE_PROGRAM_SCH_START_DATE="UPDATE program_Scheduled SET Start_Date=? WHERE Scheduled_program_id=?";
	public static final String UPDATE_PROGRAM_SCH_END_DATE="UPDATE program_Scheduled SET End_Date=? WHERE Scheduled_program_id=?";
	public static final String UPDATE_PROGRAM_SCH_SESSION="UPDATE program_Scheduled SET sessions_per_week=? WHERE Scheduled_program_id=?";
	public static final String INSERT_PROGRAM_SCHEDULED="INSERT INTO program_Scheduled VALUES(?,?,?,?,?,?)";
	public static final String DELETE_PROGRAM_SCHEDULED="DELETE program_Scheduled WHERE Scheduled_program_id=?";

	// for participant table
	public static final String INSERT_PARTICIPANT="INSERT participants VALUES(?,?,?,?,?,?)";
	
	// sequences
	public static final String GEN_APPID = "SELECT application_id_seq.nextval FROM DUAL";
	public static final String GEN_PROGID = "SELECT program_id_seq.nextval FROM DUAL";
	public static final String GEN_ROLLNO = "SELECT roll_no_seq.nextval FROM DUAL";
}
