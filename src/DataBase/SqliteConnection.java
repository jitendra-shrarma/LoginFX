package DataBase;

import java.sql.*;

public class SqliteConnection {

	private static PreparedStatement perp=null;

	/**returns Connection to DataBase*/
	public static Connection Connect() {
		try {
			Class.forName("org.sqlite.JDBC");
			Connection connection = DriverManager.getConnection("jdbc:sqlite:DataBase.db");
			return connection;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void createUserAccountsDataBase(){
		try {
			perp=SqliteConnection.Connect().prepareStatement("CREATE TABLE if not exists accounts(username varchar(30),"
					+ "password varchar(30));");
			perp.executeUpdate();
			perp.close();
			System.out.println("Successfully connected to database");
		} catch (SQLException e) {
			System.out.println("can't connect to dataBase");
		}
	}

	public static void insertAccount(String userName,String password){
		try
		{
			String insert="insert into accounts(username,password)"+"values(?,?)";
			perp=SqliteConnection.Connect().prepareStatement(insert);
			perp.setString(1,userName);
			perp.setString(2, password);
			boolean b = perp.execute();
			perp.close();
			System.out.println("Accounts Entry added.");
		}
		catch(Exception e) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
	}

	public static boolean isSignUp(String user, String password){
		String  query = "select count(*) from accounts where username = ? and password = ?";
		try {
			perp = SqliteConnection.Connect().prepareStatement(query);
			perp.setString(1, user);
			perp.setString(2, password);
			ResultSet resultSet = perp.executeQuery();
			int count = resultSet.getInt(1);
			if(count==1){
				return true;
			}
			perp.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}