package org.techhub.repositery;

import org.techhub.bdconfig.DBInitialize;
import org.techhub.model.AdminLogin;

public class ValidateAdminRepoImpl extends DBInitialize implements ValidateAdminRepo{

	@Override
	public boolean varifyAdminLogin(AdminLogin login) {
		try {
			stmt=conn.prepareStatement("select * from adminlogin where username=? and password=?");
			stmt.setString(1, login.getUsername());
			stmt.setString(2, login.getPassword());
			
			rs=stmt.executeQuery();
			if(rs.next()) {
				return true;
			}
			else {
				return false;
			}
			
		}
		catch(Exception ex) {
			System.out.println("Error is "+ex.getMessage());
			
		}
		return false;
	}

}
