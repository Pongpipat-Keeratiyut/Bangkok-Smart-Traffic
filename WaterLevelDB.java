package M;

import java.sql.Timestamp;

public class WaterLevelDB
{
	public int water_level_id ;
	public int road_id;
	public int water_level;
	public Timestamp water_level_time_start;
	public Timestamp water_level_time_end;

	public WaterLevelDB(){}

	public WaterLevelDB(int xwater_level_id, int xroad_id, int xwater_level, 
			Timestamp xwater_level_time_start, Timestamp xwater_level_time_end) 
	{
		water_level_id = xwater_level_id;
		road_id = xroad_id;
		water_level = xwater_level;
		water_level_time_start = xwater_level_time_start;
		water_level_time_end = xwater_level_time_end;
	}
}
