package org.techhub.repositery;

import java.io.File;
import java.io.FileOutputStream;
import java.util.*;

import org.techhub.bdconfig.DBInitialize;
import org.techhub.model.EventModel;
import org.techhub.model.RegistrationModel;

import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Cell;

public class EventRepoImpl extends DBInitialize implements EventRepo{

	
	
	@Override
	public boolean isAddEvent(EventModel model) {
		try {
			stmt = conn.prepareStatement("insert into campuseventdb (name, eventdate, venue, capacity ) values(?,?,?,?)");
			stmt.setString(1, model.getName());
			stmt.setString(2, model.getEventdate());
			stmt.setString(3, model.getVenue());
			stmt.setInt(4, model.getCapacity());
			int value= stmt.executeUpdate();
			return value>0? true: false;
			
			
		}
		catch(Exception ex) {
			System.out.println("Error is"+ex);
		}
		return false;
	}

	@Override
	public List<EventModel> getAllEvents() {
		List<EventModel> eventList = new ArrayList<>();
		try {
			stmt = conn.prepareStatement("select * from campuseventdb");
			rs=stmt.executeQuery();
			
			
			while(rs.next()) {
				EventModel model=new EventModel();
				model.setEventid(rs.getInt(1));
				model.setName(rs.getString(2));
				model.setEventdate(rs.getString(3));
				model.setVenue(rs.getString(4));
				model.setCapacity(rs.getInt(5));
				eventList.add(model);
			}
				
		}
		catch(Exception ex) {
			System.out.println("Error is"+ex);
		}
		return eventList;
	}

	@Override
	public boolean isUpdateEvent(EventModel model) {
		try {
			stmt = conn.prepareStatement("update campuseventdb set name=?, eventdate=?, venue=?, capacity=?  where eventid=?");
			
			
			stmt.setString(1, model.getName());
			stmt.setString(2, model.getEventdate());
			stmt.setString(3, model.getVenue());
			stmt.setInt(4, model.getCapacity());
			stmt.setInt(5, model.getEventid());
			int value=stmt.executeUpdate();
			
			return value>0?true:false;
		}
		catch(Exception ex) {
			System.out.println("Error is"+ex);
		}
		return false;
	}

	@Override
	public List<EventModel> getUpcommingEvent() {
		List<EventModel> upcomEvent = new ArrayList<>();
		try {
			 stmt = conn.prepareStatement( "select * from campuseventdb where eventdate > CURDATE()");
			 rs = stmt.executeQuery();
			 
			 while(rs.next()) {
				 EventModel em = new EventModel();
				 em.setEventid(rs.getInt(1));
				 em.setName(rs.getString(2));
				 em.setEventdate(rs.getString(3));
				 em.setVenue(rs.getString(4));
				 em.setCapacity(rs.getInt(5));
				 upcomEvent.add(em);
			 }
		}
		catch(Exception ex) {
			System.out.println("Error is "+ex);
		}
		return upcomEvent;
	}

	@Override
	public boolean isDeleteEvent(int id) {
		try {
			stmt = conn.prepareStatement("delete from campuseventdb where eventid=?");
			stmt.setInt(1, id);
			int value = stmt.executeUpdate();
			return value>0?true:false;

		}
		catch(Exception ex) {
			System.out.println("Error is "+ex);
		}
		return false;
	}

	@Override
	public void getUpEventReport(List<EventModel> events) {
	    if (events == null || events.isEmpty()) {
	        System.out.println("No upcoming events to generate PDF.");
	        return;
	    }

	    String pdfPath = "UpcomingEvents.pdf"; // will be created in project root

	    try {
	        PdfWriter writer = new PdfWriter(pdfPath);
	        PdfDocument pdfDoc = new PdfDocument(writer);
	        Document document = new Document(pdfDoc);

	        // Title
	        document.add(new Paragraph("Upcoming Events Report")
	                .setBold()
	                .setFontSize(18)
	                .setMarginBottom(20));

	        // Table with 5 columns
	        Table table = new Table(new float[]{1, 4, 3, 4, 2}); // ID, Name, Date, Venue, Capacity
	        table.setWidth(100);

	        // Header
	        table.addHeaderCell(new Cell().add(new Paragraph("ID")));
	        table.addHeaderCell(new Cell().add(new Paragraph("Name")));
	        table.addHeaderCell(new Cell().add(new Paragraph("Date")));
	        table.addHeaderCell(new Cell().add(new Paragraph("Venue")));
	        table.addHeaderCell(new Cell().add(new Paragraph("Capacity")));

	        // Data rows
	        for (EventModel e : events) {
	            table.addCell(String.valueOf(e.getEventid()));
	            table.addCell(e.getName());
	            table.addCell(e.getEventdate());
	            table.addCell(e.getVenue());
	            table.addCell(String.valueOf(e.getCapacity()));
	        }

	        document.add(table);
	        document.close();
	        
	        System.out.println("======================================");
	        System.out.println("✅ PDF Download Successful!");
	        System.out.println("File saved at: " + pdfPath);
	        System.out.println("======================================");

	        System.out.println("PDF generated successfully: " + pdfPath);

	    } catch (Exception ex) {
	        System.out.println("Error while generating PDF: " + ex);
	    }
	}

	@Override

	public List<EventModel> getEventWiseReg() {
	    try {
	        stmt = conn.prepareStatement(
	            "SELECT r.regid AS RegId, r.studentid, r.eventid, r.regdate, " +
	            "s.name AS StudentName, s.department AS Department, s.email AS Email, " +
	            "e.name AS EventName, e.eventdate AS EventDate, e.venue AS Venue " +
	            "FROM student s " +
	            "INNER JOIN registration r ON s.studentid = r.studentid " +
	            "INNER JOIN campuseventdb e ON r.eventid = e.eventid " +
	            "ORDER BY r.eventid"
	        );
	        rs = stmt.executeQuery();

	        System.out.println("\n---------Event wise Student Data---------------------");
	        System.out.printf("%-5s %-20s %-15s %-25s %-20s %-15s\n",
	                "RID", "Student Name", "Dept", "Email", "Event Name", "Event Date");
	        System.out.println("----------------------------------------------------------------------------");

	        while (rs.next()) {
	            System.out.printf("%-5d %-20s %-15s %-25s %-20s %-15s\n",
	                    rs.getInt("RegId"),
	                    rs.getString("StudentName"),
	                    rs.getString("Department"),
	                    rs.getString("Email"),
	                    rs.getString("EventName"),
	                    rs.getString("EventDate")
	            );
	        }

	        System.out.println("----------------------------------------------------------------------------");

	    } catch (Exception ex) {
	        System.out.println("Error is: " + ex);
	    }

	    return null;
	}



}
