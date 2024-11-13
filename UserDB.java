package M;

public class UserDB
{
	public int user_id;
	public String username;
	public String password;
	public String user_type;
	
	public UserDB() {}
	public UserDB(int xid, String xusername, String xpassword, String xusertype) {
		user_id = xid;
		username = xusername;
		password = xpassword;
		user_type = xusertype;
	}
}
