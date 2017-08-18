package jt.jsoup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.junit.Test;

public class TestJsoup {
	@Test
	public List<String> getAllLevel3Url() throws IOException{
		List<String> list = new ArrayList<String>();
		String url = "";
		Jsoup.connect(url).get().select("");
		return list;
	}
}
