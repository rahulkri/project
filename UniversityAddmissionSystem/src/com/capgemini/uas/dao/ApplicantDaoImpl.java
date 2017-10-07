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
import com.capgemini.uas.dto.ProgramScheduledBean;
import com.capgemini.uas.exception.UniversityException;
import com.capgemini.uas.util.ConnectionUtil;

public class ApplicantDaoImpl implements IApplicantDao {

	private Connection connect;
	
	@Override
	public List<ProgramScheduledBean> showProgramScheduled()
			throws UniversityException {
		ConnectionUtil util = new ConnectionUtil();
		connect = util.getConnection();
		ResultSet rs = null;
		Statement stmt = null;
		List<ProgramScheduledBean> progDetails = new ArrayList<ProgramScheduledBean>();
		try{
			stmt = connect.createStatement();
			rs = stmt.executeQuery(IQueryMapper.LIST_PROGRAM);
			while(rs.next()){
				String scheduledProgramId = rs.getString(1);
				String programName = rs.getString(2);
				String location = rs.getString(3);
				Date startDate = rs.getDate(4);
				Date endDate = rs.getDate(5);
				int sessionPerWeek = rs.getInt(6);
				ProgramScheduledBean pb= new ProgramScheduledBean(scheduledProgramId, programName, location, startDate, endDate, sessionPerWeek);
				progDetails.add(pb);
			}
			}catch(SQLException e){
				e.printStackTrace();
				throw new UniversityException("Problem in writing data showProgramScheduled",e);
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
							"Could not close the connection in showProgramScheduled()");
						}
					}
		return progDetails;
	}

	@Override
	public int addDetail(ApplicationBean ab) throws UniversityException {
		int recsAffected=0;
		int applicationId = getApplicationId();
		ConnectionUtil util = new ConnectionUtil();
		connect = util.getConnection();
		PreparedStatement stmt = null;
		try{
			stmt = connect.prepareStatement(IQueryMapper.ADD_APPLICANT);
			stmt.setInt(1,applicationId);
			stmt.setString(2,ab.getFullName());
			Date sqlDob = Date.valueOf(ab.getDateOfBirth()); //to convert date of birth local date to sql date
			stmt.setDate(3,sqlDob);
			stmt.setString(4,ab.getHighestQualification());
			stmt.setInt(5,ab.getMarksObtained());
			stmt.setString(6,ab.getGoals());
			stmt.setString(7,ab.getEmailId());
			stmt.setString(8,ab.getScheduledProgramId());
			stmt.setString(9,ab.getStatus());
			Date sqlDoI = Date.valueOf(ab.getDateOfInterview()); //to convert date of birth local date to sql date
			stmt.setDate(10,sqlDoI);
			recsAffected= stmt.executeUpdate();
			}catch(SQLException e){
				e.printStackTrace();
				throw new UniversityException("Problem in writing data in addDetail",e);
			}finally {
					try {
						if (connect != null) {
							stmt.close();
							connect.close();
						}
					} catch (Exception e) {
						e.printStackTrace();
						throw new UniversityException(
						"Could not close the connection in addDetail");
					}
			}
		return applicationId;
	}

	//Sequence to fetch next value of Application ID
	private int getApplicationId() throws UniversityException 
	{
		ConnectionUtil util = new ConnectionUtil();
		connect = util.getConnection();
		int applicationId = 0;
		PreparedStatement pstmt = null;
		ResultSet res = null;
		try
		{
			pstmt= connect.prepareStatement(IQueryMapper.GEN_APPID);
			res = pstmt.executeQuery();
			if(res.next())
				{
					applicationId = res.getInt(1);
				}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			throw new UniversityException("Problem in generating Application Id");
		}finally {
			try {
				if (connect != null) {
					pstmt.close();
					res.close();
					connect.close();
				}
				} catch (Exception e) {
					e.printStackTrace();
					throw new UniversityException(
					"Could not close the connection in getApplicationId()");
				}
			}
		return applicationId;		
	}
	
	@Override
	public ApplicationBean showStatus(int applicationId)
			throws UniversityException {
		ApplicationBean appBean = null;
		ConnectionUtil util = new ConnectionUtil();
		connect = util.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
				stmt = connect.prepareStatement(IQueryMapper.CHECK_STATUS);
				stmt.setInt(1,applicationId);
				rs = stmt.executeQuery();
				while(rs.next()){
					String fullName = rs.getString(2);
					LocalDate dateOfBirth = rs.getDate(3).toLocalDate();
					String highestQualification = rs.getString(4);
					int marksObtained = rs.getInt(5);
					String goals = rs.getString(6);
					String emailId = rs.getString(7);
					String scheduledProgramId = rs.getString(8);
					String status = rs.getString(9);
					LocalDate dateOfInterview = rs.getDate(10).toLocalDate();
					appBean = new ApplicationBean(fullName, dateOfBirth, highestQualification, marksObtained, goals, emailId, scheduledProgramId, status, dateOfInterview);
				}
				}catch(SQLException e){
					e.printStackTrace();
					throw new UniversityException("Problem in writing data in showStatus",e);
				}finally {
					try {
						if (connect != null) {
							stmt.close();
							rs.close();
							connect.close();
						}
						} catch (Exception e) {
							e.printStackTrace();
							throw new UniversityException(
							"Could not close the connection in showStatus()");
						}
				}
		return appBean;
	}
}