package server.services;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import server.data.domain.*;
//TODO: Implement Singleton Pattern
public class CrAppService {
	//TODO: remove when DAO Pattern is implemented
	private List<Session> sessions = new ArrayList<>();
	private List<Challenge> challenges= new ArrayList<>();
	public CrAppService() {
		//TODO: remove when DAO Pattern is implemented
		this.initilizeData();
	}
	//TODO: remove when DAO Pattern is implemented
	private void initilizeData() {
		//Create Users
		User user0 = new User();
		user0.setEmail("thomas.e2001@gmail.com");
		user0.setNickname("Thomas");
		user0.setPassword("$!9PhNz,");
		
		User user1 = new User();
		user1.setEmail("sample@gmail.com");
		user1.setNickname("buyer33");		
		user1.setPassword("hqc`}3Hb");
		//Create Sessions
		//Session1
		Session s1=new Session();
		s1.setDistance(2222);
		s1.setTitle("Sesion 1");
		s1.setSport("Running");
		s1.setDuration(120);
		s1.setOwner(user1);
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, 60);
		s1.setStart(calendar.getTime());
		Calendar calendar1 = Calendar.getInstance();
		calendar1.add(Calendar.DAY_OF_MONTH, 60);
		s1.setEnd(calendar1.getTime());
		//Session2
		Session s2=new Session();
		s2.setDistance(100);
		s2.setTitle("Sesion 2");
		s2.setSport("Walking");
		s2.setDuration(60);
		s2.setOwner(user1);
		Calendar calendar3 = Calendar.getInstance();
		calendar3.add(Calendar.DAY_OF_MONTH, 60);
		s2.setStart(calendar3.getTime());
		Calendar calendar4 = Calendar.getInstance();
		calendar4.add(Calendar.DAY_OF_MONTH, 60);
		s2.setEnd(calendar4.getTime());
		//Create Challenges
		//Challenge 1
		Challenge c1= new Challenge();
		c1.setDistance(200);
		c1.setName("llegar a la meta");
		Calendar calendar5 = Calendar.getInstance();
		calendar5.add(Calendar.DAY_OF_MONTH, 60);
		c1.setStart(calendar3.getTime());
		c1.setEnd(calendar5.getTime());
		c1.setTime(calendar3.getTimeInMillis()-calendar5.getTimeInMillis());
		c1.setOwner(user1);
		//Challenge 2
		Challenge c2= new Challenge();
		c2.setDistance(180);
		c2.setName("Aguantar lo maximo");
		Calendar calendar6 = Calendar.getInstance();
		calendar5.add(Calendar.DAY_OF_MONTH, 60);
		c1.setStart(calendar1.getTime());
		c1.setEnd(calendar6.getTime());
		c1.setTime(calendar1.getTimeInMillis()-calendar6.getTimeInMillis());
		c1.setOwner(user1);
		//add  sessions a el cache local
		this.sessions.add(s1);
		this.sessions.add(s2);
		//add challenges a el cache local
		this.challenges.add(c1);
		this.challenges.add(c2);
	}
	public List<Session> getSessions(){
		//TODO: Get all the sessions using DAO Pattern
		return this.sessions;
	}
	public List<Challenge> getChallenges(){
		//TODO: Get all the challenges using DAO Pattern
		return this.challenges;
	}
	public boolean makeCr(User user, String title) {
		//TODO: Find the  Session using DAO Pattern
		Session session = null;
		
		for (Session s : this.sessions) {
			if (s.getTitle().equals(title)) {
				session= s;
				break;
			}
		}
		//TODO: Find the  Challenge using DAO Pattern
				Challenge challenge = null;
				
				for (Challenge c : this.challenges) {
					if (c.getName().equals(title)) {
						challenge= c;
						break;
					}
				}

		if (session != null && challenge!=null ) {
			Cr newCr = new Cr();		
			newCr.setDate(Calendar.getInstance().getTime());
			newCr.setChallenge(challenge);
			newCr.setSession(session);
			newCr.setUser(user);		
			user.addCr(newCr);
			return true;
		
	

			//TODO: Save the new Cr in the DB using DAO Pattern
			
			
		} else {
			return false;
		}
	}
}
