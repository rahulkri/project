package com.capgemini.uas.service;

import java.util.List;

import com.capgemini.uas.dao.ApplicantDaoImpl;
import com.capgemini.uas.dao.IApplicantDao;
import com.capgemini.uas.dto.ApplicationBean;
import com.capgemini.uas.dto.ProgramScheduledBean;
import com.capgemini.uas.exception.UniversityException;

public class ApplicantServiceImpl implements IApplicantService {
	private IApplicantDao appDao;
	
	public ApplicantServiceImpl() throws UniversityException {
		appDao = new ApplicantDaoImpl();
	}

	@Override
	public List<ProgramScheduledBean> showProgramScheduled()
			throws UniversityException {
		// TODO Auto-generated method stub
		return appDao.showProgramScheduled();
	}

	@Override
	public int addDetail(ApplicationBean ab) throws UniversityException {
		// TODO Auto-generated method stub
		return appDao.addDetail(ab);
	}

	@Override
	public ApplicationBean showStatus(int applicationId)
			throws UniversityException {
		// TODO Auto-generated method stub
		return appDao.showStatus(applicationId);
	}

}
