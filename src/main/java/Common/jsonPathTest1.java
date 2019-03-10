package Common;

import java.util.Collections;
import java.util.List;

import Trigger.httpclient;
import com.jayway.jsonpath.JsonPath;
import net.sf.json.JSONObject;
/**
 * Created by liyu on 2018/9/1.
 */
public class jsonPathTest1 {
    public static  String  Url="http://api.douban.com/v2/movie/top250";

    public static void main (String []args) throws Exception {
        String Parameter="start=1&count=250";
        httpclient httpGet= new  httpclient();
        JSONObject json=httpGet.sendGet(Url,Parameter);
        List<Integer> count=JsonPath.read(json.toString(), "$..collect_count");
        System.out.println("最大值："+ Collections.max(count));
        List<String> result=JsonPath.read(json.toString(), "$.subjects[?(@.collect_count == '"+Collections.max(count)+"')]");
        System.out.println("result："+ result);
    }

}
