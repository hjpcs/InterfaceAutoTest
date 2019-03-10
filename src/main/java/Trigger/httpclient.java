package Trigger;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import net.sf.json.JSONObject;
import org.apache.http.HeaderIterator;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.cookie.CookieSpecProvider;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.impl.cookie.BestMatchSpecFactory;
import org.apache.http.impl.cookie.BrowserCompatSpecFactory;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class httpclient {

    public static CookieStore cookieStore = new BasicCookieStore();
    public CloseableHttpClient httpclient = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
    public static HttpClientContext context = null;

    /**
     * get请求
     * @param url
     * @param Parameter
     * @return
     * @throws Exception
     * @throws ClientProtocolException
     */
    public JSONObject sendGet(String url, String Parameter) throws Exception {
        StringBuffer responseStr=new StringBuffer();
        try {
            if(Parameter.isEmpty()==false){url=url + "?" + Parameter;}
            HttpGet httpget = new HttpGet(url);
            HttpResponse response = httpclient.execute(httpget);
            HttpEntity entity = response.getEntity();
            System.out.println("----------------------------------------");
            System.out.println(response.getStatusLine());
            if (entity != null) {
                System.out.println("Response content length: " + entity.getContentLength());
            }
            System.out.println("----------------------------------------");

            InputStream inSm = entity.getContent();
            Scanner inScn = new Scanner(inSm,"utf-8");
            while (inScn.hasNextLine()) {
                responseStr.append(inScn.nextLine().toString());
            }

            httpget.abort();
        } finally {

           // httpclient.getConnectionManager().shutdown();
        }
        //System.out.println("Response:");
       // System.out.println("----------------------------------------");
        //System.out.println(responseStr.toString());
        //System.out.println("----------------------------------------");
        return JSONObject.fromObject(responseStr.toString().trim());
    }


    /**
     * 用post请求
     * @param url
     * @param parameter
     * @return
     */

public JSONObject sendPost(String url, String parameter)  {
    String Response =new String();
    HttpPost httpPost = new HttpPost(url);
    List<NameValuePair> formParams = new ArrayList<NameValuePair>();
    FormatProcessing(formParams,parameter);
    UrlEncodedFormEntity urlEncodedFormEntity;
    try {
        urlEncodedFormEntity = new UrlEncodedFormEntity(formParams, "UTF-8");
        httpPost.setEntity(urlEncodedFormEntity);
        httpPost.setHeader("Content-Type","application/x-www-form-urlencoded");
        System.out.println("execurting request:" + httpPost.getURI()+"\nparameter:"+parameter);
        HttpResponse httpResponse = null;
        httpResponse = httpclient.execute(httpPost);
        HttpEntity httpEntity = httpResponse.getEntity();
        if (httpEntity != null) {
            String content = EntityUtils.toString(httpEntity, "UTF-8");
            Response=content;
        }
        printResponse(httpResponse);
        setContext();
        setCookieStore(httpResponse);
    } catch (ClientProtocolException e) {
        e.printStackTrace();
    } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        //关闭连接，释放资源
    }
    System.out.println("Response:");
    System.out.println("----------------------------------------");
    System.out.println(Response.toString());
    System.out.println("----------------------------------------");
    return JSONObject.fromObject(Response.toString().trim());
}

    /**
     * Post 入参转换成List
     * @param formParams
     * @param parameter
     */
    public static  void  FormatProcessing (List<NameValuePair> formParams, String parameter){
        try{
        String value[] =parameter.split("&");
        for(int i=0;i<value.length;i++){
            String keyword[]=value[i].split("=");
            formParams.add(new BasicNameValuePair(keyword[0], keyword[1]));
        }}catch(Exception e){
            e.printStackTrace();
            System.out.println("入参转换失败");
        }

    }

    /**
     * 打印httpResponse
     * @param httpResponse
     * @throws ParseException
     * @throws IOException
     */
    public static void printResponse(HttpResponse httpResponse) throws ParseException, IOException {
        HttpEntity entity = httpResponse.getEntity();
        System.out.println("status:" + httpResponse.getStatusLine());
        HeaderIterator iterator = httpResponse.headerIterator();
        while (iterator.hasNext()) {
            System.out.println("\t" + iterator.next());
        }
        if (entity != null) {
            try {
                String responseString = EntityUtils.toString(entity, "UTF-8");
            }catch(Exception E){}

        }
    }


    /**
     * 保留cookie
     * @param httpResponse
     */
    public static void setCookieStore(HttpResponse httpResponse) {
        String setCookie =new String();
        if(httpResponse.getFirstHeader("Set-Cookie")!=null) {
             setCookie = httpResponse.getFirstHeader("Set-Cookie").getValue();
        }
        String JSESSIONID =new String();
        if(setCookie.isEmpty()==false) {
             JSESSIONID = setCookie.substring("JSESSIONID=".length(),
                    setCookie.indexOf(";"));
        }
        // 新建一个Cookie
        BasicClientCookie cookie = new BasicClientCookie("JSESSIONID",
                JSESSIONID);
        cookie.setVersion(0);
        cookie.setDomain("127.0.0.1");
        cookie.setPath("/CwlProClient");
        cookieStore.addCookie(cookie);
    }


    /**
     * 设置Context
     */
    public static void setContext() {
        context = HttpClientContext.create();
        Registry<CookieSpecProvider> registry = RegistryBuilder
                .<CookieSpecProvider> create()
                .register(CookieSpecs.BEST_MATCH, new BestMatchSpecFactory())
                .register(CookieSpecs.BROWSER_COMPATIBILITY,
                        new BrowserCompatSpecFactory()).build();
        context.setCookieSpecRegistry(registry);
        context.setCookieStore(cookieStore);
    }


    /**
     * tmain
     * @param args
     * @throws Exception
     */
    public  static void main(String[] args) throws Exception {
        Trigger.httpclient api=new httpclient();
        String url ="http://30.17.1.73:8080/APIInterface/GetUserList";
        String data="page=1&limit=3";
        System.out.println("response:"+api.sendGet(url,data));
        /* String url ="http://30.17.1.73:8080/APIInterface/InsertProject";
        String data="name=test&remark=test&createuser=test&type=1";
        System.out.println(api.sendPost(url,data));*/
    }

}
