package org.techhub.service;

import java.util.List;

import org.techhub.model.EventModel;
import org.techhub.repositery.EventRepo;
import org.techhub.repositery.EventRepoImpl;

public class EventServiceImpl implements EventService{

	EventRepo eventRepo = new EventRepoImpl();
	@Override
	public boolean isAddEvent(EventModel model) {
		
		return eventRepo.isAddEvent(model);
	}
	@Override
	public List<EventModel> getAllEvents() {
		
		return eventRepo.getAllEvents();
	}
	@Override
	public boolean isUpdateEvent(EventModel model) {
		// TODO Auto-generated method stub
		return eventRepo.isUpdateEvent(model);
	}
	@Override
	public List<EventModel> getUpcommingEvent() {
		// TODO Auto-generated method stub
		return eventRepo.getUpcommingEvent();
	}
	@Override
	public boolean isDeleteEvent(int id) {
		// TODO Auto-generated method stub
		return eventRepo.isDeleteEvent(id);
	}
	
	@Override
	public void getUpEventReport(List<EventModel> events) {
		 EventRepo eventRepo = new EventRepoImpl();
		    eventRepo.getUpEventReport(events);
	}
	@Override
	public List<EventModel> getEventWiseReg() {
		// TODO Auto-generated method stub
		return eventRepo.getEventWiseReg();
	}

}
