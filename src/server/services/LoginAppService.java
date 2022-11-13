package server.services;
import  server.data.domain.User;
public class LoginAppService {
	public User login(String email, String password) {
		//TODO: Get User using DAO and check 		
		User user = new User();		
		user.setEmail("Javier.e2002@gmail.com");
		user.setNickname("Javier");		
		//Generate the hash of the password
		String sha1 = org.apache.commons.codec.digest.DigestUtils.sha1Hex("$!9PhNz,");		
		user.setPassword(sha1);
		
		if (user.getEmail().equals(email) && user.checkPassword(password)) {		
			return user;
		} else {
			return null;
		}
	}
}
