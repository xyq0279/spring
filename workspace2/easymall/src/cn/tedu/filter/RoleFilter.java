package cn.tedu.filter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.domain.User;

/**
 * Servlet Filter implementation class RoleFilter
 */
public class RoleFilter implements Filter {

    
    //声明相应权限可访问页面的list集合
    private List<String> userList = null;
    private List<String> adminList = null;
    
    /**
     * 初始化
     */
    public void init(FilterConfig fConfig) throws ServletException {
    	userList = new ArrayList<String>();
        adminList = new ArrayList<String>();
    	String path = fConfig.getServletContext().getRealPath(File.separator+"WEB-INF"+File.separator+"user.txt");
    	try {
			BufferedReader br = new BufferedReader(new FileReader(new File(path)));
			String len = null;
			while((len=br.readLine())!=null){
				userList.add(len);
			}
			path=fConfig.getServletContext().getRealPath(File.separator+"WEB-INF"+File.separator+"admin.txt");
			br = new BufferedReader(new FileReader(new File(path)));
			while((len=br.readLine())!=null){
				adminList.add(len);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
    }

	/**
	 * 权限过滤
	 */
	public void doFilter(ServletRequest request, ServletResponse response, 
			FilterChain chain) throws IOException, ServletException {
		//强制类型转换
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		String uri = req.getRequestURI();
		if(userList.contains(uri)||adminList.contains(uri)){//需要权限
			//判断用户是否登录
			Object object = req.getSession().getAttribute("user");
			if(object==null){
				req.getRequestDispatcher("/login.jsp");
			}
			User user = (User) object;
			//获得用户权限
			String role = user.getRole();
			//判断用户权限是否可以访问该页面
			if("user".equals(role)&userList.contains(uri)){
				chain.doFilter(req, resp);
			}else if("admin".equals(role)&adminList.contains(uri)){
				chain.doFilter(req, resp);
			}else{
				resp.getWriter().write("您的权限不足，请联系客服或管理员");
			}
		}else{
			//无须权限
			chain.doFilter(req, resp);
		}
	}	
	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}


}
