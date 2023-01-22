package server.test;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import server.data.domain.Challenge;
import server.data.domain.Session;
import server.data.domain.User;
import server.data.dto.ChallengeDTO;
import server.data.dto.LoginUserTypeDTO;
import server.data.dto.SessionDTO;
import server.remote.RemoteFacade;
public class LocalTest {
	public static void main(String[] args) {
	SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");	
	RemoteFacade Rfacade =null;
	List<ChallengeDTO> challenges=null;
	List<SessionDTO> sessions=null;
	SessionDTO session= null;
	ChallengeDTO challenge= null;
	long token =0l;
	try {
		Rfacade= new RemoteFacade();
		User user= new User();
		user.setEmail("thomas.e2001@gmail.com");
		user.setNickname("Thomas");	
		String sha1 = org.apache.commons.codec.digest.DigestUtils.sha1Hex("$!9PhNz,");		
		user.setPassword(sha1);
		
		Session s1=new Session();
		s1.setTitle("Running");
		s1.setDuration(30);
		s1.setDistance(100);
		String sDate1="05/09/2022";  
	    s1.setStart( new SimpleDateFormat("dd/MM/yyyy").parse(sDate1)); 
		s1.setSport("Athletism");
		ArrayList<Session> sessions2= new ArrayList<>();
		sessions2.add(s1);
		user.setSessions(sessions2);
		for(SessionDTO s: sessions) {
			System.out.println(s);
		}
		challenges=Rfacade.getChallenges();
		challenge=challenges.get(0);
		for(ChallengeDTO chdto: challenges) {
			System.out.println(chdto);
		}
	
		
		
	} catch (Exception e) {
		System.out.println("ERROR:"+ e.getMessage() +e.getCause());
	} try {
		//Login
		String sha1 = org.apache.commons.codec.digest.DigestUtils.sha1Hex("$!9PhNz,");
		token = Rfacade.login("thomas.e2001@gmail.com", sha1, "Thomas", LoginUserTypeDTO.Email);	
		//Logout
		Rfacade.logout(token);
		challenges = Rfacade.getChallenges();
		challenge = challenges.get(0); 			
		System.out.println("\t- " + challenge);
	}catch (Exception e) {
		System.out.println("\t# Error: " + e.getMessage());	
	}
		
	//Force exit to stop RMI Server
			System.exit(0);
	}
}