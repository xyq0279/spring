package jt.redisLock;

public class CacheLockException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public CacheLockException(){
		
	}
	public CacheLockException(String msg){
		super(msg);
	}
}
