package pm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class interactions {

	public static void add() throws SQLException {
		System.out.println("Enter software name: ");
		String software = Main.sc.next().toLowerCase().trim();
		System.out.println("Enter username: ");
		Main.sc.nextLine();
		String username = Main.sc.nextLine().trim();
		System.out.println("Enter password: ");
		String password = Main.sc.next().trim();
		String query = "insert into user_data(software, username, pass) values(?,?,?)";
		Connection con = DriverManager.getConnection(Main.url, Main.user, Main.pass);
		PreparedStatement st = con.prepareStatement(query);
		st.setString(1, software);
		st.setString(2, username);
		st.setString(3, password);
		st.executeUpdate();
		System.out.println("Entry added.");
		st.close();
		con.close();
	}

	public static void get() throws SQLException {
		System.out.println("Enter software name: ");
		String software = Main.sc.next().toLowerCase().trim();
		String query = "select * from user_data where software = '" + software + "'";
		Connection con = DriverManager.getConnection(Main.url, Main.user, Main.pass);
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		String data = "";
		System.out.println("---------------------\nxxxxxxxxxxxxxxxxxxxx\n---------------------");
		System.out.println("Usernames\t\tPasswords");
		while (rs.next()) {
			System.out.println(rs.getString(3) + "\t\t" + rs.getString(4));
		}
		st.close();
		con.close();
	}

	public static void get(String software) throws SQLException {
		String query = "select * from user_data where software = '" + software + "'";
		Connection con = DriverManager.getConnection(Main.url, Main.user, Main.pass);
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		String data = "";
		System.out.println("---------------------\nxxxxxxxxxxxxxxxxxxxx\n---------------------");
		System.out.println("ID\t\tUsernames\t\tPasswords");
		while (rs.next()) {
			System.out.println(rs.getInt(1) + "\t\t" + rs.getString(3) + "\t\t" + rs.getString(4));
		}
		st.close();
		con.close();
	}

	public static void remove() throws SQLException {
		System.out.println("Enter software name: ");
		String software = Main.sc.next().toLowerCase();
		get(software);
		System.out.println("Enter ID number of the data you want to delete: ");
		int id = Main.sc.nextInt();
		String query = "delete from user_data where id = ?";
		Connection con = DriverManager.getConnection(Main.url, Main.user, Main.pass);
		PreparedStatement st = con.prepareStatement(query);
		st.setInt(1, id);
		st.executeUpdate();
		System.out.println("Entry deleted password changed.");
		st.close();
		con.close();
	}
}
