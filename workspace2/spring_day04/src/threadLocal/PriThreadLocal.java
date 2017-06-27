package threadLocal;

import java.util.List;



public class PriThreadLocal {
	
	
	private static ThreadLocal<List<String>> threadLocal = new ThreadLocal<List<String>>();

	public static List<String> getThreadLocal() {
		return threadLocal.get();
	}

	public static void setThreadLocal(List<String> list) {
		threadLocal.set(list);
	}
	
	
}
