package server.services;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import factory.FacebookSocketClient;
import factory.GoogleServiceGateway;
import server.data.domain.Challenge;
import server.data.domain.LoginUserType;
import server.data.domain.Session;
import  server.data.domain.User;
import server.data.dto.ChallengeDTO;
import server.data.dto.LoginUserTypeDTO;
import server.data.dto.SessionAssembler;
import server.data.dto.SessionDTO;
import server.data.dto.UserAssembler;
import server.data.dto.UserDTO;
public class LoginAppService {
	private List<User> users= new ArrayList<>();
	private UserAssembler userassembler = new UserAssembler();
	private SessionAssembler sessionAssembler= new SessionAssembler(); 	//TODO CALLING THE CONSTRUCTORS DIRECTLY ,NOT THE LOGINFACTORY
	private GoogleServiceGateway Googleservice = new GoogleServiceGateway(); //TODO NOT NULLPOINTER BECAUSE WE DECLARE DE CONSTRUCTORS INSTEAD OF THE LOGINFACTORY
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
		s1.setStart(format.parse("05/09/2022"));
		s1.setSport("Athletism");
		//S2
		Session s2=new Session();
		s2.setTitle("Swiming");
		s2.setDuration(30);
		s2.setDistance(100);
		s2.setStart(format.parse("01/12/2022"));
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
		s3.setStart(format.parse("08/10/2022"));
		s3.setSport("Boxing");
		//S4
		Session s4= new Session();
		s4.setTitle("Jumping");
		s4.setDuration(120);
		s4.setDistance(10);
		s4.setStart(format.parse("07/07/2022"));
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
		s5.setStart(format.parse("03/06/2022"));
		s5.setSport("Athletism");
		Session s6= new Session();
		s6.setTitle("HighJump");
		s6.setDuration(50);
		s6.setDistance(120);
		s6.setStart(format.parse("03/06/2022"));
		s6.setSport("Natation");
		ArrayList<Session> sessions3= new ArrayList<>();
		sessions3.add(s5);
		sessions3.add(s6);
		userG.setSessions(sessions3);
		List<Challenge> challenges3= new ArrayList<>();
		userG.setChallenges(challenges3);
		users.add(userG);
		
		
		
		for(User u:users) {
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
					ChallengeDTO chdto= new ChallengeDTO();
					chdto.setOwner(userassembler.userToDTO(ch.getOwner()));
					chdto.setDistance(ch.getDistance());
					chdto.setName(ch.getName());
					chdto.setStart(ch.getStart());
					chdto.setEnd(ch.getEnd());
					chdto.setSport(ch.getSport());
					chdto.setTime(ch.getTime());
					challengesDTO.add(chdto);
					
					
				}
				userdto.setChallenges(challengesDTO);
				userdto.setUsertype(LoginUserTypeDTO.Email);
				return userdto;
				
				
			}
			}else if(user.getUsertype() == LoginUserType.Google) {
				if(Googleservice.checkUser(email, pass)) {
					List <SessionDTO> sessionsDTO= new ArrayList<>();
					userdto.setEmail(user.getEmail());
					userdto.setNickname(user.getNickname());
					for(Session s : user.getSessions()) {
						sessionsDTO.add(sessionAssembler.sessionToDTO(s));
						
					}
					userdto.setSessions(sessionsDTO);
					userdto.setUsertype(LoginUserTypeDTO.Google);
					List<ChallengeDTO> challengesDTO= new ArrayList<>();
					for(Challenge ch: u.getChallenges()) {
						ChallengeDTO chdto= new ChallengeDTO();
						chdto.setOwner(userassembler.userToDTO(ch.getOwner()));
						chdto.setDistance(ch.getDistance());
						chdto.setName(ch.getName());
						chdto.setStart(ch.getStart());
						chdto.setEnd(ch.getEnd());
						chdto.setSport(ch.getSport());
						chdto.setTime(ch.getTime());
						challengesDTO.add(chdto);
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
					for(Challenge ch: u.getChallenges()) {
						ChallengeDTO chdto= new ChallengeDTO();
						chdto.setOwner(userassembler.userToDTO(ch.getOwner()));
						chdto.setDistance(ch.getDistance());
						chdto.setName(ch.getName());
						chdto.setStart(ch.getStart());
						chdto.setEnd(ch.getEnd());
						chdto.setSport(ch.getSport());
						chdto.setTime(ch.getTime());
						challengesDTO.add(chdto);
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
	
}
