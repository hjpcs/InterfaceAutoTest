package Common;

import java.io.IOException;

public class JsonPath {

	public static  void  main(String[]args) throws IOException {
	String jsonStr="{\"store\":{\"book\":[{\"category\":\"reference\",\"author\":\"Nigel Rees\",\"title\":\"Sayings of the Century\",\"price\":8.95},{\"category\":\"fiction\",\"author\":\"Evelyn Waugh\",\"title\":\"Sword of Honour\",\"price\":12.99},{\"category\":\"fiction1\",\"author\":\"Herman Melville\",\"title\":\"Moby Dick\",\"isbn\":\"0-553-21311-3\",\"price\":8.99},{\"category\":\"fiction23\",\"author\":\"32J. R. R. Tolkien\",\"title\":\"The Lord of the Rings\",\"isbn\":\"0-395-19395-8\",\"price\":22.99}],\"bicycle\":{\"color\":\"red\",\"price\":19.95}}}";
		System.out.println(jsonStr);
		JsonPath json=new   JsonPath();
		String expression1="$..price";//获取所有节点下的price
		String expression2="$..book[1].price";//获取所有书籍里的第二个价格
		String expression3="$.store.book[?(@.category == 'reference')]";//获取Book category 为reference 的书籍
		String expression4="$.store.book[?(@.isbn)]";//获取有 isbn 的书籍
		String expression5="$.store.book[?(@.price > 10)]";//获取Book 价格大于10 的书籍
		String expression6="$..book[-1:]";//获取最后一本书
		String expression10="$..book[-2:]";//获取最后两本书
		String expression7="$.store.book[0].title";//获取第一本书的书名
		String expression8="$.store.bicycle.color";//获取自动车的颜色
		String expression9="$.store.book[*].author";//获取所有的书本作者

		System.out.println("获取所有节点下的price:"+com.jayway.jsonpath.JsonPath.read(json, "$..price[0]"));

	/*	System.out.println("获取所有节点下的price:"+json.JsonPath(jsonStr,expression1));
		System.out.println("获取所有书籍里的第二个价格:"+json.JsonPath(jsonStr,expression2));
		System.out.println("获取Book category 为reference 的书籍:"+json.JsonPath(jsonStr,expression3));
		System.out.println("获取有 isbn 的书籍:"+json.JsonPath(jsonStr,expression4));
		System.out.println("获取Book 价格大于10 的书籍:"+json.JsonPath(jsonStr,expression5));
		System.out.println("获取最后一本书:"+json.JsonPath(jsonStr,expression6));
		System.out.println("获取最后两本书:"+json.JsonPath(jsonStr,expression7));
		System.out.println("获取第一本书的书名:"+json.JsonPath(jsonStr,expression8));
		System.out.println("获取自动车的颜色:"+json.JsonPath(jsonStr,expression9));
		System.out.println("获取所有的书本作者:"+json.JsonPath(jsonStr,expression10));*/
}

public static String JsonPath(String json, String expression ) throws IOException {
	String GetValue=new String();

	try{
		if(json!=null&&expression!=null) {
			GetValue= com.jayway.jsonpath.JsonPath.read(json, expression).toString();
			if(GetValue.contains("[")){
				GetValue=GetValue.substring(GetValue.indexOf("[")+1,GetValue.length()-1);
			}
		}
		}catch(Exception e){
		e.printStackTrace();
		System.out.println("JsonPath取值失败：");
	}

	return GetValue.replaceAll("\"","");
}

}
