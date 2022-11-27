package server.services;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import server.data.domain.*;
import server.data.dto.ChallengeAssembler;
import server.data.dto.ChallengeDTO;
import server.data.dto.SessionDTO;
import server.data.dto.UserDTO;
//TODO: Implement Singleton Pattern
public class CrAppService {
	//TODO: remove when DAO Pattern is implemented
	private List<Session> sessions = new ArrayList<>();
	private List<Challenge> challenges= new ArrayList<>();
	public CrAppService()  {
		//TODO: remove when DAO Pattern is implemented
		this.initilizeData();
	}
	//TODO: remove when DAO Pattern is implemented
	private void initilizeData()  {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		//Create Users
		User user0 = new User();
		user0.setEmail("thomas.e2001@gmail.com");
		user0.setNickname("Thomas");
		user0.setPassword("$!9PhNz,");
		
		User user1 = new User();
		user1.setEmail("antonio@gmail.com");
		user1.setNickname("antonio33");		
		user1.setPassword("hqc`}3Hb");
	
		
	
		//Create Challenges
		//Challenge 1
		Challenge ch1= new Challenge();
		ch1.setDistance(1000);
		try {
			ch1.setStart(format.parse("01/01/2022"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			ch1.setEnd(format.parse("31/12/2022"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ch1.setName("SLOWRUN");
		ch1.setSport("Athletism");
		ch1.setTime(20);
		ch1.setOwner(user1);
		//Challenge 2
		Challenge ch2= new Challenge();
		ch2.setDistance(2000);
		try {
			ch2.setStart(format.parse("01/04/2022"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			ch2.setEnd(format.parse("31/12/2022"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ch2.setName("DIVEFAST");
		ch2.setSport("Natation");
		ch2.setTime(60);
		ch2.setOwner(user0);
		challenges.add(ch1);
		challenges.add(ch2);
	}
	
	public ArrayList<ChallengeDTO> getChallenges(String sport){
		//TODO: Get all the challenges using DAO Patte
		ArrayList<ChallengeDTO> challenges2 = new ArrayList<>();
		for(Challenge ch :this.challenges)
		if(ch.getSport().equals(sport)) {
			ChallengeAssembler.getInstance();
			ChallengeDTO chdto= ChallengeAssembler.challengeToDTO(ch);
			challenges2.add(chdto);
		}
		return challenges2;
	}
	//ChallAcomplished
	public float challAcomplished(UserDTO user,ChallengeDTO challenge) {
		float res=0;
		for(SessionDTO session: user.getSessions()) {
			float sdist=0;
			float t = session.getDuration();
			int time=(int)t;
			Date d= addHoursToJavaUtilDate(session.getStart(),time);
			if(session.getSport().matches(challenge.getSport()) && session.getStart().after(challenge.getStart()) && d.before(challenge.getEnd())){
				sdist= sdist+ session.getDistance();
				int chdist=challenge.getDistance();
				res=((sdist*100)/chdist);
				System.out.println("Chall accomplished by: "+res);
				return res;
				
			}
		}
		System.out.println("Error");
		return res;
	}
	//Check this
	private Date addHoursToJavaUtilDate(Date d, int h) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(d);
		calendar.add(Calendar.HOUR_OF_DAY, h);
		return calendar.getTime();
	}
	//Create Challenges
	public void createChallenge(UserDTO user,ChallengeDTO challenge){
		User u= new User();
		Challenge ch= new Challenge();
		u.setNickname(user.getNickname());
		ch.setDistance(challenge.getDistance());
		ch.setEnd(challenge.getEnd());
		ch.setName(ch.getName());
		ch.setStart(challenge.getStart());
		ch.setTime(ch.getTime());
		ch.setOwner(u);
		this.challenges.add(ch);
	}
	//Del challenge
	public void DelChallenge(String title){
		List<Challenge> ch= new ArrayList<>();
		for(Challenge c: this.challenges) {
			if(!c.getName().matches(title)) {
				ch.add(c);
			}
		}
		this.challenges=ch;
	}
	
	
}
