package org.techhub.service;

import org.techhub.model.AdminLogin;
import org.techhub.repositery.ValidateAdminRepo;
import org.techhub.repositery.ValidateAdminRepoImpl;

public class ValidateAdminImpl implements ValidateAdmin{

	ValidateAdminRepo adminRepo = new ValidateAdminRepoImpl();  // repo
	@Override
	public boolean varifyAdminLogin(AdminLogin login) {
		
		return adminRepo.varifyAdminLogin(login);
	}

}
