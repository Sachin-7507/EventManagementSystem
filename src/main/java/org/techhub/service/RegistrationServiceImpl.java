package org.techhub.service;

import java.util.List;
import java.util.Map;

import org.techhub.model.EventModel;
import org.techhub.model.RegistrationModel;
import org.techhub.repositery.RegistrationRepo;
import org.techhub.repositery.RegistrationRepoImpl;

public class RegistrationServiceImpl implements RegistrationService {

    RegistrationRepo repo = new RegistrationRepoImpl();
    
    @Override
    public List<EventModel> getUpEvents() {
        return repo.getUpEvents();
    }

	@Override
	public List<EventModel> getPrevEvents() {
		// TODO Auto-generated method stub
		return repo.getPrevEvents();
	}

	@Override
	public boolean isRegister(int studentid, int eventid) {
		// TODO Auto-generated method stub
		return repo.isRegister(studentid, eventid);
	}

	@Override
	public List<RegistrationModel> getRegisteredEvent(int studentid) {
		// TODO Auto-generated method stub
		return repo.getRegisteredEvent(studentid);
	}

	@Override
	public boolean isCancelReg(int eventid) {
	
		return repo.isCancelReg(eventid);
	}

	@Override
	public List<Map<String, String>> getUserRegistrationPDFData(int studentId) {
		// TODO Auto-generated method stub
		return repo.getUserRegistrationPDFData(studentId);
	}
}
