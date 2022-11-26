package server.data.domain;

public class UserPass extends User {
	private String pass;//Refers to the user password
	public boolean checkPassword (String pass) {
		return this.pass.equals(pass);
	}
	public String getPassword() {
		return this.pass;
	}
	public void setPassword(String pass) {
		this.pass=pass;
	}
}
