package org.techhub.repositery;

import java.util.List;
import java.util.Map;

import org.techhub.model.EventModel;
import org.techhub.model.RegistrationModel;

public interface RegistrationRepo {
    List<EventModel> getUpEvents();
    List<EventModel> getPrevEvents();
    public boolean isRegister(int studentid, int eventid);
    public List<RegistrationModel> getRegisteredEvent(int studentid);
    public boolean isCancelReg(int eventid);
    public List<Map<String, String>> getUserRegistrationPDFData(int studentId);
    public List<EventModel> getEventCapacity();
}
