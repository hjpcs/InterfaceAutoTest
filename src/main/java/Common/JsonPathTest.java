package Common;

import java.io.IOException;
import java.util.List;

import com.jayway.jsonpath.JsonPath;
public class JsonPathTest {

	public static  void  main(String[]args) throws IOException {
		String jsonStr
			= "{\"store\":{\"book\":[{\"category\":\"reference\",\"author\":\"Nigel Rees\",\"title\":\"Sayings of the Century\",\"price\":8.95},{\"category\":\"fiction\",\"author\":\"Evelyn Waugh\",\"title\":\"Sword of Honour\",\"price\":12.99},{\"category\":\"fiction1\",\"author\":\"Herman Melville\",\"title\":\"Moby Dick\",\"isbn\":\"0-553-21311-3\",\"price\":8.99},{\"category\":\"fiction23\",\"author\":\"32J. R. R. Tolkien\",\"title\":\"The Lord of the Rings\",\"isbn\":\"0-395-19395-8\",\"price\":22.99}],\"bicycle\":{\"color\":\"red\",\"price\":19.95}}}";
		System.out.println(jsonStr);

		System.out.println("获取所有节点下的price:" + JsonPath.read(jsonStr, "$..price"));
		List<Double> priceList=JsonPath.read(jsonStr, "$..price");
		System.out.println("获取所有节点下的price  第一个价格:" + priceList.get(0));

		System.out.println("获取所有书籍里的第二个价格:" + JsonPath.read(jsonStr, "$..book[1].price"));
		System.out.println("获取自动车的颜色:"+JsonPath.read(jsonStr, "$.store.bicycle.color"));
		System.out.println("获取所有的书本作者:"+JsonPath.read(jsonStr, "$.store.book[*].author"));

		//条件查找：
		System.out.println("获取Book 价格大于10 的书籍:" + JsonPath.read(jsonStr, "$.store.book[?(@.price > 10)]"));
		System.out.println("获取Book category 为reference 的书籍:"+JsonPath.read(jsonStr,"$.store.book[?(@.category == 'reference')]"));

		System.out.println("获取Book category 为reference 的书籍第一本的书名？:"+
			JsonPath.read(jsonStr,"$.store.book[?(@.category == 'reference')].category"));

	}

}
