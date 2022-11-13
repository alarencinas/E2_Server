package server.data.dto;
import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import server.data.domain.User;
//This class implements DTO pattern
public class ChallengeDTO implements Serializable{
	//This attribute is needed to implement the "Serializable" interface.
	private static final long serialVersionUID = 1L;
	private String name;
	private Date start;
	private Date end;
	private int distance;
	private float time;
	private User owner;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public int getDistance() {
		return distance;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}
	public float getTime() {
		return time;
	}
	public void setTime(float time) {
		this.time = time;
	}
	@Override
	public String toString() {
		StringBuffer result = new StringBuffer();
		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MMM-YY - hh:mm");
		NumberFormat numberFormatter = NumberFormat.getCurrencyInstance(Locale.getDefault()); 

		result.append(this.name);
		result.append("Fecha de Inicio:");
		result.append(dateFormatter.format(this.start));
		result.append(" Fecha de Fin:");
		result.append(dateFormatter.format(this.end));
		result.append(" Distancia:");
		result.append(numberFormatter.format(this.distance));
		result.append(" TIME:");
		result.append(numberFormatter.format(this.time));
		
		return result.toString();
	}
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
}
