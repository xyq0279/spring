package cn.tedu.Filter;

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

public class EncodingFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		response.setContentType("text/html;charset=utf-8");
		
//		request.setCharacterEncoding("utf-8");
//		
//		String name = request.getParameter("username");
//		
//		System.out.println("username1:"+name);
		
		HttpServletRequest myHttServletRequest = new MyHttServletRequest((HttpServletRequest) request);
		
		chain.doFilter(myHttServletRequest, response);
		
	}
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}
	@Override
	public void destroy() {

	}

}

class MyHttServletRequest extends HttpServletRequestWrapper{
	private HttpServletRequest request ;
	private boolean flag=true;
	public MyHttServletRequest(HttpServletRequest request) {
		super(request);
		this.request=request;
		// TODO Auto-generated constructor stub
	}
	@Override
	public String getParameter(String name) {
		// TODO Auto-generated method stub
		return getParameterMap().get(name)==null? null : ((String[])getParameterMap().get(name))[0];
	}
	@Override
	public String[] getParameterValues(String name) {
		// TODO Auto-generated method stub
		return (String[]) getParameterMap().get(name);
	}
	@Override
	public Map getParameterMap() {
		try {
			//获取并判断请求方式
			String method = request.getMethod();
			if("POST".equals(method)){
				request.setCharacterEncoding("utf-8");
				return request.getParameterMap();
			}else if("GET".equals(method)){
				//遍历整个map，取出每一个请求参数，手动编码解决乱码问题
				Map<String, String[]> map = request.getParameterMap();
				if(flag){
					for(Map.Entry<String, String[]> entry : map.entrySet()){
						String[] vs = entry.getValue();
						for(int i =0;i<vs.length;i++){
							vs[i]= new String(vs[i].getBytes("iso8859-1"),"utf-8");
						}
					}
					flag=false;
				}
				return map;
			}
			return super.getParameterMap();
			// TODO Auto-generated method stub
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	
}


