package com.capgemini.uas.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.capgemini.uas.exception.UniversityException;

public class ConnectionUtil {
	private Connection connect;

	public ConnectionUtil() throws UniversityException {
		PropertiesServices propServices = new PropertiesServices(); 
		String url = propServices.getPropValue("url");
		String userName = propServices.getPropValue("userName");
		String password = propServices.getPropValue("password");
		try {
			connect = DriverManager.getConnection(url,userName,password);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new UniversityException("Connection opening failed");
		}
	}
	public Connection getConnection(){
		return connect;
	}

}