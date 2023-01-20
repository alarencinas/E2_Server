package server.remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.ParseException;
import java.util.ArrayList;
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
import server.data.dto.LoginUserTypeDTO;
import server.data.dto.SessionDTO;
import server.data.dto.UserDTO;
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
	public synchronized long login(String email, String password,String nick,LoginUserTypeDTO usertype) throws RemoteException {
		System.out.println(" * RemoteFacade login(): " + email + " / " + password);
				
		//Perform login() using LoginAppService
		User user = loginService.login(email, password, nick, usertype);
			
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
	
	public List<ChallengeDTO> getChallenges(String sport) throws RemoteException{
		ArrayList<ChallengeDTO> ch= crService.getChallenges(sport);
	return ch;
	}
	@Override
	public float challAcomplished(UserDTO user, ChallengeDTO challenge) throws RemoteException {
		return crService.challAcomplished(user, challenge);
	}
	

		@Override
	public void DelChallenge(String title) throws RemoteException {
		crService.DelChallenge(title);
		
	}
	
	@Override
	public UserDTO getUser(String email, String password) throws RemoteException, ParseException {
		return loginService.getUser(email, password);
	}
	@Override
	public UserDTO acceptChallenge(User userDTO, ChallengeDTO challengeAccepted) throws RemoteException {
		return crService.acceptChallenge(userDTO, challengeAccepted, crService);
		
	}
	@Override
	public List<SessionDTO> createSession(UserDTO userDTO, String title, String sport, int distance, Date start,
			long duration) throws RemoteException {
		return crService.createSession(userDTO, title, sport, distance, start, duration);
		
	}
	@Override
	public void createChallenge(UserDTO userDTO, String name, Date start, Date end, int distance, float time,
			String Sport) throws RemoteException {
	 crService.createChallenge(userDTO, name, start, end, distance, time, Sport);
		
	}
	@Override
	public void createUser(LoginUserTypeDTO type, String nickname, String password, String email)
			throws RemoteException {
	
		loginService.createUser(type, nickname, password, email);
	}
	@Override
	public UserDTO updateUser(UserDTO user) throws RemoteException {
	
		return loginService.updateUser(user, crService);
	}
	
	
	
	
	
	
	
	//public void signUp(long token, Challenge challenge) {
	//	Owner.addChallenge(challenge);
		
	//}
	
	
}
