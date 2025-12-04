package org.techhub.repositery;

import java.util.List;

import org.techhub.model.EventModel;

public interface EventRepo {
	public boolean isAddEvent(EventModel model);
	public List<EventModel> getAllEvents();
	public boolean isUpdateEvent(EventModel model);
	public List<EventModel> getUpcommingEvent();
	public boolean isDeleteEvent(int id);
	public void getUpEventReport(List<EventModel> events);
	public List<EventModel> getEventWiseReg();
}
