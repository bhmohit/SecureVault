package pm;

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

public class masterPasswordHandler {

	public static String getMasterPassword() throws SQLException {
		String masterPassword = "";
		String query = "select master_password from master";
		Connection con = DriverManager.getConnection(Main.url, Main.user, Main.pass);
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		rs.next();
		masterPassword = rs.getString(1);
		st.close();
		con.close();
		return masterPassword;
	}

	public static String getKey() throws SQLException {
		String key = "";
		String query = "select forgot_key from master";
		Connection con = DriverManager.getConnection(Main.url, Main.user, Main.pass);
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		rs.next();
		key = rs.getString(1);
		st.close();
		con.close();
		return key;
	}

	public static void setMasterPassword(String newMaster, String key) throws SQLException {
		String query = "update master set master_password = ? where forgot_key = ?";
		Connection con = DriverManager.getConnection(Main.url, Main.user, Main.pass);
		PreparedStatement st = con.prepareStatement(query);
		st.setString(1, newMaster);
		st.setString(2, key);
		st.executeUpdate();
		System.out.println("Master password changed.");
		st.close();
		con.close();
	}

	public static void setKey(String newMaster, String key) throws SQLException {
		String query = "update master set forgot_key = ? where master_password = ?";
		Connection con = DriverManager.getConnection(Main.url, Main.user, Main.pass);
		PreparedStatement st = con.prepareStatement(query);
		st.setString(1, key);
		st.setString(2, newMaster);
		st.executeUpdate();
		st.close();
		con.close();
	}

	public static String generateKey() throws NoSuchAlgorithmException {
		Random random = ThreadLocalRandom.current();
		byte[] randomBytes = new byte[256 / 8];
		random.nextBytes(randomBytes);
		String key = Base64.getUrlEncoder().encodeToString(randomBytes);
		return key;
	}

}
