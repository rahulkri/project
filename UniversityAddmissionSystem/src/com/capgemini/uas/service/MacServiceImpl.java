package com.capgemini.uas.service;

import java.time.LocalDate;
import java.util.List;

import com.capgemini.uas.dao.IMacDao;
import com.capgemini.uas.dao.MacDaoImpl;
import com.capgemini.uas.dto.ApplicationBean;
import com.capgemini.uas.dto.UsersBean;
import com.capgemini.uas.exception.UniversityException;

public class MacServiceImpl implements IMacService {

	private IMacDao macDao;
	public MacServiceImpl() {
		macDao = new MacDaoImpl();
	}
	@Override
	public List<ApplicationBean> getApplicantsOnSchduledId(
			String scheduledProgramId) throws UniversityException {
		return macDao.getApplicantsOnSchduledId(scheduledProgramId);
	}

	@Override
	public List<ApplicationBean> getApplicantsAfterInterviewOnId(
			String scheduledProgramId) throws UniversityException {
		return macDao.getApplicantsAfterInterviewOnId(scheduledProgramId);
	}

	@Override
	public String updateApplicantStatus(int applicationId, String status)
			throws UniversityException {
		return macDao.updateApplicantStatus(applicationId,status);
	}
	@Override
	public void updateApplicantDateOfInterview(int applicationId, LocalDate dateOfInterview)
			throws UniversityException {
			macDao.updateApplicantDateOfInterview(applicationId, dateOfInterview);
	}
}
