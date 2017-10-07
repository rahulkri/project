package com.capgemini.uas.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.capgemini.uas.dto.ProgramOfferedBean;
import com.capgemini.uas.dto.ProgramScheduledBean;
import com.capgemini.uas.exception.UniversityException;
import com.capgemini.uas.util.ConnectionUtil;

public class AdminDaoImpl implements IAdminDao {
	private Connection connect;
	@Override
	public boolean addProgramOffered(ProgramOfferedBean pOffered)
			throws UniversityException {
		
		int recsAffected=0;
		
		ConnectionUtil util = new ConnectionUtil();
		connect = util.getConnection();
		PreparedStatement stmt = null;
		try{
			stmt = connect.prepareStatement(IQueryMapper.ADD_PROGRAM);
			stmt.setString(1,pOffered.getProgramName());
			stmt.setString(2,pOffered.getDescription());
			 //to convert date of birth local date to sql date
			stmt.setString(3,pOffered.getApplicantEligiblity());
			stmt.setInt(4,pOffered.getDuration());
			stmt.setString(5,pOffered.getDegreeCertificate());
			
			recsAffected= stmt.executeUpdate();
			}catch(SQLException e){
				e.printStackTrace();
				throw new UniversityException("Problem in writing data in programOffered",e);
			}finally {
					try {
						if (connect != null) {
							stmt.close();
							connect.close();
						}
					} catch (Exception e) {
						e.printStackTrace();
						throw new UniversityException(
						"Could not close the connection in programOffered");
					}
			}
		if(recsAffected==1)
		{
			return true;
	}
		else
		return false;
	}

	
	@Override
	public boolean deleteProgramOffered(String programName)
			throws UniversityException {
		int recsAffected=0;
		System.out.println("In Dao");
		ConnectionUtil util = new ConnectionUtil();
		connect = util.getConnection();
		PreparedStatement stmt = null;
		System.out.println("In Dao2");
		try{
			stmt = connect.prepareStatement(IQueryMapper.DELETE_PROGRAM_OFFERED);
			stmt.setString(1,programName);
			System.out.println("In Dao3");
		recsAffected=stmt.executeUpdate();
			System.out.println("In Dao4");
			}catch(SQLException e){
				e.printStackTrace();
				throw new UniversityException("Problem in deleting  data in programOffered",e);
			}finally {
					try {
						if (connect != null) {
							stmt.close();
							connect.close();
						}
					} catch (Exception e) {
						e.printStackTrace();
						throw new UniversityException(
						"Could not close the connection in programOffered");
					}
			}
		if(recsAffected==1)
		{
			return true;
	}
		else
		return false;	
		
	
	}

	@Override
	public int addProgramScheduled(ProgramScheduledBean pScheduled)
			throws UniversityException {
		int recsAffected=0;
		int programId = getProgramId();
		ConnectionUtil util = new ConnectionUtil();
		connect = util.getConnection();
		PreparedStatement stmt = null;
		try{
			stmt = connect.prepareStatement(IQueryMapper.INSERT_PROGRAM_SCHEDULED);
			stmt.setInt(1,programId);
			stmt.setString(2,pScheduled.getProgramName());
			 //to convert date of birth local date to sql date
			stmt.setString(3,pScheduled.getLocation());
			stmt.setDate(4,new java.sql.Date(pScheduled.getStartDate().getTime()));
			stmt.setDate(5,new java.sql.Date(pScheduled.getEndDate().getTime()));
			stmt.setInt(6,pScheduled.getSessionPerWeek());
			
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
						"Could not close the connection in addScheduledDetail");
					}
			}
		return programId;
		
	}

	@Override
	public boolean deleteProgramScheduled(String programId)
			throws UniversityException {
		int recsAffected=0;
		System.out.println("In Dao");
		ConnectionUtil util = new ConnectionUtil();
		connect = util.getConnection();
		PreparedStatement stmt = null;
		System.out.println("In Dao2");
		try{
			stmt = connect.prepareStatement(IQueryMapper.DELETE_PROGRAM_SCHEDULED);
			stmt.setString(1,programId);
			System.out.println("In Dao3");
		recsAffected=stmt.executeUpdate();
			System.out.println("In Dao4");
			}catch(SQLException e){
				e.printStackTrace();
				throw new UniversityException("Problem in deleting  data in programOffered",e);
			}finally {
					try {
						if (connect != null) {
							stmt.close();
							connect.close();
						}
					} catch (Exception e) {
						e.printStackTrace();
						throw new UniversityException(
						"Could not close the connection in programOffered");
					}
			}
		if(recsAffected==1)
		{
			return true;
	}
		else
		return false;	
		
	}
	private int getProgramId() throws UniversityException 
	{
		ConnectionUtil util = new ConnectionUtil();
		connect = util.getConnection();
		int programId = 0;
		PreparedStatement pstmt = null;
		ResultSet res = null;
		try
		{
			pstmt= connect.prepareStatement(IQueryMapper.GEN_PROGID);
			res = pstmt.executeQuery();
			if(res.next())
				{
					programId = res.getInt(1);
				}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			throw new UniversityException("Problem in generating Program Id");
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
					"Could not close the connection in getProgramId()");
				}
			}
return programId;
}


	@Override
	public List<ProgramScheduledBean> getAllDetails()
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
}