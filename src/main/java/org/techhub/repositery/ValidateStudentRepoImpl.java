package org.techhub.repositery;

import org.techhub.bdconfig.DBInitialize;
import org.techhub.helper.StudentHelper;
import org.techhub.model.StudentLogin;

public class ValidateStudentRepoImpl extends DBInitialize implements ValidateStudentRepo{

	@Override
	public boolean varifyStudentLogin(StudentLogin login) {
		try {
			stmt = conn.prepareStatement("select  * from student WHERE email=? AND contact=?");
			stmt.setString(1, login.getUsername());
			stmt.setInt(2,  login.getPassword());
			
			rs=stmt.executeQuery();
			if(rs.next()) {
				 StudentHelper.loggedStudentId = rs.getInt("studentid");
				return true;
			}
			else {
				return false;
			}	
		}
		catch(Exception ex) {
			System.out.println("Error is"+ex);
		}
		return false;
	}

}
