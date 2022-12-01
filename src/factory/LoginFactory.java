package factory;

public class LoginFactory {
	public static ILoginGateway AddLoginService(String type) { //TODO EL TYPEUSER.
		if (type.equals("Google")) {
			return (ILoginGateway) new GoogleServiceGateway();
		} else if (type.equals("Facebook")) {
			final String serverIP = "127.0.0.1";	//TODO not to hardcode it, beter with ant
			final int serverPort = 35600;
			return new FacebookSocketClient(serverIP, serverPort);
		} else {
			return null;
		}
	}
}
