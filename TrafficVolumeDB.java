package M;

import java.sql.Timestamp;

public class TrafficVolumeDB
{
	public int volume_id ;
	public int road_id;
	public int volume_of_vehicles;
	public Timestamp volume_time_start;
	public Timestamp volume_time_end;

	public TrafficVolumeDB(){}

	public TrafficVolumeDB(int xvolume_id, int xroad_id, int xvolume_of_vehicles, 
			Timestamp xvolume_time_start, Timestamp xvolume_time_end) 
	{
		volume_id = xvolume_id;
		road_id = xroad_id;
		volume_of_vehicles = xvolume_of_vehicles;
		volume_time_start = xvolume_time_start;
		volume_time_end = xvolume_time_end;
	}
}
