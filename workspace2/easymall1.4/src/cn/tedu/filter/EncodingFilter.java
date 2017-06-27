package cn.tedu.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class EncodingFilter implements Filter{

	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("全站乱码解决过滤器初始化成功...");
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		//1、解决响应正文乱码
		response.setContentType("text/html;charset=utf-8");
		
		//2、
		HttpServletRequest myReq = new MyHttpServletRequest((HttpServletRequest) request);
		//3、放行过滤器
		chain.doFilter(myReq, response);
	}

	public void destroy() {
		
	}

}

class MyHttpServletRequest extends HttpServletRequestWrapper{
	private HttpServletRequest request;
	private boolean flag = true;
	public MyHttpServletRequest(HttpServletRequest request) {
		super(request);
		this.request = request;
		
		// TODO Auto-generated constructor stub
	}
	@Override
	public String getParameter(String name) {
		// TODO Auto-generated method stub
		return getParameterMap().get(name)==null? null :( (String[]) getParameterMap().get(name))[0];
	}
	@Override
	public String[] getParameterValues(String name) {
		// TODO Auto-generated method stub
		return (String[]) getParameterMap().get(name);
	}
	@Override
	public Map getParameterMap() {
		try {
			String method = request.getMethod();
			if("POST".equals(method)){
				request.setCharacterEncoding("utf-8");
				return request.getParameterMap();
			}else if("GET".equals(method)){
				Map<String, String[]> map = request.getParameterMap();
				if(flag){
					for(Map.Entry<String, String[]> entry: map.entrySet()){
						String[] vs = entry.getValue();
						for(int i = 0;i<vs.length;i++){
							vs[i] = new String(vs[i].getBytes("iso8859-1"),"utf-8");
						}
					}
					flag = false;
				}
				return map;
			}
			// TODO Auto-generated method stub
			return super.getParameterMap();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
