package server.services;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import server.data.dao.ChallengeDAO;
import server.data.dao.SessionDAO;
import server.data.dao.UserDAO;
import server.data.domain.*;
import server.data.dto.ChallengeAssembler;
import server.data.dto.ChallengeDTO;
import server.data.dto.SessionAssembler;
import server.data.dto.SessionDTO;
import server.data.dto.UserAssembler;
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
		ch1.setStart("01/01/2022");
		ch1.setEnd("31/12/2022");
		ch1.setName("SLOWRUN");
		ch1.setSport("Athletism");
		ch1.setTime(20);
		ch1.setOwner(user1);
		//Challenge 2
		Challenge ch2= new Challenge();
		ch2.setDistance(2000);
		ch2.setStart("01/04/2022");
		ch2.setEnd("31/12/2022");
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
		
		
//		for(SessionDTO session: user.getSessions()) {
//			float sdist=0;
//			float t = session.getDuration();
//			int time=(int)t;
//			Date d= addHoursToJavaUtilDate(session.getStart(),time);
//			if(session.getSport().matches(challenge.getSport()) && session.getStart().after(challenge.getStart()) && d.before(challenge.getEnd())){
//				sdist= sdist+ session.getDistance();
//				int chdist=challenge.getDistance();
//				res=((sdist*100)/chdist);
//				System.out.println("Chall accomplished by: "+res);
//				return res;
//				
//			}
//		}
//		System.out.println("Error");
		return res;
	}
	//Check this
	private Date addHoursToJavaUtilDate(Date string, int h) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(string);
		calendar.add(Calendar.HOUR_OF_DAY, h);
		return calendar.getTime();
	}
	//Create Challenges
	public void createChallenge(UserDTO user,String name, String start, String end ,int distance ,float time,String Sport ){
		User u= new User();
		Challenge ch= new Challenge();
		u.setNickname(user.getNickname());
		ch.setDistance(distance);
		ch.setEnd(end);
		ch.setName(name);
		ch.setStart(start);
		ch.setTime(time);
		ch.setOwner(u);
		ChallengeDAO.getInstance().save(ch);
	}
	//Create Session
	
	public List <SessionDTO> createSession(UserDTO userDTO, String title , String sport, int distance, String start, long duration){
		User user = new User();
		for(User u: UserDAO.getInstance().getAll()) {
			if(u.getNickname().matches(userDTO.getNickname())) {
				user=u;
			}
		}
		Session s =new Session();
		s.setTitle(title);
		s.setSport(sport);
		s.setDistance(distance);
		s.setStart(start);
		s.setDuration(duration);
		user.getSessions().add(s);
		SessionDAO.getInstance().save(s);
		UserDAO.getInstance().save(user);
		
		SessionAssembler sA=  new SessionAssembler();
		ArrayList<SessionDTO> sessions = new ArrayList<>();
		for(Session session: user.getSessions()) {
			sessions.add(sA.sessionToDTO(session));
		}
		return sessions;
	}
	public void DBupdate(List<User> users,List<Session>sessions,List<Challenge> challenges) {
		for(Challenge ch: ChallengeDAO.getInstance().getAll()) {
			ChallengeDAO.getInstance().delete(ch);
		}
		for (User u: UserDAO.getInstance().getAll()) {
			UserDAO.getInstance().delete(u);
		}
		for(Session s: SessionDAO.getInstance().getAll()) {
			SessionDAO.getInstance().delete(s);
		}
		
		for(User user: users) {
			UserDAO.getInstance().save(user);
		}
		for(Session s: sessions) {
			SessionDAO.getInstance().save(s);
		}
		for(Challenge ch:challenges) {
			ChallengeDAO.getInstance().save(ch);
		}
	}
	
	//Accept chall
	public UserDTO acceptChallenge(User userDTO, ChallengeDTO challengeAccepted, CrAppService cr) {
		User user=UserDAO.getInstance().find(userDTO.getNickname());
		for(User u : UserDAO.getInstance().getAll()) {
			if(userDTO.getNickname().matches(u.getNickname())) {
				user=u;
			}
		}
		System.out.println(user.getNickname());
		Challenge challenge = new Challenge();
		for(Challenge ch: ChallengeDAO.getInstance().getAll()) {
			if(ch.getName().matches(challengeAccepted.getName())) {
				challenge=ch;
			}
		}
		if(user.getChallenges()!=null) {
			user.getChallenges().add(challenge);
		}else {
			List<Challenge> challenges = new ArrayList<>();
			user.setChallenges(challenges);
		}
		
		//ChallengeDAO.getInstance().save(challenge);
		UserDAO.getInstance().save(user);
		UserAssembler uA= new UserAssembler();
		//Final u
		UserDTO Fuser;
		Fuser= uA.userToDTO(user,cr);
		return Fuser;
		
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
