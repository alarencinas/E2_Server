package server.data.dto;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import server.data.domain.Challenge;
import server.data.domain.Cr;
import server.data.domain.Session;
public class UserDTO implements Serializable{
	//This attribute is needed to implement the "Serializable" interface.
		private static final long serialVersionUID = 1L;	
		private String nickname;
		private String email;
		private String password;
		private List<Challenge> challenges= new ArrayList<>();
		private List<Session> sessions= new ArrayList<>();
		private List<Cr> crs= new ArrayList<>();
		public List<Cr> getCrs(){
			return crs;
		}
		public void setCrs(List<Cr> crs) {
			this.crs=crs;
		}
		public List<Challenge> getChallenges() {
			return challenges;
		}
		public void setChallenges(List<Challenge> challenges) {
			this.challenges = challenges;
		}
		public void addChallenge(Challenge challenge) {
			if (challenge != null && !this.challenges.contains(challenge)) {
				this.challenges.add(challenge);
			}
		}
		public boolean checkPassword(String password) {
			return this.password.equals(password);
		}
		public void setPassword(String password) {
			this.password = password;
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
		public void setSessions(List<Session> sessions) {
			this.sessions = sessions;
		}
}
