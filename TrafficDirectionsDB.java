package M;

import java.sql.Timestamp;

public class TrafficDirectionsDB
{
	public int direction_id ;
	public int road_id;
	public String direction_name;
	public int volume_of_direction;
	public Timestamp direction_time_start;
	public Timestamp direction_time_end;

	public TrafficDirectionsDB(){}

	public TrafficDirectionsDB(int xdirection_id, int xroad_id, String xdirection_name, int xvolume_of_direction,
			Timestamp xdirection_time_start, Timestamp xdirection_time_end) 
	{
		direction_id = xdirection_id;
		road_id = xroad_id;
		direction_name = xdirection_name;
		volume_of_direction = xvolume_of_direction;
		direction_time_start = xdirection_time_start;
		direction_time_end = xdirection_time_end;
	}
}
