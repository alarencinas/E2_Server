package server.remote;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

import server.data.domain.Challenge;
import server.data.dto.ChallengeDTO;
import server.data.dto.SessionDTO;
import server.data.domain.*;
//This interface defines the API of the Server. It represents the Remote Facade pattern
public interface IRemoteFacade extends Remote {
	public long login(String email, String password) throws RemoteException;
	public void logout(long token) throws RemoteException;
	public List<SessionDTO> getSessions() throws RemoteException;
	public List<ChallengeDTO> getChallenges() throws RemoteException;
	public boolean makeCr(long token,String sessionorchallengetitle) throws RemoteException ;
	public Challenge createChallenge(String name,Date start, Date end, int distance, long time, User Owner);
	public Session createSession(String title, String sport,int distance,Date start,Date end,User Owner,long duration);
	public User RegisterUser(String nickname,String password);
	
}
