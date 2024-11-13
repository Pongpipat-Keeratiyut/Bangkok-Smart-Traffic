package M;

public class RoadDB
{
	public int road_id, user_id;
	public String road_name;
	public String sub_district;
	public String district;
	public String province;
	public String postal_code;
	
	public RoadDB() {}
	public RoadDB(int xid, int xuserid, String xname, String xsubdistrict, String xdistrict, String xprovince, String xpostalcode) {
		road_id = xid;
		user_id = xuserid;
		road_name = xname;
		sub_district = xsubdistrict;
		district = xdistrict;
		province = xprovince;
		postal_code = xpostalcode;
	}
}
