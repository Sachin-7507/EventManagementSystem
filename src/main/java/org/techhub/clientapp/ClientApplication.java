package org.techhub.clientapp;
import java.util.*;

import org.techhub.helper.AdminHelper;
import org.techhub.helper.StudentHelper;
import org.techhub.model.AdminLogin;
import org.techhub.model.StudentLogin;
import org.techhub.service.ValidateAdmin;
import org.techhub.service.ValidateAdminImpl;
import org.techhub.service.ValidateStudent;
import org.techhub.service.ValidateStudentImpl;
public class ClientApplication {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		do {
			System.out.println("1.Admin Login");	
			System.out.println("2.Student Login");
			System.out.println("Enter your choice");
			int choice = sc.nextInt();
			
			switch(choice) {
			case 1:
				sc.nextLine();
				System.out.println("Enter Username");
				String username = sc.nextLine();
				System.out.println("Enter password");
				String password = sc.nextLine();
				
				AdminLogin login = new AdminLogin();
				login.setUsername(username);
				login.setPassword(password);
				
				ValidateAdmin validAdmin = new ValidateAdminImpl();
				boolean b= validAdmin.varifyAdminLogin(login);
				if(b) {
					System.out.println("Admin Login Successfully...");
					System.out.println();
					AdminHelper.startWorking();
				}
				else {
					System.out.println("Invalid Login....");
				}
				
				break;
				
			case 2:
				
				sc.nextLine();
				System.out.println("Enter Username");
				username = sc.nextLine();
				System.out.println("Enter password");
				int pass = sc.nextInt();
				StudentLogin slogin = new StudentLogin();
				slogin.setUsername(username);
				slogin.setPassword(pass);
				
				ValidateStudent validStudent = new ValidateStudentImpl();
				b=validStudent.varifyStudentLogin(slogin);
				
				if(b) {
					System.out.println("Student login Successsfully..");
					System.out.println();
					//System.out.println("Logged Student ID = " + StudentHelper.loggedStudentId);
					StudentHelper.startWorking();
				}
				else {
					System.out.println("Invalid Student login...");
				}
				
				break;
				
				default:
					System.out.println("Wrong choice");
			}
		}
		while(true);
	}

}
