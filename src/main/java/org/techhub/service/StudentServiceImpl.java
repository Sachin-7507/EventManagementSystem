package org.techhub.service;

import java.util.List;

import org.techhub.model.StudentModel;
import org.techhub.repositery.EventRepo;
import org.techhub.repositery.EventRepoImpl;
import org.techhub.repositery.StudentRepositery;
import org.techhub.repositery.StudentRepositeryImpl;

public class StudentServiceImpl implements StudentService{

	StudentRepositery studentRepo = new StudentRepositeryImpl();
	@Override
	public boolean idAddStudent(StudentModel model) {
		// TODO Auto-generated method stub
		return studentRepo.idAddStudent(model);
	}
	@Override
	public List<StudentModel> getAllStudent() {
		// TODO Auto-generated method stub
		return studentRepo.getAllStudent();
	}
	@Override
	public boolean isDeleteStudent(int id) {
		// TODO Auto-generated method stub
		return studentRepo.isDeleteStudent(id);
	}
	@Override
	public boolean isUpdateStudent(StudentModel model) {
		// TODO Auto-generated method stub
		return studentRepo.isUpdateStudent(model);
	}
	@Override
	public List<StudentModel> getStudentsByDepartment(String department) {
		// TODO Auto-generated method stub
		return studentRepo.getStudentsByDepartment(department);
	}
	@Override
	public List<StudentModel> getStudentsByEmail(String email) {
		// TODO Auto-generated method stub
		return studentRepo.getStudentsByEmail(email);
	}
	@Override
	public void geStudentReport(List<StudentModel> student) {
		StudentRepositery studRepo = new StudentRepositeryImpl();
		studRepo.geStudentReport(student);
		
	}
}
