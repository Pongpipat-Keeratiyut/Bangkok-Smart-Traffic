package M;

import java.sql.Timestamp;

public class CongestedDB
{
	public int congested_id ;
	public int road_id;
	public String congested_cause;
	public Timestamp congested_time_start;
	public Timestamp congested_time_end;

	public CongestedDB(){}

	public CongestedDB(int xcongested_id, int xroad_id, String xcongested_cause, 
			Timestamp xcongested_time_start, Timestamp xcongested_time_end) 
	{
		congested_id = xcongested_id;
		road_id = xroad_id;
		congested_cause = xcongested_cause;
		congested_time_start = xcongested_time_start;
		congested_time_end = xcongested_time_end;
	}
}
