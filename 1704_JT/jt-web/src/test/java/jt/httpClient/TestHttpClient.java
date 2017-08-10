package jt.httpClient;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class TestHttpClient {
	@Test	//模拟一个get
	public void get() throws ClientProtocolException, IOException{
		//1.创建http对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		//2.模拟get请求
		String url = "http://manage.jt.com/web/itemcat/all";	//抓取网页
		HttpGet get = new HttpGet(url);
		//3.获取一个返回的对象
		CloseableHttpResponse response = httpClient.execute(get);	//执行发起一个http请求
		//4.通过工具类转成成一个字符串，字符串就是网页源文件
		String html = EntityUtils.toString(response.getEntity(), "utf-8");
		System.out.println(html);
	}

}
