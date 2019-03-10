package testCase;

import java.io.IOException;
import java.net.MalformedURLException;

import Common.Commons;
import Common.JsonPath;
import Common.Log;
import Trigger.httpclient;
import junit.framework.Assert;
import net.sf.json.JSONObject;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class hiBug {
    public static int  version_id=628;
    public static int  module_id=724;
    public static String   project_id="453";
    public static String   project_strId="FM97uf9gQS0=";
    public static httpclient httpGet =new  httpclient();
    /**
     *
     * 前置步骤
     * 【步骤名称】:注册
     * @throws InterruptedException
     * @throws IOException
     */

    @BeforeClass
    public void setup() throws Exception {
        JSONObject json=Commons.login("979916060@qq.com","a5201314",httpGet);
        Assert.assertEquals(json.getString("message"),"登录成功");

    }



    @Test
    public void testCase() throws Exception {
        Log.logs("CaseName：登录创建Bug->搜索获取bugId->检查Bug是否创建");
        String bug_title="testBug-"+System.currentTimeMillis();
        String description ="test";
        JSONObject json= Commons.createBug( bug_title, version_id, module_id,  description , project_id,httpGet);
        if(json.getString("message").equals("添加成功")){
            Log.logs("response："+json.toString());
            JSONObject searchBug= Commons.searchBug( project_strId, bug_title, 0);
            Log.logs("response："+searchBug.toString());
            if(searchBug.get("data")!=null){
                Log.logs("Bug 创建成功 BugId："+ JsonPath.JsonPath(searchBug.toString(),"$.data[0].bug_id"));
                Log.logs("用例执行成功");
            }else{
                Log.logs("用例执行失败");
            }
        }else{
            Log.logs("用例执行失败");
        }
    }








    @Test
    public void editBug() throws Exception {
        Log.logs("CaseName：登录创建项目->搜索获取BugId->修改BugTittle->检查是否修复成功");
        String bug_title="testBug-"+System.currentTimeMillis();
        String description ="test";
        JSONObject json= Commons.createBug( bug_title, version_id, module_id,  description , project_id,httpGet);
        if(json.getString("message").equals("添加成功")){
            Log.logs("response："+json.toString());
            JSONObject searchBug= Commons.searchBug( project_strId, bug_title, 0);
            Log.logs("response："+searchBug.toString());
            if(searchBug.get("data")!=null){
                String bugId=JsonPath.JsonPath(searchBug.toString(),"$.data[0].bug_id");
                Log.logs("Bug 创建成功 BugId："+ JsonPath.JsonPath(searchBug.toString(),"$.data[0].bug_id"));
                String  EditBug_title="修改bug|"+System.currentTimeMillis();
                JSONObject editBug= Commons.editBug( EditBug_title, version_id, module_id,  description , project_id,bugId,httpGet);
                if(editBug.getString("message").equals("修改成功")) {
                    Log.logs("Bug 修改成功");
                    JSONObject searchEditBug= Commons.searchBug( project_strId, bug_title, 0);
                    Log.logs("response："+searchEditBug.toString());
                    if(searchBug.get("data")!=null){
                        Log.logs("Bug 创建成功 BugId："+ JsonPath.JsonPath(searchBug.toString(),"$.data[0].bug_id"));
                        Log.logs("用例执行成功");
                    }else{
                        Log.logs("用例执行失败");
                    }
                }
            }else{
                Log.logs("用例执行失败");
            }
        }else{
            Log.logs("用例执行失败");
        }
    }

    @AfterClass
    public void quit() throws MalformedURLException {
        //Commons.loginOut( httpGet);
    }



}
