package server.test;
import java.util.List;
import server.data.dto.ChallengeDTO;
import server.data.dto.SessionDTO;
import server.remote.RemoteFacade;
public class LocalTest {
	public static void main(String[] args) {	
		RemoteFacade facade=null;
		List<SessionDTO> sessions= null;
		SessionDTO session= null;
		List<ChallengeDTO> challenges=null;
		ChallengeDTO challenge=null;
		long token =0l;
		long token1=1l;
		try {
			facade= new RemoteFacade();
			//Get Sessions
			sessions= facade.getSessions();
			session= sessions.get(0);
			for(SessionDTO s: sessions) {
				System.out.println("\t- "+ s);
			}
			//Get Challenges
			challenges= facade.getChallenges();
			challenge=challenges.get(0);
			for(ChallengeDTO c: challenges) {
				System.out.println("\t- "+ c);
			}
			//make a Cr
			facade.makeCr(0, session.getTitle());
			facade.makeCr(1, challenge.getName());
		}catch(Exception e){
			System.out.println("Error: "+ e.getMessage() );
		}
		try {
			//Login
			String sha1 = org.apache.commons.codec.digest.DigestUtils.sha1Hex("$!9PhNz,");
			token = facade.login("Javier.e2002@gmail.com", sha1);			
			//Make a Cr
			facade.makeCr(token, session.getTitle());
			//Logout
			facade.logout(token);
			//Get Sessions to check if cr is registered
//			sessions=facade.getSessions();
//			sessions=(List<SessionDTO>) sessions.get(0);
//			System.out.println("\t-"+ session);
			
		}catch(Exception e) {
			System.out.println("Error: "+ e.getMessage());
		}
		System.exit(0);
	}
}

