package org.techhub.repositery;

import java.sql.*;
import java.util.*;
import org.techhub.bdconfig.DBInitialize;
import org.techhub.helper.StudentHelper;
import org.techhub.model.EventModel;
import org.techhub.model.RegistrationModel;

public class RegistrationRepoImpl extends DBInitialize implements RegistrationRepo {

	@Override
	public List<EventModel> getUpEvents() {
		List<EventModel> upEvList = new ArrayList<>();
		try {
			stmt = conn.prepareStatement("select * from campuseventdb where eventdate > CURDATE()");
			rs = stmt.executeQuery();

			while (rs.next()) {
				EventModel e = new EventModel();
				e.setEventid(rs.getInt(1));
				e.setName(rs.getString(2));
				e.setEventdate(rs.getString(3));
				e.setVenue(rs.getString(4));
				upEvList.add(e);
			}
		} catch (Exception ex) {
			System.out.println("Error is " + ex);
		}
		return upEvList;
	}

	@Override
	public List<EventModel> getPrevEvents() {
		List<EventModel> prevEvList = new ArrayList<>();
		try {
			stmt = conn.prepareStatement("select * from campuseventdb where eventdate < CURDATE()");
			rs = stmt.executeQuery();

			while (rs.next()) {
				EventModel e = new EventModel();
				e.setEventid(rs.getInt(1));
				e.setName(rs.getString(2));
				e.setEventdate(rs.getString(3));
				e.setVenue(rs.getString(4));
				prevEvList.add(e);
			}
		} catch (Exception ex) {
			System.out.println("Error is " + ex);
		}
		return prevEvList;
	}

	@Override
	public boolean isRegister(int studentid, int eventid) {
	    try {
	    	// 1. Check student already registered
	        stmt = conn.prepareStatement("select * from registration where studentid=? AND eventid=?");
	        stmt.setInt(1, studentid);
	        stmt.setInt(2, eventid);
	        rs = stmt.executeQuery();

	        if (rs.next()) {
	            return false; // already registered
	        }
	        
	        //  Check event capacity
	        stmt = conn.prepareStatement("select capacity from campuseventdb where eventid=?");
	        stmt.setInt(1, eventid);
	        rs = stmt.executeQuery();
	        int capacity = 0;
	        if (rs.next()) {
	            capacity = rs.getInt("capacity");//100
	        }

	        // Count current registrations for this event
	        stmt = conn.prepareStatement("select count(*) as total from registration where eventid=?");//2
	        stmt.setInt(1, eventid);
	        rs = stmt.executeQuery();
	        int registered = 0;
	        if (rs.next()) {
	            registered = rs.getInt("total");
	        }

	        if (registered >= capacity) {
	            System.out.println("Registration full for this event....");
	            return false;
	        }

	        
	        stmt = conn.prepareStatement("insert into registration(studentid, eventid, regdate) values(?,?,NOW())");
	        stmt.setInt(1, studentid);
	        stmt.setInt(2, eventid);
	        int value=stmt.executeUpdate();
	        return value>0?true:false;
	    }
	    catch(Exception ex) {
	        System.out.println();
	    }
	    return false;
	}

	@Override
	public List<RegistrationModel> getRegisteredEvent(int studentid) {
	    List<RegistrationModel> regList = new ArrayList<>();
	    try {
	        stmt = conn.prepareStatement(
	            "SELECT r.regid, e.eventid, e.name, e.eventdate, e.venue, r.regdate " +
	            "FROM registration r " +
	            "INNER JOIN campuseventdb e ON r.eventid = e.eventid " +
	            "WHERE r.studentid = ?"
	        );
	        stmt.setInt(1, studentid);
	        rs = stmt.executeQuery();

	        System.out.println("\n========== Your Registrations ==========");
	        System.out.printf("%-5s %-8s %-25s %-15s %-20s %-15s\n",
	                "RID", "EID", "Event Name", "Date", "Venue", "Reg.Date");
	        System.out.println("--------------------------------------------------------------------------");

	        while (rs.next()) {
	            System.out.printf("%-5d %-8d %-25s %-15s %-20s %-15s\n",
	                    rs.getInt("regid"),
	                    rs.getInt("eventid"),
	                    rs.getString("name"),
	                    rs.getString("eventdate"),
	                    rs.getString("venue"),
	                    rs.getString("regdate")
	            );
	        }

	        System.out.println("--------------------------------------------------------------------------");

	    } catch (Exception ex) {
	        System.out.println("Error is: " + ex);
	    }
	    return regList; 
	}

	@Override
	public boolean isCancelReg(int eventid) {
		try {
			
			
			

			
			
			
			// registration details
		stmt = conn.prepareStatement(
	            "SELECT r.regid, e.eventid, e.name, e.eventdate, e.venue, r.regdate " +
	            "FROM registration r " +
	            "INNER JOIN campuseventdb e ON r.eventid = e.eventid " +
	            "WHERE r.studentid = ?"
	        );
	        stmt.setInt(1, StudentHelper.loggedStudentId);
	        rs = stmt.executeQuery();

	        System.out.println("\n========== Your Registrations ==========");
	        System.out.printf("%-5s %-8s %-25s %-15s %-20s %-15s\n",
	                "RID", "EID", "Event Name", "Date", "Venue", "Reg.Date");
	        System.out.println("--------------------------------------------------------------------------");

	        while (rs.next()) {
	            System.out.printf("%-5d %-8d %-25s %-15s %-20s %-15s\n",
	                    rs.getInt("regid"),
	                    rs.getInt("eventid"),
	                    rs.getString("name"),
	                    rs.getString("eventdate"),
	                    rs.getString("venue"),
	                    rs.getString("regdate")
	            );
	        }
	        System.out.println("--------------------------------------------------------------------------");
	        
	        //Check date
	        stmt = conn.prepareStatement("select eventdate from campuseventdb WHERE eventid=?");
	        stmt.setInt(1, eventid);
	        rs = stmt.executeQuery();

	        if (rs.next()) {
	            java.sql.Date evDate = rs.getDate("eventdate"); 
	            java.time.LocalDate eventDate = evDate.toLocalDate(); // convert to LocalDate

	            java.time.LocalDate today = java.time.LocalDate.now();

	            // Check if event is past or today
	            if (!eventDate.isAfter(today)) { 
	                System.out.println("You cannot cancel registration for today's or past event.");
	                return false;
	            }
	        } else {
	            System.out.println("Event not found.");
	            return false;
	        }
	        
	        
	        // delete registration
	        stmt = conn.prepareStatement("Delete from registration where eventid=?");
	        stmt.setInt(1, eventid);
	        int value = stmt.executeUpdate();
	        return value>0?true:false;

	    } catch (Exception ex) {
	        System.out.println("Error is: " + ex);
	    }
	    return false; 
	}

	@Override
	public List<Map<String, String>> getUserRegistrationPDFData(int studentId) {
		List<Map<String, String>> list = new ArrayList<>();
		
		try {
	        stmt = conn.prepareStatement(
	            "SELECT e.name AS eventName, e.venue, e.eventdate, r.regdate " +
	            "FROM registration r " +
	            "INNER JOIN campuseventdb e ON r.eventid = e.eventid " +
	            "WHERE r.studentid = ?"
	        );
	        stmt.setInt(1, studentId);
	        rs = stmt.executeQuery();

	        while (rs.next()) {
	            Map<String, String> map = new HashMap<>();
	            map.put("eventName", rs.getString("eventName"));
	            map.put("venue", rs.getString("venue"));
	            map.put("eventDate", rs.getString("eventdate"));
	            map.put("regDate", rs.getString("regdate"));
	            
	            // Attended = past event, Cancelled = we don't track separately, default "No"
	            java.time.LocalDate today = java.time.LocalDate.now();
	            java.time.LocalDate evDate = rs.getDate("eventdate").toLocalDate();
	            map.put("attended", evDate.isBefore(today) ? "Yes" : "No");
	            map.put("cancelled", "No");

	            list.add(map);
	        }

	    } catch (Exception ex) {
	        System.out.println("Error fetching registration data: " + ex);
	    }
	    return list;
	}

}
