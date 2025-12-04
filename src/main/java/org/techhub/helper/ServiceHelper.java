package org.techhub.helper;

import org.techhub.service.EventService;
import org.techhub.service.EventServiceImpl;
import org.techhub.service.RegistrationService;
import org.techhub.service.RegistrationServiceImpl;
import org.techhub.service.StudentService;
import org.techhub.service.StudentServiceImpl;

public class ServiceHelper {
	public static EventService eventService = new EventServiceImpl();
	public static StudentService studentService = new StudentServiceImpl();
	public static RegistrationService regService = new RegistrationServiceImpl();
}
