package server.data.dto;
import java.util.ArrayList;
import java.util.List;

import server.data.domain.Challenge;
import server.data.domain.Session;
public class ChallengeAssembler {
	private static ChallengeAssembler instance;
	private ChallengeAssembler() { }
	public static ChallengeAssembler getInstance() {
		if(instance== null) {
			instance=new ChallengeAssembler();
		}
		return instance;
	}
	public ChallengeDTO challengeToDTO(Challenge challenge) {
		ChallengeDTO dto= new ChallengeDTO();
		dto.setName(challenge.getName());
		dto.setDistance(challenge.getDistance());
		dto.setStart(challenge.getStart());
		dto.setEnd(challenge.getEnd());
		dto.setTime(challenge.getTime());
		return dto;
		
		
	}
	public List<ChallengeDTO> challengeToDTO(List<Challenge> challenges){
		List<ChallengeDTO> dtos= new ArrayList<>();
		for(Challenge challenge : challenges) {
			dtos.add(this.challengeToDTO(challenge));
		}
		return dtos;
	}
}
