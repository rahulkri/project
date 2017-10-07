package com.capgemini.uas.dao;

import java.util.List;

import com.capgemini.uas.dto.ProgramOfferedBean;
import com.capgemini.uas.dto.ProgramScheduledBean;
import com.capgemini.uas.exception.UniversityException;

public interface IAdminDao {
	public boolean addProgramOffered(ProgramOfferedBean pOffered) throws UniversityException;
	public boolean deleteProgramOffered(String programName) throws UniversityException;
	public int addProgramScheduled(ProgramScheduledBean pScheduled) throws UniversityException;
	public boolean deleteProgramScheduled(String programId) throws UniversityException;
	public List<ProgramScheduledBean> getAllDetails() throws UniversityException;
	
}
