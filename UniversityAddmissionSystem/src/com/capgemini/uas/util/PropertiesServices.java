package com.capgemini.uas.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;
import com.capgemini.uas.exception.UniversityException;

public class PropertiesServices {
	private Properties props;
	public PropertiesServices() throws UniversityException{
		props = new Properties();
		try (FileInputStream fis = new FileInputStream("Projectdb.properties");){
			props.load(fis);
		} catch (IOException e) {
			throw new UniversityException("Property file missing",e);
		}
	}
	public String getPropValue(String prop){
		return props.getProperty(prop);
	}
	
	public Set<Object> getPropKeys(){
		Set<Object> Keys = props.keySet();
		return Keys;
	}
}