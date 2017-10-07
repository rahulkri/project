package com.capgemini.uas.service;

import com.capgemini.uas.dao.IUsersDao;
import com.capgemini.uas.dao.UsersDaoImpl;
import com.capgemini.uas.dto.UsersBean;
import com.capgemini.uas.exception.UniversityException;

public class UsersServiceImpl implements IUsersService {
	
	private IUsersDao uDao;
	
	public UsersServiceImpl() {
		uDao = new UsersDaoImpl();
	}

	@Override
	public void checkUser(UsersBean userBeanMain) throws UniversityException {
		UsersBean userBeanDao = uDao.getUserOnId(userBeanMain.getLoginId());
		if (userBeanDao==null)
			throw new UniversityException("No Such UserName Found");
		else {
			if(userBeanDao.getPassword().equals(userBeanMain.getPassword())) {
				if(!userBeanDao.getRole().equals(userBeanMain.getRole()))
					throw new UniversityException("Not a valid "+userBeanMain.getRole());
			}
			else{
				throw new UniversityException("Password is Wrong");
			}
		}
	}

}
