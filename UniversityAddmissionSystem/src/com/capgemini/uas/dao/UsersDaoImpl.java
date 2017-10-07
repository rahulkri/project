package com.capgemini.uas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.capgemini.uas.dto.UsersBean;
import com.capgemini.uas.exception.UniversityException;
import com.capgemini.uas.util.ConnectionUtil;

public class UsersDaoImpl implements IUsersDao {
	
	private Connection connect;

	@Override
	public UsersBean getUserOnId(String loginId) throws UniversityException {
		UsersBean userBean = null;
		ConnectionUtil util = new ConnectionUtil();
		connect = util.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
				stmt = connect.prepareStatement(IQueryMapper.GET_USER);
				stmt.setString(1,loginId);
				rs = stmt.executeQuery();
				while(rs.next()){
					String password = rs.getString(2);
					String role = rs.getString(3);
					userBean = new UsersBean(loginId, password, role);
				}
				}catch(SQLException e){
					e.printStackTrace();
					throw new UniversityException("Problem in writing data in getUserOnId",e);
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
							"Could not close the connection in getUserOnId");
						}
				}
		return userBean;
	}

}
