package M;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import common.GlobalData;

public class UserManager
{
	public static ArrayList<UserDB> getAllUser() 
	{
		ArrayList<UserDB> list = new ArrayList<UserDB>();
		try
		{
			String myDriver = "com.mysql.cj.jdbc.Driver";
			String myUrl = "jdbc:mysql://" + GlobalData.DATABASE_LOCATION + ":" + GlobalData.DATABASE_PORT + "/"
					+ GlobalData.DATABASE_DATABASE_NAME;
			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(myUrl, GlobalData.DATABASE_USERNAME,
					GlobalData.DATABASE_PASSWORD);
			
			String query = "SELECT * FROM users";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);

			while (rs.next())
			{
				int id = rs.getInt("user_id");
				String username = rs.getString("username");
				String password = rs.getString("password");
				String usertype = rs.getString("user_type");				
			
				UserDB cc = new UserDB(id, username, password, usertype);
				list.add(cc);				
			}
			st.close();
		} catch (Exception e)
		{
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}

		return list;
	}
	
	public static void saveNewUser(UserDB x)
	{
		try
		{
			String myDriver = "com.mysql.cj.jdbc.Driver";
			String myUrl = "jdbc:mysql://" + GlobalData.DATABASE_LOCATION + ":" + GlobalData.DATABASE_PORT + "/"
					+ GlobalData.DATABASE_DATABASE_NAME;

			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(myUrl, GlobalData.DATABASE_USERNAME,
					GlobalData.DATABASE_PASSWORD);
			
			String query = "INSERT INTO users VALUES ( 0,   ?,  ?,  ? ) ";
			PreparedStatement ps = conn.prepareStatement(query);
	        ps.setString(1, x.username);
	        ps.setString(2, x.password);
	        ps.setString(3, x.user_type);
	        
			ps.executeUpdate();	
			ps.close();
		} catch (Exception e){
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
	}
	
	public static void editUser(UserDB x)
	{
		try
		{
			String myDriver = "com.mysql.cj.jdbc.Driver";
			String myUrl = "jdbc:mysql://" + GlobalData.DATABASE_LOCATION + ":" + GlobalData.DATABASE_PORT + "/"
					+ GlobalData.DATABASE_DATABASE_NAME;
			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(myUrl, GlobalData.DATABASE_USERNAME,
					GlobalData.DATABASE_PASSWORD);
			
			String query = "UPDATE users SET username =  ?,  password = ?,  user_type = ?  WHERE  user_id = ?";
			PreparedStatement ps = conn.prepareStatement(query);
	        ps.setString(1, x.username);
	        ps.setString(2, x.password);
	        ps.setString(3, x.user_type);
	        ps.setInt(4, x.user_id);
	        
			ps.executeUpdate();	
			ps.close();
		} catch (Exception e)
		{
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
	 
	}

	public static void deleteUser(UserDB x)
	{
		try
		{
			String myDriver = "com.mysql.cj.jdbc.Driver";
			String myUrl = "jdbc:mysql://" + GlobalData.DATABASE_LOCATION + ":" + GlobalData.DATABASE_PORT + "/"
					+ GlobalData.DATABASE_DATABASE_NAME;

			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(myUrl, GlobalData.DATABASE_USERNAME,
					GlobalData.DATABASE_PASSWORD);
			
			String query = "DELETE FROM users  WHERE  user_id = ?";
			PreparedStatement ps = conn.prepareStatement(query);
	        ps.setInt(1, x.user_id);
	        
			ps.executeUpdate();	
			ps.close();
			
		} catch (Exception e)
		{
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
	}
	
	public static boolean checkLogin(String username ,String password) 
	{		
		try
		{
			String myDriver = "com.mysql.cj.jdbc.Driver";
			String myUrl = "jdbc:mysql://" + GlobalData.DATABASE_LOCATION + ":" + GlobalData.DATABASE_PORT + "/"
					+ GlobalData.DATABASE_DATABASE_NAME;
			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(myUrl, GlobalData.DATABASE_USERNAME,
					GlobalData.DATABASE_PASSWORD);

			String query = "SELECT * FROM users WHERE username = ?  AND  password = ? ";

			PreparedStatement st = conn.prepareStatement(query);
			st.setString(1, username);
			st.setString(2, password);
			
			ResultSet rs = st.executeQuery();
			while (rs.next())
			{
				GlobalData.CurrentUser_userID = rs.getInt(1);
				GlobalData.CurrentUser_username = rs.getString(2);
				GlobalData.CurrentUser_usertype = rs.getString(4);
				return true;
			}
			st.close();
		} catch (Exception e)
		{
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
		
		return false;
	}
}
