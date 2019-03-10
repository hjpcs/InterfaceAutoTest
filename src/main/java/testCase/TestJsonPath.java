package testCase;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

import Common.JsonPath;
import Trigger.httpclient;
import net.sf.json.JSONObject;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author yuge.ly
 * @date 2017/6/7
 */
public class TestJsonPath {
    public  String  Url="https://www.sojson.com/open/api/weather/json.shtml";


    @BeforeMethod
    public void setup() throws Exception {
       /* String Parameter="city=上海";
        String str="high: 高温 32.0℃\",";
        httpclient httpGet= new  httpclient();
        JSONObject json=httpGet.sendGet(Url,Parameter);
        System.out.println(str.substring(str.indexOf("温")+2,str.indexOf("℃")));
        System.out.println(com.jayway.jsonpath.JsonPath.read(json, "$..wendu").toString());;
        System.out.println(JsonPath.JsonPath(json.toString(), "$..wendu").toString());;*/


    }



    @Test
    public void testMethodTwo() throws Exception {
        ArrayList<Double> List=new  ArrayList<Double>();
        ArrayList<JSONObject> JsonList=new  ArrayList<JSONObject>();
        String [] cityList= new String[] {"上海", "北京", "广州", "深圳"};
        for (int i=0;i<cityList.length;i++){
            httpclient httpGet= new  httpclient();
            String Parameter="city="+cityList[i];
            JSONObject json=httpGet.sendGet(Url,Parameter);
            String jsonStr= JsonPath.JsonPath(json.toString(), "$.data.yesterday.high").toString();
            System.out.println(cityList[i]+"昨日最高温度:"+jsonStr);
            String high=jsonStr.substring(jsonStr.indexOf("温")+2,jsonStr.indexOf("℃"));
            List.add(Double.parseDouble(high));
            JsonList.add(json);
            Thread.sleep(4000);
        }
        /**
         * 排序算法
         */
        BubbleSort(List);
        /**
         * 温度城市排序打印
         */
        for (int i=0;i<List.size();i++){
          String city=getCity(List.get(i), JsonList);
          System.out.println("城市："+city+"|昨日最高温度："+List.get(i)+"|排名："+(i+1));
        }

    }

    @AfterMethod(alwaysRun = true)
    public void quit() throws MalformedURLException {
    }

    /**
     * 排序算法
     * @param list
     * @return
     */
    public static ArrayList<Double>  BubbleSort(ArrayList<Double> list) {
           System.out.println("排序前数组为：" + list.toString());
           for (int i = 0; i < list.size() - 1; i++) {//外层循环控制排序趟数
               for (int j = 0; j < list.size() - 1 - i; j++) {//内层循环控制每一趟排序多少次
                   if (list.get(j) < list.get(j + 1)) {
                       Double temp = list.get(j);
                       list.set(j, list.get(j + 1));
                       list.set(j + 1, temp);
                   }
               }
           }
           System.out.println();
           System.out.println("排序后的数组为：" + list.toString());
           return list;

       }

    /**
     * 根据温度获取城市
     * @param wendu
     * @param JsonList
     * @return
     * @throws IOException
     */
        public static String  getCity(Double wendu, ArrayList<JSONObject> JsonList) throws IOException {
            String city="";
            for (int i=0;i<JsonList.size();i++){
                String jsonStr=JsonPath.JsonPath(JsonList.get(i).toString(), "$.data.yesterday.high").toString();
                if(jsonStr.contains(String.valueOf(wendu))){
                    city=JsonPath.JsonPath(JsonList.get(i).toString(), "$.city").toString();
                    JsonList.remove(i);
                    break;
                }

            }
            return city;

        }

    }

