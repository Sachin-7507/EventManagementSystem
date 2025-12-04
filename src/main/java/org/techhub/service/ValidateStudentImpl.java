package org.techhub.service;

import org.techhub.model.StudentLogin;
import org.techhub.repositery.ValidateStudentRepo;
import org.techhub.repositery.ValidateStudentRepoImpl;

public class ValidateStudentImpl implements ValidateStudent{

	ValidateStudentRepo valStud = new ValidateStudentRepoImpl();
	@Override
	public boolean varifyStudentLogin(StudentLogin login) {
		// TODO Auto-generated method stub
		return valStud.varifyStudentLogin(login);
	}

}
