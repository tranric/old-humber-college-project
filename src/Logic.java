import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Anirudh
 *
 */
public class Logic {
	/**
	 * Connection Element
	 */
	protected Database database;
	
	/**
	 * Logic Class Constructor
	 */
	public Logic()
	{
		database = new Database("anirudh.ddns.net", 3306, "project_humber", "nirav", "nish8099");
	}
	/**
	 * This Method returns authentication result. It requires username and passowrd and tells if the user exists or not
	 * @param username
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public boolean authenticate(String username, String password) throws Exception
	{
		String uname = username.trim().toUpperCase().toString();
		String pass = password.trim().toUpperCase().toString();
		try
		{
			String query = String.format(Query.AUTHENTICATE.toString(), uname, pass);
			ArrayList<HashMap<String,String>> data = database.execute(query);
			if(data.isEmpty())
			{
				throw new Exception("No user found with that username!");
			}
			else if(data.size() == 1)
			{
				return true;
			}
			else
			{
				for(int i = 0 ; i < data.size() ; i++)
				{
					if(data.get(i).get("USERNAME").equals(uname) && data.get(i).get("PASSWORD").equals(pass))
					{
						return true;
					}
				}
			}
		}
		catch(Exception ex)
		{
			throw ex;
		}
		return false;
	}
	/**
	 * This Method get all info such as username, role, password, firstname and lastname
	 * @param username
	 * @return HashMap<String,String>
	 * @throws Exception
	 */
	public HashMap<String, String> get_info(String username) throws Exception
	{
		HashMap<String, String> map = new HashMap<String,String>();
		String uname = username.trim().toUpperCase().toString();
		try
		{
			String query = String.format(Query.SELECT_ALL_FROM.toString(), "USER", uname);
			ArrayList<HashMap<String,String>> data = database.execute(query);
			if(data.isEmpty())
			{
				throw new Exception("No user found with that username or password!");
			}
			else
			{
				for(int i = 0 ; i < data.size() ; i++)
				{
					if(data.get(i).get("USERNAME").equals(uname))
					{
						map.put("USERNAME", data.get(i).get("USERNAME"));
						map.put("ROLE", data.get(i).get("ROLE"));
						
						/*Getting First Name and Last Name*/
						query = String.format(Query.SELECT_ALL_FROM.toString(), data.get(i).get("ROLE") , uname);
						data = database.execute(query);
						if(!data.isEmpty())
						{
							for(int j=0; j < data.size(); j++)
							{
								if(data.get(i).get("USERNAME").equals(uname))
								{
									map.put("FIRSTNAME",data.get(i).get("FIRSTNAME"));
									map.put("LASTNAME", data.get(i).get("LASTNAME"));
									return map;
								}
							}
						}
					}
				}
			}
		}
		catch(Exception ex)
		{
			throw ex;
		}
		return null;
	}
	
	
	public String get_student_exams(String username) throws Exception
	{
		String uname = username.toUpperCase().trim();
		String data = " ";
		try {
			String query = String.format(Query.SELECT_EXAMS.toString(), uname);
			ArrayList<HashMap<String,String>> courses = database.execute(query);
			if(courses.size() != 0)
			{
				for(int i=0 ; i < courses.size(); i++)
				{
					data += String.format("<tr> <th>%s</th> <th>%s</th> <th>%s</th> <th>%s</th> <th>%s</th> </tr>",
							courses.get(i).get("COURSE_CODE").toString(),courses.get(i).get("EXAM_DATE").toString(),
							courses.get(i).get("START_TIME").toString(),courses.get(i).get("END_TIME").toString(),courses.get(i).get("ROOM_NO").toString());
				}
			}
		} 
		catch (Exception e) {
			throw e;
		}
		if(data == " ")
		{
			data = new Display(Display.Type.INFO).getHtml("You have no exam scheduled yet! Please Check Back Later!");
		}
		return data;
	}
	
	
}
