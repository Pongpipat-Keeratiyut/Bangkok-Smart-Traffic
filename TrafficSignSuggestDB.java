package M;

public class TrafficSignSuggestDB
{
	public int suggest_id ;
	public int accident_id;
	public int congested_id;
	public int rain_id ;
	public int water_level_id;
	public String suggestion;

	public TrafficSignSuggestDB(){}

	public TrafficSignSuggestDB( int xsuggest_id, int xaccident_id, int xcongested_id,
			int xrain_id, int xwater_level_id,
			String xsuggestion )
	{
		suggest_id = xsuggest_id;
		accident_id = xaccident_id;
		congested_id = xcongested_id;
		rain_id = xrain_id;
		water_level_id = xwater_level_id;
		suggestion = xsuggestion;
	}
}
