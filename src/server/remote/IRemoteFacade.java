package server.remote;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import server.data.domain.Challenge;
import server.data.dto.ChallengeDTO;
import server.data.dto.LoginUserTypeDTO;
import server.data.dto.SessionDTO;
import server.data.dto.UserDTO;
import server.data.domain.*;
//This interface defines the API of the Server. It represents the Remote Facade pattern
public interface IRemoteFacade extends Remote {
	public long login(String email, String password,String nick, LoginUserTypeDTO usertype) throws RemoteException;
	public void logout(long token) throws RemoteException;
	public List<ChallengeDTO> getChallenges(String Sport) throws RemoteException;
	//Gets how is going the challenge
	public float challAcomplished(UserDTO user,ChallengeDTO challenge)throws RemoteException;

	//Delete CHALLENGE
	public void DelChallenge(String title)throws RemoteException;
	public UserDTO getUser(String email,String password)throws RemoteException, ParseException;
	
	//AcceptChallenge
	public  UserDTO acceptChallenge(User userDTO, ChallengeDTO challengeAccepted) throws RemoteException;
	
	//Create Session
	public List <SessionDTO> createSession(UserDTO userDTO, String title , String sport, int distance, Date start, long duration)throws RemoteException;
	//Create Challenge
	public void createChallenge(UserDTO userDTO,String name, Date start, Date end ,int distance ,float time,String Sport )throws RemoteException;
	
	//Create User
	public void createUser(LoginUserTypeDTO type, String nickname,String password,String email)throws RemoteException;
	//Update User
	public UserDTO updateUser(UserDTO user)throws RemoteException;
	
}
