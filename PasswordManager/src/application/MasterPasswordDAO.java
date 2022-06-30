/* This class also has a non-public masterpassword class.
  	The public class implements JDBC's DAO principles 
 */

package application;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Base64;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class MasterPasswordDAO {
	static String url = "jdbc:mysql://localhost:3306/passwordmanager";
	static String user = "root";
	static String pass = "root";
	static boolean exception = true;

	public static String generateKey() throws NoSuchAlgorithmException {
		Random random = ThreadLocalRandom.current();
		byte[] randomBytes = new byte[256 / 8];
		random.nextBytes(randomBytes);
		String key = Base64.getUrlEncoder().encodeToString(randomBytes);
		return key;
	}

	public static MasterPassword getDetails() throws SQLException {
		Connection con = DriverManager.getConnection(url, user, pass);
		MasterPassword details = new MasterPassword();
		String query = "select * from master";
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		rs.next();
		details.master = rs.getString(2);
		details.key = rs.getString(3);
		st.close();
		con.close();
		return details;
	}

	public static void setDetails(String master, String key) throws SQLException, NoSuchAlgorithmException {
		Connection con = DriverManager.getConnection(url, user, pass);
		String query;
		do {
			try {
				query = "update master set master_password = ?, forgot_key = ? where id = 1";
				PreparedStatement pst = con.prepareStatement(query);
				pst.setString(1, Hash.toHexString(Hash.getSHA(master)));
				pst.setString(2, Hash.toHexString(Hash.getSHA(key)));
				pst.executeUpdate();
				pst.close();
				exception = false;
			} catch (Exception e) {
				query = "insert into master values(1,?,?)";
			}
		} while (exception);
		con.close();
	}
}

class MasterPassword {
	String master;
	String key;
}