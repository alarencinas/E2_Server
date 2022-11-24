package server.remote;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

import server.data.domain.Challenge;
import server.data.dto.ChallengeDTO;
import server.data.dto.SessionDTO;
import server.data.dto.UserDTO;
import server.data.domain.*;
//This interface defines the API of the Server. It represents the Remote Facade pattern
public interface IRemoteFacade extends Remote {
	public long login(String email, String password) throws RemoteException;
	public void logout(long token) throws RemoteException;
	public List<SessionDTO> getSessions() throws RemoteException;
	public List<ChallengeDTO> getChallenges() throws RemoteException;
	public boolean makeCr(long token,String sessionorchallengetitle) throws RemoteException ;
	public ChallengeDTO createChallenge(String name,Date start, Date end, int distance, long time, long token) throws RemoteException;
	public SessionDTO createSession(String title, String sport,int distance,Date start,Date end,long token,long duration) throws RemoteException;
	public UserDTO RegisterUser(String nickname,String password);
	public void signUp(User Owner, Challenge challenge);
	
}
