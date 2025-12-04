package org.techhub.service;
import java.util.*;
import org.techhub.model.EventModel;

public interface EventService {
	public boolean isAddEvent(EventModel model);
	public List<EventModel> getAllEvents();
	public boolean isUpdateEvent(EventModel model);
	public List<EventModel> getUpcommingEvent();
	public boolean isDeleteEvent(int id);
	public void getUpEventReport(List<EventModel> events);
	public List<EventModel> getEventWiseReg();
}
