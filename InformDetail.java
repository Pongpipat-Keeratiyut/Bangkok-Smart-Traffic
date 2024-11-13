package M;

public class InformDetail
{
	public int roadID;
	public String roadName;
	public Long congested_time;
	public String accident;
	public String congested;
	
	public int rainfall;
	public int waterlevel;
	
	public InformDetail(){}

	public InformDetail(int xroad_id, String xroad_name, String xaccident_descr,
			Long xcongested_time, String xcongested_cause,
			int xrainfall, int xwater_level)
	{
		roadID = xroad_id;
		roadName = xroad_name;
		accident = xaccident_descr;
		congested_time = xcongested_time;
		congested = xcongested_cause;
		rainfall = xrainfall;
		waterlevel = xwater_level;
	}
}