package server.remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import server.data.domain.User;
import server.data.domain.Challenge;
import server.data.domain.Session;
import server.data.domain.User;
import server.data.dto.ChallengeAssembler;
import server.data.dto.SessionAssembler;
import server.data.dto.ChallengeDTO;
import server.data.dto.SessionDTO;
import server.services.LoginAppService;
import server.services.CrAppService;
public class RemoteFacade extends UnicastRemoteObject implements IRemoteFacade  {
	private static final long serialVersionUID = 1L;
	//Data structure for manage Server State
	private Map<Long, User> serverState = new HashMap<>();
	//TODO: Remove this instances when Singleton Pattern is implemented
	private LoginAppService loginService = new LoginAppService();
	private CrAppService crService= new CrAppService();
	public RemoteFacade() throws RemoteException {
		super();		
	}
	public synchronized long login(String email, String password) throws RemoteException {
		System.out.println(" * RemoteFacade login(): " + email + " / " + password);
				
		//Perform login() using LoginAppService
		User user = loginService.login(email, password);
			
		//If login() success user is stored in the Server State
		if (user != null) {
			//If user is not logged in 
			if (!this.serverState.values().contains(user)) {
				Long token = Calendar.getInstance().getTimeInMillis();		
				this.serverState.put(token, user);		
				return(token);
			} else {
				throw new RemoteException("User is already logged in!");
			}
		} else {
			throw new RemoteException("Login fails!");
		}
		
	}
	public synchronized void logout(long token) throws RemoteException {
		System.out.println(" * RemoteFacade logout(): " + token);
		
		if (this.serverState.containsKey(token)) {
			//Logout means remove the User from Server State
			this.serverState.remove(token);
		} else {
			throw new RemoteException("User is not logged in!");
		}
		//¿HAcer mas Tarde con CrApp?
	}
	public List<SessionDTO> getSessions() throws RemoteException{
		System.out.println(" * RemoteFacade getSessions()");
		//Get Sessions using CrAppService
		List<Session> sessions= crService.getSessions();
		if(sessions!=null) {
			return SessionAssembler.getInstance().sessionToDTO(sessions);
			
		}else {
			throw new RemoteException("getSessions() fails");
		}
	}
	public List<ChallengeDTO> getChallenges() throws RemoteException{
		System.out.println(" * RemoteFacade getChallenges()");
		//Get Sessions using CrAppService
		List<Challenge> challenges= crService.getChallenges();
		if(challenges!=null) {
			return ChallengeAssembler.getInstance().challengeToDTO(challenges);
			
		}else {
			throw new RemoteException("getChallenges() fails");
		}
	
	}
	public Challenge createChallenge(String name,Date start, Date end, int distance, long time, User Owner) {
		Challenge ch = new Challenge();
		ch.setName(name);
		ch.setOwner(Owner);
		ch.setDistance(distance);
		ch.setTime(time);
		ch.setEnd(end);
		
		return ch;
		
		
	}
	public Session createSession(String title, String sport,int distance,Date start,Date end,User Owner,long duration) {
		Session s= new Session();
		s.setDistance(distance);
		s.setDuration(duration);
		s.setEnd(end);
		s.setStart(start);
		s.setSport(sport);
		s.setOwner(Owner);
		s.setTitle(title);
		return s;
	}
	public User RegisterUser(String nickname,String password) {
		User u=new User();
		u.setChallenges(null);
		u.setNickname(nickname);
		u.setEmail(nickname);
		u.setPassword(password);
		u.setSessions(null);
		return u;
	}
	
	
	public boolean makeCr(long token,String sessionorchallengetitle) throws RemoteException {
		System.out.println(" * RemoteFacade makeCr session or challenge :" + sessionorchallengetitle);
		if(this.serverState.containsKey(token)) {
			//Make the Cr using Cr Application Service
			if(crService.makeCr(this.serverState.get(token), sessionorchallengetitle )) {
				return true;
			}else {
				throw new RemoteException("makeCr() fails!");
			}
		}else {
			throw new RemoteException("To place a session you must first log in");
		}
	}
	
	
}
