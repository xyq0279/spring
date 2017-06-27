package tran;

import org.springframework.stereotype.Component;

@Component
public class TranManager {
	
	public void begin(){
		System.out.println("开启事务");
	}
	public void commit(){
		System.out.println("提交事务");
	}
	public void rollback(){
		System.out.println("回滚");
	}
}
