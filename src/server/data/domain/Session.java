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
		result.append("km # Start: ");
		result.append(dateFormatter.format(this.start));
		
		result.append(" Duration:");
		result.append(numberFormatter.format(this.duration));
		
		return result.toString();		
	}
	
	public boolean equals(Object obj) {
		if (this.getClass().getName().equals(obj.getClass().getName())) {
			return this.title == ((Session)obj).title;
		}
		
		return false;
	}
	
	
}
