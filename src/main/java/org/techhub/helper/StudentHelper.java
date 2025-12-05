package org.techhub.helper;

import java.util.*;
import org.techhub.model.EventModel;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Cell;
public class StudentHelper {
    public static int loggedStudentId;

    public static void startWorking() {
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("1.View All Events");
            System.out.println("2.Register for the Events");
            System.out.println("3.View All Registration");
            System.out.println("4.Cancle Registration");
            System.out.println("5.Check Event Capacity");
            System.out.println("6.Download Registration Report");
            
            System.out.println("Enter your choice");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    
                    System.out.println("1.Show Upcomming Events");
                    System.out.println("2.Show Previous Events");
                    System.out.println("Enter your choice");
                    int eventChoice = sc.nextInt();
                    sc.nextLine();

                    if (eventChoice == 1) {
                        System.out.println("\n================ Upcomming Events ================");
                        System.out.printf("%-5s %-25s %-15s %-15s\n", "ID", "Event Name", "Date", "Venue");
                        System.out.println("---------------------------------------------------------------");

                        List<EventModel> upEvent = ServiceHelper.regService.getUpEvents();
                        for (EventModel em : upEvent) {
                            System.out.printf("%-5d %-25s %-15s %-15s\n", em.getEventid(), em.getName(),
                                    em.getEventdate(), em.getVenue());
                        }
                        System.out.println("---------------------------------------------------------------");
                    } else if (eventChoice == 2) {
                        System.out.println("\n================ Previous Events ================");
                        System.out.printf("%-5s %-25s %-15s %-15s\n", "ID", "Event Name", "Date", "Venue");
                        System.out.println("---------------------------------------------------------------");

                        List<EventModel> prevEvent = ServiceHelper.regService.getPrevEvents();
                        for (EventModel e : prevEvent) {
                            System.out.printf("%-5d %-25s %-15s %-15s\n", e.getEventid(), e.getName(),
                                    e.getEventdate(), e.getVenue());
                        }
                        System.out.println("---------------------------------------------------------------");
                    } else {
                        System.out.println("Wrong choice");
                    }
                    break;

                case 2:
                    System.out.println("\n================ Upcomming Events ================");
                    System.out.printf("%-5s %-25s %-15s %-15s\n", "ID", "Event Name", "Date", "Venue");
                    System.out.println("---------------------------------------------------------------");

                    List<EventModel> upEvent = ServiceHelper.regService.getUpEvents();
                    for (EventModel em : upEvent) {
                        System.out.printf("%-5d %-25s %-15s %-15s\n", em.getEventid(), em.getName(),
                                em.getEventdate(), em.getVenue());
                    }
                    System.out.println("---------------------------------------------------------------");

                    System.out.print("Enter Event ID to Register: ");
                    int eid = sc.nextInt();
                    sc.nextLine();

                    boolean registered = ServiceHelper.regService.isRegister(StudentHelper.loggedStudentId, eid);

                    if (registered)
                        System.out.println("Registered Successfully....");
                    else
                        System.out.println("You are already registered for this event....");
                    break;

                case 3:
                    ServiceHelper.regService.getRegisteredEvent(StudentHelper.loggedStudentId);
                    break;
                    
                case 4:
                	 ServiceHelper.regService.getRegisteredEvent(StudentHelper.loggedStudentId);
                	 System.out.println("Enter the Event ID to cancle the event");
                	 int eventid=sc.nextInt();
                	 
                	  boolean b=ServiceHelper.regService.isCancelReg(eventid);
                	  if(b) {
                		  System.out.println("Registration Cancle Successfully..");
                		  System.out.println();
                	  }
                	  else {
                		  System.out.println("Registration Not Cancled..");
                		  System.out.println();
                	  }
                	  
                	break;
                	
                	
                case 5:
                	System.out.println("---------------  Event Capacity--------------------");
                	List<EventModel> ls = ServiceHelper.regService.getEventCapacity();
                
                	break;

                case 6:
                    List<Map<String, String>> regs = ServiceHelper.regService.getUserRegistrationPDFData(StudentHelper.loggedStudentId);
                    if (regs.isEmpty()) {
                        System.out.println("No registrations to generate PDF.");
                        break;
                    }

                    String pdfPath = "RegistrationReport.pdf";
                    try {
                        PdfWriter writer = new PdfWriter(pdfPath);
                        PdfDocument pdfDoc = new PdfDocument(writer);
                        Document document = new Document(pdfDoc);

                        document.add(new Paragraph("Registration Report")
                                .setBold()
                                .setFontSize(18)
                                .setMarginBottom(10));
                        document.add(new Paragraph("Total Registrations: " + regs.size())
                                .setBold()
                                .setMarginBottom(20));

                        Table table = new Table(new float[]{1, 4, 3, 3, 2, 2});
                        table.setWidth(100);

                        table.addHeaderCell("Sr No");
                        table.addHeaderCell("Event Name");
                        table.addHeaderCell("Venue");
                        table.addHeaderCell("Date");
                        table.addHeaderCell("Attended");
                        table.addHeaderCell("Cancelled");

                        int sr = 1;
                        for (Map<String, String> r : regs) {
                            table.addCell(String.valueOf(sr++));
                            table.addCell(r.get("eventName"));
                            table.addCell(r.get("venue"));
                            table.addCell(r.get("eventDate"));
                            table.addCell(r.get("attended"));
                            table.addCell(r.get("cancelled"));
                        }

                        document.add(table);
                        document.close();

                        System.out.println("PDF generated successfully");

                    } catch (Exception ex) {
                        System.out.println("Error generating PDF: " + ex);
                    }
                    break;


                default:
                    System.out.println("Wrong choice");
            }
        } while (true);
    }
}
