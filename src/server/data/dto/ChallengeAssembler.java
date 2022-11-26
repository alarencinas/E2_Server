package server.data.dto;
import java.util.ArrayList;
import java.util.List;

import server.data.domain.Challenge;
import server.data.domain.Session;
public class ChallengeAssembler {
	private static ChallengeAssembler instance;
	public ChallengeAssembler() { }
	public static ChallengeAssembler getInstance() {
		if(instance== null) {
			instance=new ChallengeAssembler();
		}
		return instance;
	}
	
	public static ChallengeDTO challengeToDTO(Challenge challenge){
		ChallengeDTO chdto= new ChallengeDTO();
		chdto.setDistance(challenge.getDistance());
		chdto.setEnd(challenge.getEnd());
		chdto.setName(challenge.getName());
		chdto.setSport(challenge.getSport());
		chdto.setStart(challenge.getStart());
		chdto.setTime(challenge.getTime());
		UserDTO userdto = new UserDTO();
		userdto.setNickname(challenge.getOwner().getNickname());
		userdto.setEmail(challenge.getOwner().getEmail());
		chdto.setOwner(userdto);
		return chdto;
	}
}
