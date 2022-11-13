package server.data.domain;

import java.util.Date;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;


public class Session {
	private String title;
	private String sport;
	private int distance;
	private Date start;
	private Date end;
	private User owner;
	private long duration;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSport() {
		return sport;
	}
	public void setSport(String sport) {
		this.sport = sport;
	}
	public int getDistance() {
		return distance;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}
	public Date getStart() {
		return start;
	}
	public void setStart(Date start) {
		this.start = start;
	}
	public Date getEnd() {
		return end;
	}
	public void setEnd(Date end) {
		this.end = end;
	}
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	public long getDuration() {
		return duration;
	}
	public void setDuration(long duration) {
		this.duration = duration;
	}
	public String toString() {
		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MMM-YY - hh:mm");
		NumberFormat numberFormatter = NumberFormat.getCurrencyInstance(Locale.getDefault()); 

		StringBuffer result = new StringBuffer();
		
		
		result.append(this.title);
		result.append("' Sport: ");
		result.append(this.sport);
		result.append("/");
		result.append(numberFormatter.format(this.distance));
		result.append("km # Fecha de Inicio: ");
		result.append(dateFormatter.format(this.start));
		result.append("Fecha de Fin:");
		result.append(dateFormatter.format(this.end));
		result.append(" Duracion:");
		result.append(numberFormatter.format(this.duration));
		
		return result.toString();		
	}
	
	
}
