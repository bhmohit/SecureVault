/*This class is for each row of data which contains the id, software,
  username, password and iv used for encrypting the password. 
 */

package application;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Entry {
	int id;
	String software;
	String username;
	String password;
	byte[] iv;
	private IntegerProperty idProperty;
	private StringProperty softwareProperty;
	private StringProperty usernameProperty;
	private StringProperty passwordProperty;

	Entry() {
		this.idProperty = new SimpleIntegerProperty();
		this.softwareProperty = new SimpleStringProperty();
		this.usernameProperty = new SimpleStringProperty();
		this.passwordProperty = new SimpleStringProperty();
	}

	Entry(String software, String username, String password) {
		this.software = software;
		this.username = username;
		this.password = password;
	}

	public int getID() {
		return idProperty.get();
	}

	public void setID(int id) {
		this.idProperty.set(id);
	}

	public IntegerProperty getID2() {
		return idProperty;
	}

	public String getSoftware() {
		return softwareProperty.get();
	}

	public void setSoftware(String software) {
		this.softwareProperty.set(software);
	}

	public StringProperty getSoftware2() {
		return softwareProperty;
	}

	public String getUsername() {
		return usernameProperty.get();
	}

	public void setUsername(String username) {
		this.usernameProperty.set(username);
	}

	public StringProperty getUsername2() {
		return usernameProperty;
	}

	public String getPassword() {
		return passwordProperty.get();
	}

	public void setPassword(String password) {
		this.passwordProperty.set(password);
	}

	public StringProperty getPassword2() {
		return passwordProperty;
	}
}
