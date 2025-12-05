package org.techhub.helper;

import java.util.List;
import java.util.Scanner;
import org.techhub.model.EventModel;
import org.techhub.model.StudentModel;
import org.techhub.service.EventServiceImpl;
import org.techhub.service.StudentService;
import org.techhub.service.StudentServiceImpl;

public class AdminHelper {

    public static void startWorking() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("1. Event Master");
            System.out.println("2. Student Master");
            System.out.println("3. Back to login");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    eventMaster();
                    break;

                case 2:
                    studentMaster();
                    break;

                case 3:
                    
                    return; 

                default:
                    System.out.println("Wrong choice!");
            }
        }
    }

    private static void eventMaster() {
    	Scanner sc=new Scanner(System.in);
        EventServiceImpl eventService = new EventServiceImpl();
        boolean back = false;

        while (!back) {
            System.out.println("\n============ Event Master ==============");
            System.out.println("1. Add Event");
            System.out.println("2. View All Events");
            System.out.println("3. Update Event by ID");
            System.out.println("4. View All Upcoming Events");
            System.out.println("5. Delete Event by ID");
            System.out.println("6. Get Upcoming Events Report");
            System.out.println("7.View All Registration Event wise");
            System.out.println("8. Back to Home");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Event name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Event date: ");
                    String date = sc.nextLine();
                    System.out.print("Enter Venue: ");
                    String venue = sc.nextLine();
                    System.out.print("Enter Capacity: ");
                    int capacity = sc.nextInt();
                    sc.nextLine();

                    EventModel e = new EventModel();
                    e.setName(name);
                    e.setEventdate(date);
                    e.setVenue(venue);
                    e.setCapacity(capacity);

                    if (ServiceHelper.eventService.isAddEvent(e))
                        System.out.println("Event added successfully.");
                    else
                        System.out.println("Event not added.");
                    break;

                case 2:
                    printEventList(ServiceHelper.eventService.getAllEvents(), "All Events");
                    break;

                case 3:
                    System.out.print("Enter Event ID to update: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter new name: ");
                    name = sc.nextLine();
                    System.out.print("Enter new date: ");
                    date = sc.nextLine();
                    System.out.print("Enter new venue: ");
                    venue = sc.nextLine();
                    System.out.print("Enter new capacity: ");
                    capacity = sc.nextInt();
                    sc.nextLine();

                    EventModel em = new EventModel();
                    em.setEventid(id);
                    em.setName(name);
                    em.setEventdate(date);
                    em.setVenue(venue);
                    em.setCapacity(capacity);

                    if (ServiceHelper.eventService.isUpdateEvent(em))
                        System.out.println("Event updated successfully.");
                    else
                        System.out.println("Event not updated.");
                    break;

                case 4:
                    printEventList(ServiceHelper.eventService.getUpcommingEvent(), "Upcoming Events");
                    break;

                case 5:
                    System.out.print("Enter Event ID to delete: ");
                    id = sc.nextInt();
                    sc.nextLine();
                    if (ServiceHelper.eventService.isDeleteEvent(id))
                        System.out.println("Event deleted successfully.");
                    else
                        System.out.println("Event not deleted.");
                    break;

                case 6:
                    List<EventModel> upcoming = ServiceHelper.eventService.getUpcommingEvent();
                    ServiceHelper.eventService.getUpEventReport(upcoming);
                    System.out.println("Report generated.");
                    break;
                    
                case 7:
                	System.out.println("---------Event wise Student Data---------------------");
                	ServiceHelper.eventService.getEventWiseReg();

                case 8:
                    back = true;
                    break;

                default:
                    System.out.println("Wrong choice!");
            }
        }
    }

    private static void studentMaster() {
    	Scanner sc=new Scanner(System.in);
        StudentService studentService = new StudentServiceImpl();
        boolean back = false;

        while (!back) {
            System.out.println("\n============ Student Master ==============");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Delete Student by ID");
            System.out.println("4. Update Student by ID");
            System.out.println("5. Search Student by Department");
            System.out.println("6. Search Student by Email");
            System.out.println("7. Download Student Report");
            System.out.println("8. Back to Home");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter email: ");
                    String email = sc.nextLine();
                    System.out.print("Enter department: ");
                    String dept = sc.nextLine();
                    System.out.print("Enter contact: ");
                    int contact = sc.nextInt();
                    sc.nextLine();

                    StudentModel s = new StudentModel();
                    s.setName(name);
                    s.setEmail(email);
                    s.setDept(dept);
                    s.setContact(contact);

                    if (ServiceHelper.studentService.idAddStudent(s))
                        System.out.println("Student added successfully.");
                    else
                        System.out.println("Student not added.");
                    break;

                case 2:
                    List<StudentModel> students = ServiceHelper.studentService.getAllStudent();
                    System.out.printf("%-10s %-20s %-25s %-15s %-12s\n", "ID", "Name", "Email", "Department", "Contact");
                    System.out.println("--------------------------------------------------------------------------");
                    for (StudentModel sm : students) {
                        System.out.printf("%-10d %-20s %-25s %-15s %-12d\n",
                                sm.getStudentId(), sm.getName(), sm.getEmail(), sm.getDept(), sm.getContact());
                    }
                    break;

                case 3:
                    System.out.print("Enter Student ID to delete: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    if (ServiceHelper.studentService.isDeleteStudent(id))
                        System.out.println("Student deleted successfully.");
                    else
                        System.out.println("Student not deleted.");
                    break;

                case 4:
                    System.out.print("Enter Student ID to update: ");
                    id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter new name: ");
                    name = sc.nextLine();
                    System.out.print("Enter new email: ");
                    email = sc.nextLine();
                    System.out.print("Enter new department: ");
                    dept = sc.nextLine();
                    System.out.print("Enter new contact: ");
                    contact = sc.nextInt();
                    sc.nextLine();

                    StudentModel sm = new StudentModel();
                    sm.setStudentId(id);
                    sm.setName(name);
                    sm.setEmail(email);
                    sm.setDept(dept);
                    sm.setContact(contact);

                    if (ServiceHelper.studentService.isUpdateStudent(sm))
                        System.out.println("Student updated successfully.");
                    else
                        System.out.println("Student not updated.");
                    break;

                case 5:
                    System.out.print("Enter department: ");
                    dept = sc.nextLine();
                    List<StudentModel> byDept = ServiceHelper.studentService.getStudentsByDepartment(dept);
                    for (StudentModel st : byDept)
                        System.out.println(st.getStudentId() + "\t" + st.getName() + "\t" + st.getEmail() + "\t" + st.getDept() + "\t" + st.getContact());
                    break;

                case 6:
                    System.out.print("Enter email: ");
                    email = sc.nextLine();
                    List<StudentModel> byEmail = ServiceHelper.studentService.getStudentsByEmail(email);
                    for (StudentModel st : byEmail)
                        System.out.println(st.getStudentId() + "\t" + st.getName() + "\t" + st.getEmail() + "\t" + st.getDept() + "\t" + st.getContact());
                    break;

                case 7:
                    List<StudentModel> report = ServiceHelper.studentService.getAllStudent();
                    ServiceHelper.studentService.geStudentReport(report); 
                    break;

                case 8:
                    back = true;
                    break;

                default:
                    System.out.println("Wrong choice!");
            }
        }
    }
    
    private static void printEventList(List<EventModel> events, String title) {
    	
        System.out.println("\n================ " + title + " ================");
        System.out.printf("%-5s %-20s %-12s %-15s %-8s\n", "ID", "Name", "Event Date", "Venue", "Capacity");
        System.out.println("------------------------------------------------------------");
        for (EventModel e : events) {
            System.out.printf("%-5d %-20s %-12s %-15s %-8d\n",
                    e.getEventid(), e.getName(), e.getEventdate(), e.getVenue(), e.getCapacity());
        }
        System.out.println("------------------------------------------------------------");
    }
}
