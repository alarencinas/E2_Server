package factory;

import server.data.dto.LoginUserTypeDTO;

public class LoginFactory {
	public static ILoginGateway AddLoginService(LoginUserTypeDTO userType) { 
		if (userType.equals(LoginUserTypeDTO.Google)) {
			return (ILoginGateway) new GoogleServiceGateway();
		} else if (userType.equals(LoginUserTypeDTO.Facebook)) {
			final String serverIP = "127.0.0.1";	//TODO not to hardcode it, beter with ant
			final int serverPort = 35600;
			return new FacebookSocketClient(serverIP, serverPort);
		} else {
			return null;
		}
	}
}
