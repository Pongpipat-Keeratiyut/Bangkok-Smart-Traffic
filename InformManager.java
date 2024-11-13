package M;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import common.GlobalData;

public class InformManager
{
	
	public static ArrayList<InformDetail> getAllData() 
	{
		ArrayList<InformDetail> list = new ArrayList<InformDetail>();
		try
		{
			String myDriver = "com.mysql.cj.jdbc.Driver";
			String myUrl = "jdbc:mysql://" + GlobalData.DATABASE_LOCATION + ":" + GlobalData.DATABASE_PORT + "/"
					+ GlobalData.DATABASE_DATABASE_NAME;
			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(myUrl, GlobalData.DATABASE_USERNAME,
					GlobalData.DATABASE_PASSWORD);

			String query="SELECT r.road_id, r.road_name, a.accident_description,"
					+ "TIMESTAMPDIFF(MINUTE, c.congested_time_start, c.congested_time_end) AS congested_time, "
					+ "c.congested_cause, rf.rainfall, w.water_level "
					+ "FROM road AS r LEFT JOIN accidents AS a ON r.road_id = a.road_id "
					+ "LEFT JOIN congested_duration AS c ON r.road_id = c.road_id "
					+ "LEFT JOIN rain_information AS rf ON r.road_id = rf.road_id "
					+ "LEFT JOIN water_level_information AS w ON r.road_id = w.road_id";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
		
			while (rs.next())
			{
				int id = rs.getInt("road_id");				
				String roadname = rs.getString("road_name");
				String accident = rs.getString("accident_description");
				Long congestedtime = rs.getLong("congested_time");
				String causecongest = rs.getString("congested_cause");		
				int rainfall = rs.getInt("rainfall");
				int waterlevel = rs.getInt("water_level");
				
				InformDetail i = new InformDetail(id, roadname, accident, congestedtime, causecongest, rainfall, waterlevel);
				list.add(i);				
			}
			st.close();
		} catch (Exception e)
		{
			System.err.println("Got an exception1! ");
			System.err.println(e.getMessage());
		}

		return list;
	}
	
	
	public static ArrayList<InformDetail> getSearchRoad(int roadID)
	{
		ArrayList<InformDetail> list = new ArrayList<InformDetail>();
		try
		{
			String myDriver = "com.mysql.cj.jdbc.Driver";
			String myUrl = "jdbc:mysql://" + GlobalData.DATABASE_LOCATION + ":" + GlobalData.DATABASE_PORT + "/"
					+ GlobalData.DATABASE_DATABASE_NAME;
			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(myUrl, GlobalData.DATABASE_USERNAME,
					GlobalData.DATABASE_PASSWORD);

			String query = "SELECT r.road_id, r.road_name, a.accident_description, " +
		               "TIMESTAMPDIFF(MINUTE, c.congested_time_start, c.congested_time_end) AS congested_time, " +
		               "c.congested_cause, rf.rainfall, w.water_level, " +
		               "a.accident_id, c.congested_id, rf.rain_id, w.water_level_id " +
		               "FROM road AS r " +
		               "LEFT JOIN accidents AS a ON r.road_id = a.road_id " +
		               "LEFT JOIN congested_duration AS c ON r.road_id = c.road_id " +
		               "LEFT JOIN rain_information AS rf ON r.road_id = rf.road_id " +
		               "LEFT JOIN water_level_information AS w ON r.road_id = w.road_id " +
		               "WHERE r.road_id = ?";
			PreparedStatement ps = conn.prepareStatement(query);
		    ps.setInt(1, roadID);		
			
		    ResultSet rs = ps.executeQuery();
					
			while (rs.next())
			{
				int id = rs.getInt("road_id");				
				String roadname = rs.getString("road_name");
				String accident = rs.getString("accident_description");
				Long congestedtime = rs.getLong("congested_time");
				String causecongest = rs.getString("congested_cause");		
				int rainfall = rs.getInt("rainfall");
				int waterlevel = rs.getInt("water_level");
				
				int accidentId = rs.getInt("accident_id");
	            int congestedId = rs.getInt("congested_id");
	            int rainId = rs.getInt("rain_id");
	            int waterLevelId = rs.getInt("water_level_id");
				
				InformDetail i = new InformDetail(id, roadname, accident, congestedtime, causecongest, rainfall, waterlevel);
				list.add(i);	
				
				GlobalData.saveInform = new TrafficSignSuggestDB();
				GlobalData.saveInform.accident_id = accidentId;
				GlobalData.saveInform.congested_id = congestedId;
				GlobalData.saveInform.rain_id = rainId;
				GlobalData.saveInform.water_level_id = waterLevelId;
			}
		    ps.close();
		} catch (Exception e)
		{
			System.err.println("Got an exception2! ");
			System.err.println(e.getMessage());
		}

		return list;
	}
		
	public static void saveSuggestData(TrafficSignSuggestDB x)
	{
		try
		{
			String myDriver = "com.mysql.cj.jdbc.Driver";
			String myUrl = "jdbc:mysql://" + GlobalData.DATABASE_LOCATION + ":" + GlobalData.DATABASE_PORT + "/"
					+ GlobalData.DATABASE_DATABASE_NAME;

			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(myUrl, GlobalData.DATABASE_USERNAME,
					GlobalData.DATABASE_PASSWORD);

			String Query = "INSERT INTO traffic_sign_suggestion (suggest_id, accident_id, congested_id, rain_id, water_level_id, suggestion) VALUES (0, ?, ?, ?, ?, ?)";
			PreparedStatement ps = conn.prepareStatement(Query);

			ps.setInt(1, x.accident_id);
			ps.setInt(2, x.congested_id);
			ps.setInt(3, x.rain_id);
			ps.setInt(4, x.water_level_id);
			ps.setString(5, x.suggestion);
			ps.executeUpdate();
			
			ps.close();
			
		} catch (Exception e)
		{
			System.err.println("Got an exception3! ");
			System.err.println(e.getMessage());
		}
	}
	
}
