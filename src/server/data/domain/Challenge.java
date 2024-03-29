package server.data.domain;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.swing.text.DateFormatter;

import java.util.Date;
import javax.jdo.annotations.Discriminator;
import javax.jdo.annotations.DiscriminatorStrategy;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(detachable="true")
@Discriminator(strategy = DiscriminatorStrategy.CLASS_NAME)
public class Challenge {
	private String name;
	private Date start;
	private Date end;
	private int distance;
	private float time;
	@Persistent(defaultFetchGroup="true")
	private User owner;
	private String sport;
	
	
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
		result.append("Start:");
		result.append(dateFormatter.format(this.start));
		result.append(" End:");
		result.append(dateFormatter.format(this.end));
		result.append(" Distance:");
		result.append(numberFormatter.format(this.distance));
		result.append(" TIME:");
		result.append(numberFormatter.format(this.time));
		result.append(this.owner.getNickname());
		
		return result.toString();
	}

	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	public String getSport() {
		return sport;
	}
	public void setSport(String sport) {
		this.sport = sport;
	}
	
}
