package GUI;

public class User {
	/**
	*This class represents user data  
	*Every user characterized by firstName, lastName, userName, password, conPassword, Permutation_Test,

	*/
	
	String FirstName,LastName,UserName,Password,ConPassword;
	/**
	 * 
	 * @param firstName user first name
	 * @param lastName user last name
	 * @param userName userName
	 * @param password password
	 * @param conPassword confirmation Password
	 */
	public User(String firstName, String lastName, String userName, String password, String conPassword) {
		super();
		FirstName = firstName;
		LastName = lastName;
		UserName = userName;
		Password = password;
		ConPassword = conPassword;
	}

	

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return FirstName;
	}



	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}



	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return LastName;
	}



	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		LastName = lastName;
	}



	/**
	 * @return the userName
	 */
	public String getUserName() {
		return UserName;
	}



	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		UserName = userName;
	}



	/**
	 * @return the password
	 */
	public String getPassword() {
		return Password;
	}



	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		Password = password;
	}



	/**
	 * @return the conPassword
	 */
	public String getConPassword() {
		return ConPassword;
	}



	/**
	 * @param conPassword the conPassword to set
	 */
	public void setConPassword(String conPassword) {
		ConPassword = conPassword;
	}



	@Override
	public String toString() {
		return "User [FirstName=" + FirstName + ", LastName=" + LastName + ", UserName=" + UserName + ", Password="
				+ Password + ", ConPassword=" + ConPassword + "]";
	}
	

}
