package com.capgemini.uas.service;

import java.util.List;

import com.capgemini.uas.dao.AdminDaoImpl;
import com.capgemini.uas.dao.IAdminDao;
import com.capgemini.uas.dto.ProgramOfferedBean;
import com.capgemini.uas.dto.ProgramScheduledBean;
import com.capgemini.uas.exception.UniversityException;

public class AdminServiceImpl implements IAdminService {
private IAdminDao dao;
public AdminServiceImpl()
{
	dao=new AdminDaoImpl();
}
	@Override
	public boolean addProgramOffered(ProgramOfferedBean pOffered)
			throws UniversityException {
		// TODO Auto-generated method stub
		return dao.addProgramOffered(pOffered);
	}

	@Override
	public boolean deleteProgramOffered(String programName)
			throws UniversityException {
		// TODO Auto-generated method stub
		return dao.deleteProgramOffered(programName);
	}

	@Override
	public int addProgramScheduled(ProgramScheduledBean pScheduled)
			throws UniversityException {
		// TODO Auto-generated method stub
		return dao.addProgramScheduled(pScheduled);
	}

	@Override
	public boolean deleteProgramScheduled(String programId)
			throws UniversityException {
		// TODO Auto-generated method stub
		return dao.deleteProgramScheduled(programId);
	}
	@Override
	public List<ProgramScheduledBean> getAllDetails()
			throws UniversityException {
		// TODO Auto-generated method stub
		return dao.getAllDetails();
	}

}
