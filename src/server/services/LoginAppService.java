package server.services;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
	private SessionAssembler sessionAssembler= new SessionAssembler();
	
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
		users.add(user);
		for(User u:users) {
			if(u.checkPassword(pass)&& u.getEmail().matches(email)) {
				userdto.setNickname(u.getNickname());
				userdto.setEmail(u.getEmail());
				List<SessionDTO> sessionsDTO= new ArrayList<>();
				for(Session s: u.getSessions()) {
					sessionsDTO.add(sessionAssembler.sessionToDTO(s));
					
				}
				userdto.setSessions(sessionsDTO);
				userdto.setUsertype(LoginUserTypeDTO.Email);
				List<ChallengeDTO> challengesDTO= new ArrayList<>();
				for(Challenge ch: user.getChallenges()) {
					ChallengeDTO c= new ChallengeDTO() ;
					c.setDistance(ch.getDistance());
					c.setEnd(ch.getEnd());
					c.setName(ch.getName());
					c.setSport(ch.getName());
					c.setTime(ch.getTime());
					c.setStart(ch.getStart());
					c.setOwner(UserAssembler.userToDTO(ch.getOwner()));
					challengesDTO.add(c);
							
				}
				userdto.setChallenges(challengesDTO);
				return userdto;
				
				
			}
		}
		return userdto;
	}
	
	
	public User login(String nickname,String email, String password, LoginUserTypeDTO usertype) {
		//TODO: Get User using DAO and check 		
		if(usertype.equals(LoginUserTypeDTO.Google)||usertype.equals(LoginUserTypeDTO.Facebook)) {
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
