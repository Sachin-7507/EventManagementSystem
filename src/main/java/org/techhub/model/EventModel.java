package org.techhub.model;



import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EventModel {
	private int eventid;
	private String name;
	private String eventdate;
	private String venue;
	private int capacity;
}
