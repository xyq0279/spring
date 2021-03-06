package jt.redis;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import org.junit.Test;

public class TestRedisPipe {
	
	//格式化输入字符
	private String getString(String... args){
		StringBuilder sb = new StringBuilder();
		sb.append("*").append(args.length).append("\r\n");
		for (String str : args) {
			sb.append("$").append(str.length()).append("\r\n").append(str).append("\r\n");
		}
		return sb.toString();
	}
	
	@Test
	public void test01(){
		Long startTime = System.currentTimeMillis();
		String file = "d:\\d.txt";
		BufferedWriter w = null;
		StringBuilder sb = new StringBuilder();
		try {
			w = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));
			for(int i=100000000 ;i < 100100000;i++){
			//for (int i = 1; i <= 100; i++) {
				if (i / 30000 == 0) {
					w.flush();
				}
				sb.setLength(0);
				sb.append(this.getString("set", "u" + i, "name" + i));
//sb.append(this.getString("hmset", "usr" + i, "userid", "usr" + i, "username", "usrname" + i));
				w.append(sb.toString());
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				w.flush();
				w.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		long endTime = System.currentTimeMillis();
		System.out.println("耗时: "+(endTime - startTime)/1000+" s。");
	}

}
