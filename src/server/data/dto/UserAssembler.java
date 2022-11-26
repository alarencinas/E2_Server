package server.data.dto;
import java.util.ArrayList;
import java.util.List;

import server.data.domain.Challenge;
import server.data.domain.LoginUserType;
import server.data.domain.Session;
import server.data.domain.User;
//This class is part of the DTO pattern. It also implements Singleton Pattern.
public class UserAssembler {
	private static UserAssembler instance;

	public UserAssembler() { }
	
	public static UserAssembler getInstance() {
		if (instance == null) {
			instance = new UserAssembler();
		}

		return instance;
	}

	public static UserDTO userToDTO(User user) {
		UserDTO dto = new UserDTO();
		
		dto.setEmail(user.getEmail());
		dto.setNickname(user.getNickname());
		List<SessionDTO> sessions = new ArrayList<>();
		for(Session session:user.getSessions()) {
			SessionDTO sessionDTO= new SessionDTO();
			sessionDTO.setTitle(session.getTitle());
			sessionDTO.setDistance(session.getDistance());
			sessionDTO.setStart(session.getStart());
			sessionDTO.setSport(session.getSport());
			sessions.add(sessionDTO);
		}
		dto.setSessions(sessions);
		List<ChallengeDTO> challenges= new ArrayList<>();
		for(Challenge ch: user.getChallenges()) {
			ChallengeDTO chDTO= new ChallengeDTO() ;
			chDTO.setDistance(ch.getDistance());
			chDTO.setEnd(ch.getEnd());
			chDTO.setName(ch.getName());
			chDTO.setOwner(dto);
			chDTO.setSport(ch.getSport());
			chDTO.setStart(ch.getStart());
			chDTO.setTime(ch.getTime());
			challenges.add(chDTO);
		}
		dto.setChallenges(challenges);
		if(user.getUsertype().equals(LoginUserType.Facebook)) {
			dto.setUsertype(LoginUserTypeDTO.Facebook);
		}else if(user.getUsertype().equals(LoginUserType.Google)) {
			dto.setUsertype(LoginUserTypeDTO.Google);
		}else if(user.getUsertype().equals(LoginUserType.Email)) {
			dto.setUsertype(LoginUserTypeDTO.Email);
		}
		return dto;
	}
}
