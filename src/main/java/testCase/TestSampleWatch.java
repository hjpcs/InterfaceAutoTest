package testCase;

import java.io.IOException;
import java.net.MalformedURLException;

import Common.JsonPath;
import Trigger.httpclient;
import net.sf.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by yuge.ly on 2017/6/7.
 */
public class TestSampleWatch {
    public  String  Url="https://www.sojson.com/open/api/weather/json.shtml";
    public  String  shWeatherWedu=null;
    public  String  shWeatherShidu=null;

    /**
     *
     * 前置步骤
     * 【步骤名称】:登录
     * @throws InterruptedException
     * @throws IOException
     */
    @BeforeMethod
    public void setup() throws Exception {
        String Parameter="city=上海";
        httpclient httpGet= new  httpclient();
        JSONObject json=httpGet.sendGet(Url,Parameter);
        shWeatherShidu=json.getJSONObject("data").getString("shidu");
        shWeatherWedu= json.getJSONObject("data").getString("wendu");
        Thread.sleep(5000);
    }

    @Test
    public void testMethod() throws Exception {
        String Parameter="city=北京";
        httpclient httpGet= new  httpclient();
        JSONObject json=httpGet.sendGet(Url,Parameter);
        com.jayway.jsonpath.JsonPath.read(json.toString(), "$..book[1].price");
        String BjWeatherWedu= JsonPath.JsonPath(json.toString(),"$..wendu");
        System.out.println(BjWeatherWedu);
        Assert.assertEquals(shWeatherWedu,BjWeatherWedu);

    }

    @Test(alwaysRun = true)
    public void testMethod1() throws Exception {
        String Parameter="city=北京";
        httpclient httpGet= new  httpclient();
        JSONObject json=httpGet.sendGet(Url,Parameter);
        String BjWeatherShidu= json.getJSONObject("data").getString("shidu");
        Assert.assertEquals(shWeatherShidu,BjWeatherShidu);

    }
    @AfterMethod(alwaysRun = true)
    public void quit() throws MalformedURLException {
        System.out.println("testMethod");
    }
}
