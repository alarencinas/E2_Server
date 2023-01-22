package server.services;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import server.data.dao.ChallengeDAO;
import server.data.dao.SessionDAO;
import server.data.dao.UserDAO;
import factory.FacebookSocketClient;
import factory.GoogleServiceGateway;
import server.data.domain.Challenge;
import server.data.domain.LoginUserType;
import server.data.domain.Session;
import  server.data.domain.User;
import server.data.dto.ChallengeAssembler;
import server.data.dto.ChallengeDTO;
import server.data.dto.LoginUserTypeDTO;
import server.data.dto.SessionAssembler;
import server.data.dto.SessionDTO;
import server.data.dto.UserAssembler;
import server.data.dto.UserDTO;
public class LoginAppService {
	private List<User> users= new ArrayList<>();
	private UserAssembler userassembler = new UserAssembler();
	private SessionAssembler sessionAssembler= new SessionAssembler(); 	
	private GoogleServiceGateway Googleservice = new GoogleServiceGateway(); 
	private FacebookSocketClient client= new FacebookSocketClient("0.0.0.0", 35600);
	
	public UserDTO getUser(String email, String pass) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		UserDTO userdto = new UserDTO();
		User user= new User();
		user.setNickname("Thomas");		
		user.setEmail("thomas.e2001@gmail.com");
		user.setPassword("thomas");
		//S1
		Session s1=new Session();
		s1.setTitle("Running");
		s1.setDuration(30);
		s1.setDistance(100);
	    String sDate1="05/09/2022";  
	    s1.setStart( new SimpleDateFormat("dd/MM/yyyy").parse(sDate1)); 
		s1.setSport("Athletism");
		//S2
		Session s2=new Session();
		s2.setTitle("Swiming");
		s2.setDuration(30);
		s2.setDistance(100);
		String sDate2="01/12/2022";  
	    s2.setStart( new SimpleDateFormat("dd/MM/yyyy").parse(sDate2)); 
		s2.setSport("Natation");
		//Sessions
		ArrayList<Session> sessions= new ArrayList<>();
		sessions.add(s1);
		sessions.add(s2);
		user.setSessions(sessions);
		user.setUsertype(LoginUserType.Email);
		List<Challenge> challenges= new ArrayList<>();
		user.setChallenges(challenges);
		users.add(user);
		//FaceBook User
		User userF= new User();
		userF.setEmail("felipe@gmail.com");
		userF.setNickname("Felipe");
		userF.setUsertype(LoginUserType.Facebook);
		List<Challenge> challenges1= new ArrayList<>();
		userF.setChallenges(challenges1);
		//Sessions
		Session s3= new Session();
		//S3
		s3.setTitle("Fighting");
		s3.setDuration(50);
		s3.setDistance(120);
		String sDate3="08/10/2022";  
	    s3.setStart( new SimpleDateFormat("dd/MM/yyyy").parse(sDate3)); 
		s3.setSport("Boxing");
		//S4
		Session s4= new Session();
		s4.setTitle("Jumping");
		s4.setDuration(120);
		s4.setDistance(10);
		String sDate4="07/07/2022";  
	    s4.setStart( new SimpleDateFormat("dd/MM/yyyy").parse(sDate4)); 
		s4.setSport("Athletism");
		ArrayList<Session> sessions2= new ArrayList<>();
		sessions2.add(s3);
		sessions2.add(s4);
		userF.setSessions(sessions2);
		users.add(userF);
		//Google User
		User userG=new User();
		userG.setEmail("German@gmail.com");
		userG.setNickname("German");
		userG.setUsertype(LoginUserType.Google);
		Session s5 = new Session();
		s5.setTitle("Sprint");
		s5.setDuration(50);
		s5.setDistance(120);
		String sDate5="03/06/2022";  
	    s5.setStart( new SimpleDateFormat("dd/MM/yyyy").parse(sDate5)); 
		s5.setSport("Athletism");
		Session s6= new Session();
		s6.setTitle("HighJump");
		s6.setDuration(50);
		s6.setDistance(120);
		String sDate6="03/06/2022";  
	    s6.setStart( new SimpleDateFormat("dd/MM/yyyy").parse(sDate6)); 
		s6.setSport("Natation");
		ArrayList<Session> sessions3= new ArrayList<>();
		sessions3.add(s5);
		sessions3.add(s6);
		userG.setSessions(sessions3);
		List<Challenge> challenges3= new ArrayList<>();
		userG.setChallenges(challenges3);
		users.add(userG);
		
		UserDTO userf= new UserDTO();
		
		for(User u:UserDAO.getInstance().getAll()) {
			if(u.getUsertype()== LoginUserType.Email) {
			if(u.getEmail().matches(email)&& u.checkPassword(pass)) {
				List <SessionDTO> sessionsDTO= new ArrayList<>();
				userdto.setNickname(u.getNickname());
				userdto.setEmail(u.getEmail());
				for(Session s : u.getSessions()) {
					sessionsDTO.add(sessionAssembler.sessionToDTO(s));
					
				}
				userdto.setSessions(sessionsDTO);
				userdto.setUsertype(LoginUserTypeDTO.Email);
				List<ChallengeDTO> challengesDTO= new ArrayList<>();
				for(Challenge ch: u.getChallenges() ) {
					challengesDTO.add(ChallengeAssembler.challengeToDTO(ch));
					
				}
				userdto.setChallenges(challengesDTO);
				userdto.setUsertype(LoginUserTypeDTO.Email);
				return userdto;
				
				
			}
			}else if(user.getUsertype() == LoginUserType.Google ) {
				if(Googleservice.checkUser(email, pass) && email.matches(user.getEmail())) {
					List <SessionDTO> sessionsDTO= new ArrayList<>();
					userdto.setEmail(user.getEmail());
					userdto.setNickname(user.getNickname());
					for(Session s : user.getSessions()) {
						sessionsDTO.add(sessionAssembler.sessionToDTO(s));
						
					}
					userdto.setSessions(sessionsDTO);
					userdto.setUsertype(LoginUserTypeDTO.Google);
					List<ChallengeDTO> challengesDTO= new ArrayList<>();
					for(Challenge ch: u.getChallenges() ) {
						challengesDTO.add(ChallengeAssembler.challengeToDTO(ch));
						
					}
					userdto.setChallenges(challengesDTO);
					return userdto;
					
				}
			}else if(user.getUsertype() == LoginUserType.Facebook) {
				if(client.checkUser(email, pass)) {
					List <SessionDTO> sessionsDTO= new ArrayList<>();
					userdto.setEmail(user.getEmail());
					userdto.setNickname(user.getNickname());
					for(Session s : user.getSessions()) {
						sessionsDTO.add(sessionAssembler.sessionToDTO(s));
						
					}
					userdto.setSessions(sessionsDTO);
					userdto.setUsertype(LoginUserTypeDTO.Facebook);
					List<ChallengeDTO> challengesDTO= new ArrayList<>();
					for(Challenge ch: u.getChallenges() ) {
						challengesDTO.add(ChallengeAssembler.challengeToDTO(ch));
						
					}
					userdto.setChallenges(challengesDTO);
					return userdto;
					
				}
			}
		}
		return userdto;
	}
	
	
	public User login(String nickname,String email, String password, LoginUserTypeDTO usertype) { 
		//TODO HERE HAS TO BE THE LOGINGATEWAY	
		if(usertype.equals(LoginUserTypeDTO.Google)||usertype.equals(LoginUserTypeDTO.Facebook)) {//TODO ESTE IF THEN ELSE ES LO DE LOGINFACTORY
			User user = new User();
			user.setNickname(nickname);
			user.setEmail(email);
			List<Session> sessions= new ArrayList<>();
			List <Challenge> challenges = new ArrayList<>();
			user.setSessions(sessions);
			user.setChallenges(challenges);
			return user;
		}else if(usertype.equals(LoginUserTypeDTO.Email)) {
			User user = new User();
			user.setNickname(nickname);
			user.setEmail(email);
			List<Session> sessions= new ArrayList<>();
			List <Challenge> challenges = new ArrayList<>();
			user.setSessions(sessions);
			user.setChallenges(challenges);
			return user;
		}
		return null;
		
		
		
	}
	public void createUser(LoginUserTypeDTO type, String nickname,String password,String email) {
		User user= new User();
		user.setNickname(nickname);
		user.setEmail(email);
		user.setPassword(password);
		user.setChallenges(new ArrayList<>());
		user.setSessions(new ArrayList<>());
		if(type.equals(LoginUserTypeDTO.Email)) {
			user.setUsertype(LoginUserType.Email);
			UserDAO.getInstance().save(user);
		}else if(type.equals(LoginUserTypeDTO.Google)) {
			
				user.setUsertype(LoginUserType.Google);
				UserDAO.getInstance().save(user);
				System.out.println("GOOGLE USER");
			
			
		}else if(type.equals(LoginUserTypeDTO.Facebook)) {
			
				user.setUsertype(LoginUserType.Facebook);
				UserDAO.getInstance().save(user);
				System.out.println("Facebook USER");
				
			
		}
	}
	
	public UserDTO updateUser(UserDTO user, CrAppService cr) {
		User user1 = new User();
		for(User u: UserDAO.getInstance().getAll()) {
			if(u.getNickname().matches(user.getNickname())) {
				user1=u;
			}
			
		}
	 UserDAO.getInstance().delete(UserDAO.getInstance().find(user.getNickname()));
	 user1.setSessions(new ArrayList<>());
	 for(SessionDTO sessionDTO: user.getSessions()) {
		 Session s = new Session();
		 s.setDistance(sessionDTO.getDistance());
		 s.setDuration(sessionDTO.getDuration());
		 s.setSport(sessionDTO.getSport());
		 s.setStart(sessionDTO.getStart());
		 s.setTitle(sessionDTO.getTitle());
		 user1.getSessions().add(s);
		 
		 
	 }
	 user1.setChallenges(new ArrayList<>());
	 for(ChallengeDTO chDTO: user.getChallenges()) {
		 Challenge ch= new Challenge();
		 User owner = new User();
		 owner.setNickname(chDTO.getOwner().getNickname());
		 ch.setOwner(owner);
		 ch.setDistance(chDTO.getDistance());
		 ch.setEnd(chDTO.getEnd());
		 ch.setName(chDTO.getName());
		 ch.setSport(chDTO.getSport());
		 ch.setStart(chDTO.getStart());
		 ch.setTime(0);
		 user1.getChallenges().add(ch);
		 
	 }
	 	UserDAO.getInstance().save(user1);
	 	UserAssembler uA= new UserAssembler();
	 	return uA.userToDTO(user1, cr);
	}
	
}
