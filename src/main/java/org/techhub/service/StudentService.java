package org.techhub.service;
import java.util.*;

import org.techhub.model.EventModel;
import org.techhub.model.StudentModel;

public interface StudentService {
	public boolean idAddStudent(StudentModel model);
	public List<StudentModel> getAllStudent();
	public boolean isDeleteStudent(int id);
	public boolean isUpdateStudent(StudentModel model);
	public List<StudentModel> getStudentsByDepartment(String department);
	public List<StudentModel> getStudentsByEmail(String email);
	public void geStudentReport(List<StudentModel> student);
	
	
}
