package server.data.dto;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import server.data.domain.Challenge;
import server.data.domain.Session;
public class UserDTO implements Serializable{
	//This attribute is needed to implement the "Serializable" interface.
		private static final long serialVersionUID = 1L;	
		private String nickname;
		private String email;
		private String password;
		private LoginUserTypeDTO usertype;
		private List<ChallengeDTO> challenges= new ArrayList<>();
		private List<SessionDTO> sessions= new ArrayList<>();
		
		public String toString() {
			StringBuffer result = new StringBuffer();
			result.append(this.nickname);
			result.append(" # '");
			result.append(this.email);
			result.append("' # Email: ");
			result.append(this.email);
			return result.toString();
		}
		
		public List<ChallengeDTO> getChallenges() {
			return challenges;
		}
		public void setChallenges(List<ChallengeDTO> challenges) {
			this.challenges = challenges;
		}
		public void addChallenge(ChallengeDTO challenge) {
			if (challenge != null && !this.challenges.contains(challenge)) {
				this.challenges.add(challenge);
			}
		}
		
		public String getNickname() {
			return nickname;
		}
		
		public void setNickname(String nickname) {
			this.nickname = nickname;
		}
		
		public String getEmail() {
			return email;
		}
		
		public void setEmail(String email) {
			this.email = email;
		}
		public void setSessions(List<SessionDTO> sessions) {
			this.sessions = sessions;
		}
		public  List<SessionDTO> getSessions() {
			return sessions;
		}
		public LoginUserTypeDTO getUsertype() {
			return usertype;
		}
		public void setUsertype(LoginUserTypeDTO usertype) {
			this.usertype = usertype;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
}
