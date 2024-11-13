package M;

import java.sql.*;
import java.util.ArrayList;
import common.GlobalData;

public class DataManager
{
	public static ArrayList<AccidentDB> getAllAccidents()
	{
		ArrayList<AccidentDB> list = new ArrayList<AccidentDB>();
		try
		{
			String myDriver = "com.mysql.cj.jdbc.Driver"; 
			String myUrl = "jdbc:mysql://" + GlobalData.DATABASE_LOCATION + ":" + GlobalData.DATABASE_PORT + "/"
					+ GlobalData.DATABASE_DATABASE_NAME;

			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(myUrl, GlobalData.DATABASE_USERNAME,
					GlobalData.DATABASE_PASSWORD);

			String query = "SELECT * FROM accidents";
		
			Statement st = conn.createStatement();
			
			ResultSet rs = st.executeQuery(query);

			while (rs.next())
			{
				int accidentID= rs.getInt("accident_id");
				int deviceID = rs.getInt("device_id");
				String accidentDescr = rs.getString("accident_description");
				Timestamp accidenTime = rs.getTimestamp("time_occur");
				int roadID = rs.getInt("road_id");


				AccidentDB a = new AccidentDB(accidentID, deviceID, accidentDescr, accidenTime, roadID);
				list.add(a);
				
			}
			st.close();
		} catch (Exception e)
		{
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}

		return list;
	}

	public static ArrayList<CongestedDB> getAllCongestedTime()
	{
		ArrayList<CongestedDB> list = new ArrayList<CongestedDB>();
		try
		{
			String myDriver = "com.mysql.cj.jdbc.Driver"; 
			String myUrl = "jdbc:mysql://" + GlobalData.DATABASE_LOCATION + ":" + GlobalData.DATABASE_PORT + "/"
					+ GlobalData.DATABASE_DATABASE_NAME;

			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(myUrl, GlobalData.DATABASE_USERNAME,
					GlobalData.DATABASE_PASSWORD);

			String query = "SELECT * FROM congested_duration";
		
			Statement st = conn.createStatement();
			
			ResultSet rs = st.executeQuery(query);

			while (rs.next())
			{
				int congestedID= rs.getInt("congested_id");
				int roadID = rs.getInt("road_id");
				String congestedCause = rs.getString("congested_cause");
				Timestamp congestStartTime = rs.getTimestamp("congested_time_start");
				Timestamp congestEndTime = rs.getTimestamp("congested_time_end");


				CongestedDB c = new CongestedDB(congestedID, roadID, congestedCause, congestStartTime, congestEndTime);
				list.add(c);
				
			}
			st.close();
		} catch (Exception e)
		{
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}

		return list;
	}
	
	public static ArrayList<TrafficDirectionsDB> getAllTrafficDirection()
	{
		ArrayList<TrafficDirectionsDB> list = new ArrayList<TrafficDirectionsDB>();
		try
		{
			String myDriver = "com.mysql.cj.jdbc.Driver"; 
			String myUrl = "jdbc:mysql://" + GlobalData.DATABASE_LOCATION + ":" + GlobalData.DATABASE_PORT + "/"
					+ GlobalData.DATABASE_DATABASE_NAME;

			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(myUrl, GlobalData.DATABASE_USERNAME,
					GlobalData.DATABASE_PASSWORD);

			String query = "SELECT * FROM traffic_directions";
		
			Statement st = conn.createStatement();
			
			ResultSet rs = st.executeQuery(query);

			while (rs.next())
			{
				int dirID= rs.getInt("direction_id");
				int roadID = rs.getInt("road_id");
				Timestamp dirtStartRec = rs.getTimestamp("direction_time_start");
				Timestamp dirEndRec = rs.getTimestamp("direction_time_end");
				int volumeDirection = rs.getInt("volume_of_direction");
				String directionName = rs.getString("direction_name");
				

				TrafficDirectionsDB t = new TrafficDirectionsDB(dirID, roadID, directionName, volumeDirection, dirtStartRec, dirEndRec );
				list.add(t);
				
			}
			st.close();
		} catch (Exception e)
		{
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}

		return list;
	}
	
	public static ArrayList<TrafficVolumeDB> getAllTrafficVolume()
	{
		ArrayList<TrafficVolumeDB> list = new ArrayList<TrafficVolumeDB>();
		try
		{
			String myDriver = "com.mysql.cj.jdbc.Driver"; 
			String myUrl = "jdbc:mysql://" + GlobalData.DATABASE_LOCATION + ":" + GlobalData.DATABASE_PORT + "/"
					+ GlobalData.DATABASE_DATABASE_NAME;

			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(myUrl, GlobalData.DATABASE_USERNAME,
					GlobalData.DATABASE_PASSWORD);

			String query = "SELECT * FROM traffic_volume";
		
			Statement st = conn.createStatement();
			
			ResultSet rs = st.executeQuery(query);

			while (rs.next())
			{
				int volumeID= rs.getInt("volume_id");
				int roadID = rs.getInt("road_id");	
				int volumeVehicles = rs.getInt("volume_of_vehicles");
				Timestamp volumeStartRec = rs.getTimestamp("volume_time_start");
				Timestamp volumeEndRec = rs.getTimestamp("volume_time_end");
				

				TrafficVolumeDB t = new TrafficVolumeDB(volumeID, roadID, volumeVehicles, volumeStartRec, volumeEndRec );
				list.add(t);
				
			}
			st.close();
		} catch (Exception e)
		{
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}

		return list;
	}
	
	public static ArrayList<RainfallDB> getAllRainFall()
	{
		ArrayList<RainfallDB> list = new ArrayList<RainfallDB>();
		try
		{
			String myDriver = "com.mysql.cj.jdbc.Driver"; 
			String myUrl = "jdbc:mysql://" + GlobalData.DATABASE_LOCATION + ":" + GlobalData.DATABASE_PORT + "/"
					+ GlobalData.DATABASE_DATABASE_NAME;

			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(myUrl, GlobalData.DATABASE_USERNAME,
					GlobalData.DATABASE_PASSWORD);

			String query = "SELECT * FROM rain_information";
		
			Statement st = conn.createStatement();
			
			ResultSet rs = st.executeQuery(query);

			while (rs.next())
			{
				int rainID= rs.getInt("rain_id");
				int roadID = rs.getInt("road_id");	
				int rainFall = rs.getInt("rainfall");
				Timestamp rainTimeStart = rs.getTimestamp("rain_time_start");
				Timestamp rainTimeEnd = rs.getTimestamp("rain_time_end");
				

				RainfallDB t = new RainfallDB(rainID, roadID, rainFall, rainTimeStart, rainTimeEnd );
				list.add(t);
				
			}
			st.close();
		} catch (Exception e)
		{
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}

		return list;
	}
	
	public static ArrayList<WaterLevelDB> getAllWaterLevel()
	{
		ArrayList<WaterLevelDB> list = new ArrayList<WaterLevelDB>();
		try
		{
			String myDriver = "com.mysql.cj.jdbc.Driver"; 
			String myUrl = "jdbc:mysql://" + GlobalData.DATABASE_LOCATION + ":" + GlobalData.DATABASE_PORT + "/"
					+ GlobalData.DATABASE_DATABASE_NAME;

			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(myUrl, GlobalData.DATABASE_USERNAME,
					GlobalData.DATABASE_PASSWORD);

			String query = "SELECT * FROM water_level_information";
		
			Statement st = conn.createStatement();
			
			ResultSet rs = st.executeQuery(query);

			while (rs.next())
			{
				int waterLevelID= rs.getInt("water_level_id");
				int roadID = rs.getInt("road_id");	
				int waterLevel = rs.getInt("water_level");
				Timestamp waterLevelStartRec = rs.getTimestamp("water_level_time_start");
				Timestamp waterLevelEndRec = rs.getTimestamp("water_level_time_end");
				

				WaterLevelDB w = new WaterLevelDB(waterLevelID, roadID, waterLevel, waterLevelStartRec, waterLevelEndRec );
				list.add(w);
				
			}
			st.close();
		} catch (Exception e)
		{
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}

		return list;
	}
	
}
