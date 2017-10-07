package com.capgemini.uas.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.capgemini.uas.dto.ApplicationBean;
import com.capgemini.uas.dto.UsersBean;
import com.capgemini.uas.exception.UniversityException;
import com.capgemini.uas.util.ConnectionUtil;

public class MacDaoImpl implements IMacDao {

	private Connection connect;
	
	@Override
	public List<ApplicationBean> getApplicantsOnSchduledId(
			String scheduledProgramId) throws UniversityException {
		ConnectionUtil util = new ConnectionUtil();
		connect = util.getConnection();
		ResultSet rs = null;
		PreparedStatement stmt = null;
		List<ApplicationBean> applicantDetails = new ArrayList<ApplicationBean>();
		try{
			stmt = connect.prepareStatement(IQueryMapper.LIST_APPLICANTS);
			stmt.setString(1,scheduledProgramId);
			rs = stmt.executeQuery();
			while(rs.next()){
				int applicationId = rs.getInt(1);
				String fullName = rs.getString(2);
				LocalDate dateOfBirth = rs.getDate(3).toLocalDate();
				String highestQualification = rs.getString(4);
				int marksObtained = rs.getInt(5);
				String goals = rs.getString(6);
				String emailId = rs.getString(7);
				String status = rs.getString(9);
				LocalDate dateOfInterview = rs.getDate(10).toLocalDate();
				ApplicationBean aB = new ApplicationBean(applicationId, fullName, dateOfBirth, highestQualification, marksObtained, goals, emailId, scheduledProgramId, status, dateOfInterview);
				applicantDetails.add(aB);
			}
		}catch(SQLException e){
			e.printStackTrace();
			throw new UniversityException("Problem in writing data. in getApplicantsOnSchduledId",e);
		} finally {
			try {
				if (connect != null) {
					stmt.close();
					rs.close();
					connect.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new UniversityException(
				"Could not close the connection in getApplicantsOnSchduledId",e);
			}
		}
		return applicantDetails;
	}

	@Override
	public List<ApplicationBean> getApplicantsAfterInterviewOnId(
			String scheduledProgramId) throws UniversityException {
		ConnectionUtil util = new ConnectionUtil();
		connect = util.getConnection();
		ResultSet rs = null;
		PreparedStatement stmt = null;
		List<ApplicationBean> applicantDetails = new ArrayList<ApplicationBean>();
		try{
			stmt = connect.prepareStatement(IQueryMapper.LIST_APPLICANTS_ACCEPTED);
			stmt.setString(1,scheduledProgramId);
			rs = stmt.executeQuery();
			while(rs.next()){
				int applicationId = rs.getInt(1);
				String fullName = rs.getString(2);
				LocalDate dateOfBirth = rs.getDate(3).toLocalDate();
				String highestQualification = rs.getString(4);
				int marksObtained = rs.getInt(5);
				String goals = rs.getString(6);
				String emailId = rs.getString(7);
				String status = rs.getString(9);
				LocalDate dateOfInterview = rs.getDate(10).toLocalDate();
				ApplicationBean aB = new ApplicationBean(applicationId, fullName, dateOfBirth, highestQualification, marksObtained, goals, emailId, scheduledProgramId, status, dateOfInterview);
				applicantDetails.add(aB);
			}
		}catch(SQLException e){
			e.printStackTrace();
			throw new UniversityException("Problem in writing data in getApplicantsAfterInterviewOnId",e);
		} finally {
			try {
				if (connect != null) {
					stmt.close();
					rs.close();
					connect.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new UniversityException(
						"Could not close the connection in getApplicantsAfterInterviewOnId",e);
			}
		}
		return applicantDetails;
	}

	@Override
	public String updateApplicantStatus(int applicationId, String status)
			throws UniversityException {
		ConnectionUtil util = new ConnectionUtil();
		connect = util.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int recsAffected=0;
		try{
			stmt = connect.prepareStatement(IQueryMapper.UPDATE_APPLICANTS_STATUS);
			stmt.setString(1,status);
			stmt.setInt(2,applicationId);
			recsAffected =stmt.executeUpdate();
			/*while(rs.next()){
				status = rs.getString("status");
			}*/
		}catch(SQLException e){
			throw new UniversityException("Problem in writing data in updateApplicantStatus",e);
		}finally {
			try {
				if (connect != null) {
					stmt.close();
					connect.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new UniversityException(
						"Could not close the connection in updateApplicantStatus",e);
			}
		}
		return status;
	}

	@Override
	public void updateApplicantDateOfInterview(int applicationId, LocalDate dateOfInterview)
			throws UniversityException {
		ConnectionUtil util = new ConnectionUtil();
		connect = util.getConnection();
		PreparedStatement stmt = null;
		int recsAffected=0;
		try{
			stmt = connect.prepareStatement(IQueryMapper.UPDATE_APPLICANTS_DATE_OF_INTERVIEW);
			Date sqlDoI = Date.valueOf(dateOfInterview); //to convert date of birth local date to sql date
			stmt.setDate(1,sqlDoI);
			stmt.setInt(2,applicationId);
			recsAffected= stmt.executeUpdate(); 
		}catch(SQLException e){
			throw new UniversityException("Problem in writing data in updateApplicantDateOfInterview",e);
		}finally {
			try {
				if (connect != null) {
					stmt.close();
					connect.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new UniversityException(
						"Could not close the connection in updateApplicantDateOfInterview",e);
			}
		}
		
	}

}
