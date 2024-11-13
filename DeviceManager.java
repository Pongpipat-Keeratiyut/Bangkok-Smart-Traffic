package M;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import common.GlobalData;

public class DeviceManager
{
	
	public static ArrayList<DeviceDB> getAllDevice() 
	{
		ArrayList<DeviceDB> list = new ArrayList<DeviceDB>();
		try
		{
			String myDriver = "com.mysql.cj.jdbc.Driver";
			String myUrl = "jdbc:mysql://" + GlobalData.DATABASE_LOCATION + ":" + GlobalData.DATABASE_PORT + "/"
					+ GlobalData.DATABASE_DATABASE_NAME;
			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(myUrl, GlobalData.DATABASE_USERNAME,
					GlobalData.DATABASE_PASSWORD);

			String query="SELECT * FROM devices INNER JOIN device_types ON devices.device_type_id = device_types.device_type_id";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
		
			while (rs.next())
			{
				int deviceID = rs.getInt("device_id");
				int roadID = rs.getInt("road_id");
				String deviceName = rs.getString("device_name");
				int deviceTypeID = rs.getInt("device_type_id");
				String deviceTypeDescr = rs.getString("device_type_description");		
				
				byte[] img_byte = rs.getBytes("device_type_image"); 
				BufferedImage bufferedimg = null;
								
				ByteArrayInputStream bais = new ByteArrayInputStream(img_byte);
				bufferedimg = ImageIO.read(bais);
				bais.close();
				
				DeviceDB d = new DeviceDB(deviceID, roadID, deviceName, deviceTypeID, deviceTypeDescr, bufferedimg);
				list.add(d);				
			}
			st.close();
		} catch (Exception e)
		{
			System.err.println("Got an exception1! ");
			System.err.println(e.getMessage());
		}

		return list;
	}
	
	public static void saveDevice(DeviceDB x)
	{
		try
		{
			String myDriver = "com.mysql.cj.jdbc.Driver";
			String myUrl = "jdbc:mysql://" + GlobalData.DATABASE_LOCATION + ":" + GlobalData.DATABASE_PORT + "/"
					+ GlobalData.DATABASE_DATABASE_NAME;

			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(myUrl, GlobalData.DATABASE_USERNAME,
					GlobalData.DATABASE_PASSWORD);

			String insertDevicesQuery = "INSERT INTO devices (device_id, device_name, device_type_id, road_id) VALUES (0, ?, ?, ?)";
			PreparedStatement psDevices = conn.prepareStatement(insertDevicesQuery);

			psDevices.setString(1, x.device_name);
			psDevices.setInt(2, x.device_type_id);
			psDevices.setInt(3, x.road_id);
			psDevices.executeUpdate();
			
			psDevices.close();
			
		} catch (Exception e)
		{
			System.err.println("Got an exception2! ");
			System.err.println(e.getMessage());
		}
	}
	
	public static void deleteDevice(DeviceDB x)
	{
		try
		{
			String myDriver = "com.mysql.cj.jdbc.Driver";
			String myUrl = "jdbc:mysql://" + GlobalData.DATABASE_LOCATION + ":" + GlobalData.DATABASE_PORT + "/"
					+ GlobalData.DATABASE_DATABASE_NAME;

			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(myUrl, GlobalData.DATABASE_USERNAME,
					GlobalData.DATABASE_PASSWORD);
			
			String query = "DELETE FROM devices  WHERE  device_id = ?";
			PreparedStatement ps = conn.prepareStatement(query);
	        ps.setInt(1, x.device_id);
	        
			ps.executeUpdate();	
			ps.close();
		} catch (Exception e)
		{
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
	}
	
}
