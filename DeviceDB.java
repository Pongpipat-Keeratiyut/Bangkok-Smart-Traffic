package M;
import java.awt.image.BufferedImage; 
public class DeviceDB
{
	public int device_id;
	public int road_id;
	public String device_name;
	public int device_type_id;
	public String device_type_description;
	public BufferedImage device_type_image; 

	public DeviceDB(){}

	public DeviceDB(int xdevice_id, int xroad_id, String xdevice_name, int xdevice_type_id, String xdevice_type_description,
			BufferedImage xdevicetype_image)
	{
		device_id = xdevice_id;
		road_id = xroad_id;
		device_name = xdevice_name;
		device_type_id = xdevice_type_id;
		device_type_description = xdevice_type_description;
		device_type_image = xdevicetype_image;
	}
}
