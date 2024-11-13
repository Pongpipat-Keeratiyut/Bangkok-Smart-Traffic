package M;

import java.sql.Timestamp;

public class RainfallDB
{
	public int rain_id ;
	public int road_id;
	public int rainfall;
	public Timestamp rain_time_start;
	public Timestamp rain_time_end;

	public RainfallDB(){}

	public RainfallDB(int xrain_id, int xroad_id, int xrainfall, 
			Timestamp xrain_time_start, Timestamp xrain_time_end) 
	{
		rain_id = xrain_id;
		road_id = xroad_id;
		rainfall = xrainfall;
		rain_time_start = xrain_time_start;
		rain_time_end = xrain_time_end;
	}
}
