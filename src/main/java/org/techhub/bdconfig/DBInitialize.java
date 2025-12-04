package org.techhub.bdconfig;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

public class DBInitialize {
	protected Connection conn;
	protected PreparedStatement stmt;
	protected ResultSet rs;
	
	public DBInitialize() {
		
		try {
			File f = new File("");
			String rootpath = f.getAbsolutePath()+"\\src\\main\\resources\\db.properties";
			FileInputStream fin=new FileInputStream(rootpath);
			Properties p=new Properties();
			p.load(fin);
			String username = p.getProperty("username");
			String password = p.getProperty("password");
			String url = p.getProperty("url");
			String driverClass = p.getProperty("driver");
			
			Class.forName(driverClass);
			conn=DriverManager.getConnection(url, username, password);
			
		}
		catch(Exception ex) {
			System.out.println("Error is"+ex);
		}	
	}	
}
