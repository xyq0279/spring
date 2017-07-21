package cn.tarena.ht.tool;

import org.apache.shiro.crypto.hash.Md5Hash;

public class Md5Password {
	
	public static String getMd5HashPassword(String password, String username) {
		Md5Hash md5 = new Md5Hash(password, username, 4);
		return md5.toString();
	}
	
	
/*	public static void main(String[] args) {
	String password = "123";
	

//	 1.source 明文
//	 2.salt   盐
//	 3.hashIterations  哈希的次数
	 
	Md5Hash md5Hash = new Md5Hash(password, "飞扬的张", 4);
	System.out.println(md5Hash);
	}*/
}
