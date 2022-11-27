package factory;

public class LoginFactory {
	public static ILoginGateway AddLoginService(String type) {
		if (type.equals("Google")) {
			return (ILoginGateway) new GoogleServiceGateway();
		} else if (type.equals("Facebook")) {
			final String serverIP = "127.0.0.1";
			final int serverPort = 35600;
			return new FacebookSocketClient(serverIP, serverPort);
		} else {
			return null;
		}
	}
}
