package server.data.domain;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
public class Cr implements Comparable<Cr> {
	private long date;
	private Challenge challenge;
	private Session session;
	private User user;
	public void setDate(Date date) {		
		this.date = date.getTime();
	}
	public Date getDate() {
		return new Date(this.date);
	}
	public Challenge getChallenge() {
		return challenge;
	}
	public void setChallenge(Challenge challenge) {
		this.challenge = challenge;
	}
	public Session getSession() {
		return session;
	}
	public void setSession(Session session) {
		this.session = session;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int compareTo(Cr cr) {
		if (cr != null) {
			return Long.compare(this.date, cr.date);
		} else {		
			return 0;
		}
	}
	public String toString() {
		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MMM-YY - hh:mm");
		NumberFormat numberFormatter = NumberFormat.getCurrencyInstance(Locale.getDefault()); 

		StringBuffer result = new StringBuffer("User:");
		
		result.append(this.getUser().getNickname());
		result.append(" - Session:");
		result.append(this.getSession().getTitle());
		result.append(" - Date:");
		result.append(dateFormatter.format(this.date));
		result.append(" - Challenge");
		result.append(this.getChallenge().getName());
		
		return result.toString();
	}
	public boolean equals(Object obj) {
		if(this.getClass().getName().equals(obj.getClass().getName())) {
			Cr cr=(Cr)obj;
			return this.date==cr.date &&
					this.session.equals(cr.session) &&
					this.challenge.equals(cr.challenge) &&
					this.user.equals(cr.user);
		}
		return false;
	}
}
