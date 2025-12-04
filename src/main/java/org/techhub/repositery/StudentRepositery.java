package org.techhub.repositery;

import java.util.List;

import org.techhub.model.StudentModel;

public interface StudentRepositery {
	
		public boolean idAddStudent(StudentModel model);
		public List<StudentModel> getAllStudent();
		public boolean isDeleteStudent(int id);
		public boolean isUpdateStudent(StudentModel model);
		public List<StudentModel> getStudentsByDepartment(String department);
		public List<StudentModel> getStudentsByEmail(String email);
		public void geStudentReport(List<StudentModel> student);
}
