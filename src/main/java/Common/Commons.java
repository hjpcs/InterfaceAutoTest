package Common;

import Trigger.httpclient;
import net.sf.json.JSONObject;

public class Commons {
	private String url="";
	
	public String geturl() {
		return url;
	}
	public static double streamDouble(double pixRate) {
		double newPixRate = (double)Math.round(pixRate * 100) / 100;
		return newPixRate;
	}
	/**
	 *
	 * @param bug_title
	 * @param version_id
	 * @param module_id
	 * @param description
	 * @param project_id
     * @return
     * @throws Exception
     */
	public static  JSONObject createBug(String bug_title,int version_id,int module_id,String description ,String project_id,httpclient httpGet)  {
		String Url="http://www.hibug.cn/api/ebugs/project-bug/save-bug.do";
		String Parameter="bug_title="+bug_title+"&version_id="+version_id+"&module_id="+module_id+"&priority=1&handle_member_id=610&description="+description+"&project_id="+project_id;
		System.out.println(Parameter);
		JSONObject json=httpGet.sendPost(Url,Parameter);
		return json;
	}




	/**
	 *
	 * @param bug_title
	 * @param version_id
	 * @param module_id
	 * @param description
	 * @param project_id
	 * @return
	 * @throws Exception
	 */
	public static  JSONObject editBug(String bug_title,int version_id,int module_id,String description ,String project_id,String bug_id,httpclient httpGet)  {
		String Url="http://www.hibug.cn/api/ebugs/project-bug/edit-bug.do";
		String Parameter="bug_title="+bug_title+"&version_id="+version_id+"&module_id="+module_id+"&priority=1&description="+description+"bug_id="+bug_id+"&pid="+project_id;
		JSONObject json=httpGet.sendPost(Url,Parameter);
		return json;
	}





	/**
	 *
	 * @return
	 * @throws Exception
	 */
	public static  JSONObject getBugList(String projectId) throws Exception {
		String Url="http://www.hibug.cn/api/ebugs/project-bug/get-my-create.do";
		String Parameter="projectId="+projectId;
		httpclient httpGet= new  httpclient();
		JSONObject json=httpGet.sendGet(Url,Parameter);
		return json;
	}


	/**
	 *
	 * @return
	 * @throws Exception
	 */
	public static  JSONObject getAssignorme(String projectId) throws Exception {
		String Url="http://www.hibug.cn/api/ebugs/project-bug/get-assign-for-me.do";
		String Parameter="projectId="+projectId;
		httpclient httpGet= new  httpclient();
		JSONObject json=httpGet.sendGet(Url,Parameter);
		return json;
	}


	/**
	 *
	 * @return
	 * @throws Exception
	 */
	public static  JSONObject searchBug(String projectId,String keyword,int last_status) throws Exception {
		String Url="http://www.hibug.cn/api/ebugs/project-bug/get-all-bug.do";
		String Parameter="projectId="+projectId+"&keyword="+keyword+"&priority=0&last_status="+last_status+"&create_member_id=0&handle_member_id=0&module_id=0&version_id=0";
		System.out.println(Parameter);
		httpclient httpGet= new  httpclient();
		JSONObject json=httpGet.sendGet(Url,Parameter);
		return json;
	}

	/**
	 *
	 * @param status
	 * @param handle_id
	 * @param description
	 * @param BugId
	 * @param assign_id
	 * @return
     * @throws Exception
     */
	public static  JSONObject updateBugState(String status,String handle_id,String description,int  BugId,int  assign_id) {
		String Url="http://www.hibug.cn/api/ebugs/project-bug/assign.do";
		String Parameter="status="+status+"&handle_id="+handle_id+"&description="+description+"editor1=&bug_id="+BugId+"&assign_id="+assign_id;
		httpclient httpGet= new  httpclient();
		JSONObject json=httpGet.sendPost(Url,Parameter);
		return json;
	}



	/**
	 *
	 * @return
	 * @throws Exception
	 */
	public static  JSONObject addVersion(String project_id,String  version_name)  {
		String Url="http://www.hibug.cn/api/ebugs/project/add-version.do";
		String Parameter="project_id="+project_id+"&version_name="+version_name;
		httpclient httpGet= new  httpclient();
		JSONObject json=httpGet.sendPost(Url,Parameter);
		return json;
	}






	/**
	 *
	 * @return
	 * @throws Exception
	 */
	public static  JSONObject login(String email,String password,httpclient httpGet)  {
		String Url="http://www.hibug.cn/api/ebugs/member/login.do";
		String Parameter="email="+email+"&password="+password;
		JSONObject json=httpGet.sendPost(Url,Parameter);
		return json;
	}


	/**
	 *
	 * @return
	 * @throws Exception
	 */
	public static  JSONObject loginOut(httpclient httpGet) throws Exception {
		String Url=" http://www.hibug.cn/api/ebugs/member/logout.do";
		String Parameter="";
		JSONObject json=httpGet.sendGet(Url,Parameter);
		return json;
	}
}
