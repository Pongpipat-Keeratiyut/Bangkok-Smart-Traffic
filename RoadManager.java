package M;

import java.sql.*;
import java.util.ArrayList;
import common.GlobalData;

public class RoadManager
{
	public static ArrayList<RoadDB> getAllRoad()
	{
		ArrayList<RoadDB> list = new ArrayList<RoadDB>();
		try
		{
			String myDriver = "com.mysql.cj.jdbc.Driver"; 
			String myUrl = "jdbc:mysql://" + GlobalData.DATABASE_LOCATION + ":" + GlobalData.DATABASE_PORT + "/"
					+ GlobalData.DATABASE_DATABASE_NAME;

			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(myUrl, GlobalData.DATABASE_USERNAME,
					GlobalData.DATABASE_PASSWORD);

			String query = "SELECT * FROM road";
	
			Statement st = conn.createStatement();
		
			ResultSet rs = st.executeQuery(query);
			
			while (rs.next())
			{
				int roadID= rs.getInt("road_id");
				int userID = rs.getInt("user_id");
				String roadName = rs.getString("road_name");
				String subDistrict = rs.getString("sub_district");
				String district = rs.getString("district");
				String province = rs.getString("province");
				String postalCode = rs.getString("postal_code");

				RoadDB r = new RoadDB(roadID, userID, roadName, subDistrict, district, province, postalCode);
				list.add(r);
		
			}
			st.close();
		} catch (Exception e)
		{
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}

		return list;
	}

	public static void saveNewRoad(RoadDB x)
	{
		try
		{
			String myDriver = "com.mysql.cj.jdbc.Driver";
			String myUrl = "jdbc:mysql://" + GlobalData.DATABASE_LOCATION + ":" + GlobalData.DATABASE_PORT + "/"
					+ GlobalData.DATABASE_DATABASE_NAME;

			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(myUrl, GlobalData.DATABASE_USERNAME,
					GlobalData.DATABASE_PASSWORD);
			
			String query = "INSERT INTO road VALUES ( 0,  ? ,  ? ,  ? ,  ? ,  ? ,  ? )  ";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, x.user_id);
	        ps.setString(2, x.road_name);
	        ps.setString(3, x.sub_district);
	        ps.setString(4, x.district);
	        ps.setString(5, x.province);
	        ps.setString(6, x.postal_code);
	        
			ps.executeUpdate();	
			ps.close();
		} catch (Exception e)
		{
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
	}

	public static void editRoad(RoadDB x)
	{
		try
		{
			String myDriver = "com.mysql.cj.jdbc.Driver";
			String myUrl = "jdbc:mysql://" + GlobalData.DATABASE_LOCATION + ":" + GlobalData.DATABASE_PORT + "/"
					+ GlobalData.DATABASE_DATABASE_NAME;

			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(myUrl, GlobalData.DATABASE_USERNAME,
					GlobalData.DATABASE_PASSWORD);
	
			String query = "UPDATE road SET user_id =  ? ,  road_name =  ? ,  sub_district =  ? ,  district =  ? ,"
					+ "  province =  ? ,   postal_code = ?  WHERE  road_id = ?  ";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, x.user_id);
	        ps.setString(2, x.road_name);
	        ps.setString(3, x.sub_district);
	        ps.setString(4, x.district);
	        ps.setString(5, x.province);
	        ps.setString(6, x.postal_code);
	        ps.setInt(7, x.road_id);
	        
			ps.executeUpdate();	
			ps.close();
		} catch (Exception e)
		{
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
	}

	public static void deleteRoad(RoadDB x)
	{
		try
		{
			String myDriver = "com.mysql.cj.jdbc.Driver";
			String myUrl = "jdbc:mysql://" + GlobalData.DATABASE_LOCATION + ":" + GlobalData.DATABASE_PORT + "/"
					+ GlobalData.DATABASE_DATABASE_NAME;

			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(myUrl, GlobalData.DATABASE_USERNAME,
					GlobalData.DATABASE_PASSWORD);
			
			String query = "DELETE FROM road  WHERE  road_id = ?";
			PreparedStatement ps = conn.prepareStatement(query);
	        ps.setInt(1, x.road_id);
	        
			ps.executeUpdate();	
			ps.close();
		} catch (Exception e)
		{
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
	}
	
	public static ArrayList<RoadDB> searchRoad(String s)
	{
		ArrayList<RoadDB> list = new ArrayList<RoadDB>();
		try
		{
			String myDriver = "com.mysql.cj.jdbc.Driver";
			String myUrl = "jdbc:mysql://" + GlobalData.DATABASE_LOCATION + ":" + GlobalData.DATABASE_PORT + "/"
					+ GlobalData.DATABASE_DATABASE_NAME;			
			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(myUrl, GlobalData.DATABASE_USERNAME,
					GlobalData.DATABASE_PASSWORD);

			String query = "SELECT * FROM road WHERE road_name LIKE ?";

			 try ( PreparedStatement ps = conn.prepareStatement(query) ) 
			 {
			        StringBuilder pattern = new StringBuilder();
			        
			        // สร้าง pattern ของ SQL query โดยเพิ่ม % หน้าและหลังแต่ละตัวอักษรที่ต้องการค้นหา
			        for (int i = 0; i < s.length(); i++) 
			        {
			            pattern.append(s.charAt(i));
			            pattern.append("%");
			        }
			        
			        ps.setString(1, pattern.toString());
			        
			        try (ResultSet rs = ps.executeQuery()) 
			        {	
						while (rs.next())
						{
							int roadID= rs.getInt("road_id");
							int userID = rs.getInt("user_id");
							String roadName = rs.getString("road_name");
							String subDistrict = rs.getString("sub_district");
							String district = rs.getString("district");
							String province = rs.getString("province");
							String postalCode = rs.getString("postal_code");
				
							RoadDB r = new RoadDB(roadID, userID, roadName, subDistrict, district, province, postalCode);
							list.add(r);		
						}
						ps.close();
			        }
			 }
		} catch (Exception e)
		{
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}

		return list;
	}
	
}
