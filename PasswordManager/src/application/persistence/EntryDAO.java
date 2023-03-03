/* This class implements JDBC's Data Access Object principles for the entry class
 */

package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.crypto.spec.IvParameterSpec;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EntryDAO {
	static String url = "jdbc:mysql://localhost:3306/passwordmanager"; // change your port number (i.e 3306) and
																		// database name (i.e passwordmanager)
	static String user = "root"; // username for the database
	static String pass = "root"; // password for the database
	private static final String salt = "salt"; // salt used for encrypting passwords and usernames. change it as you
												// like.
	private static final String algo = "AES/CBC/PKCS5Padding";

	public static void addEntry(Entry entry) throws Exception {
		entry.iv = Encrypt.generateByteArray();
		IvParameterSpec ivParameterSpec = Encrypt.generateIv(entry.iv);
		String query = "insert into user_data(software, username, pass, iv) values(?,?,?,?)";
		Connection con = DriverManager.getConnection(url, user, pass);
		PreparedStatement st = con.prepareStatement(query);
		st.setString(1, entry.software);
		st.setString(2, Encrypt.encrypt(algo, entry.username,
				Encrypt.pbkdf2(MasterPasswordDAO.getDetails().master, salt), ivParameterSpec));
		st.setString(3, Encrypt.encrypt(algo, entry.password,
				Encrypt.pbkdf2(MasterPasswordDAO.getDetails().master, salt), ivParameterSpec));
		st.setBytes(4, entry.iv);
		st.executeUpdate();
		st.close();
		con.close();
	}

	public static int removeEntry(int id) throws Exception {
		String query = "delete from user_data where id = ?";
		Connection con = DriverManager.getConnection(url, user, pass);
		PreparedStatement st = con.prepareStatement(query);
		st.setInt(1, id);
		int size = st.executeUpdate();
		st.close();
		con.close();
		return size;
	}

	public static void updateEntry(Entry entry) throws Exception {
		entry.iv = Encrypt.generateByteArray();
		IvParameterSpec ivParameterSpec = Encrypt.generateIv(entry.iv);
		String query = "update user_data set software = ?, username = ?, pass = ?, iv = ? where id = ?";
		Connection con = DriverManager.getConnection(url, user, pass);
		PreparedStatement st = con.prepareStatement(query);
		st.setString(1, entry.software);
		st.setString(2, Encrypt.encrypt(algo, entry.username,
				Encrypt.pbkdf2(MasterPasswordDAO.getDetails().master, salt), ivParameterSpec));
		st.setString(3, Encrypt.encrypt(algo, entry.password,
				Encrypt.pbkdf2(MasterPasswordDAO.getDetails().master, salt), ivParameterSpec));
		st.setBytes(4, entry.iv);
		st.setInt(5, entry.id);
		st.executeUpdate();
		st.close();
		con.close();
	}

	public static ObservableList<Entry> getRecords() throws Exception {
		String query = "select * from user_data";
		Connection con = DriverManager.getConnection(url, user, pass);
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		ObservableList<Entry> entryList = getEntryObjects(rs);
		return entryList;
	}

	private static ObservableList<Entry> getEntryObjects(ResultSet rs) throws Exception {
		ObservableList<Entry> entryList = FXCollections.observableArrayList();
		while (rs.next()) {
			Entry entry = new Entry();
			entry.setID(rs.getInt("id"));
			entry.setSoftware(rs.getString("software"));
			entry.setUsername(Encrypt.decrypt(algo, rs.getString(3),
					Encrypt.pbkdf2(MasterPasswordDAO.getDetails().master, salt), new IvParameterSpec(rs.getBytes(5))));
			entry.setPassword(Encrypt.decrypt(algo, rs.getString(4),
					Encrypt.pbkdf2(MasterPasswordDAO.getDetails().master, salt), new IvParameterSpec(rs.getBytes(5))));

			entryList.add(entry);
		}
		return entryList;
	}

}
