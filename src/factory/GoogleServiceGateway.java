package factory;

import java.rmi.Naming;


import Remote.IGoogle;
public class GoogleServiceGateway {
	private IGoogle GoogleService;
	private static GoogleServiceGateway instance;
	public GoogleServiceGateway() {
		try {		
			String URL = "//127.0.0.1:1099/E2_GoogleServer";
			this.GoogleService = (IGoogle) Naming.lookup(URL);
		} catch (Exception ex) {
			System.err.println("# Error locating remote facade: " + ex);
		}
	}
	public static GoogleServiceGateway getInstance() {
		if(instance == null) {
			instance = new GoogleServiceGateway();
		}
		return instance;
	}
	public boolean checkUser(String email,String password) {
		try {
			return this.GoogleService.checkGoogleUser(email, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
