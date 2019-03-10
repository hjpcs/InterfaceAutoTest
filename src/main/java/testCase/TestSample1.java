package testCase;

import java.net.MalformedURLException;
import java.util.List;

import Trigger.httpclient;
import net.sf.json.JSONObject;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
/**
 * Created by yuge.ly on 2017/6/7.
 */
public class TestSample1 {
    public  String  Url="https://www.sojson.com/open/api/weather/json.shtml";
    public  String  shWeatherWedu=null;
    public  String  shWeatherShidu=null;



    @Test
    public void testMethod() throws Exception {

        String Parameter="city=北京";
        httpclient httpGet= new  httpclient();
        JSONObject json=httpGet.sendGet(Url,Parameter);
        List<String> value= com.jayway.jsonpath.JsonPath.read(json.toString(), "$..data.shidu");
        System.out.println(value.get(0));
    }


    @AfterMethod(alwaysRun = true)
    public void quit() throws MalformedURLException {
        System.out.println("testMethod");
    }
}
