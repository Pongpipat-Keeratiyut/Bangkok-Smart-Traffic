package M;

import java.sql.Timestamp;

public class AccidentDB
{
	public int accident_id ;
	public int device_id;
	public int road_id;
	public String accident_description;
	public Timestamp time_occur;

	public AccidentDB(){}
	
	public AccidentDB(int xaccident_id, int xdevice_id, String xaccident_description, Timestamp xtime_occur, int xroad_id) 
	{
		accident_id = xaccident_id;
		device_id = xdevice_id;
		accident_description = xaccident_description;
		time_occur = xtime_occur;
		road_id = xroad_id;
	}
}
