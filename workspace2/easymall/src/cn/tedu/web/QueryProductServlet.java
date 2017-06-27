package cn.tedu.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.Utils.webUtils;
import cn.tedu.domain.Product;
import cn.tedu.factory.BasicFactory;
import cn.tedu.service.ProdListService;

public class QueryProductServlet extends HttpServlet {
	private ProdListService service = BasicFactory.getFactory().getInstance(ProdListService.class);
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//
		String name = request.getParameter("name");
		String category = request.getParameter("category");
		String minprice = request.getParameter("minprice");
		String maxprice = request.getParameter("maxprice");
		//
		String _name = "";
		String _category = "";
		double _minprice = 0;
		double _maxprice = Double.MAX_VALUE;
		
		
		if(!webUtils.isNull(name)){
			_name = name;
		}
		if(!webUtils.isNull(category)){
			_category = category;
		}
		if(!webUtils.isNull(minprice)&&Double.parseDouble(minprice)>=0){
			
			_minprice = Double.parseDouble(minprice);
		}
		if(!webUtils.isNull(maxprice)&&Double.parseDouble(maxprice)<=_maxprice&&_minprice<=Double.parseDouble(maxprice)){
			_maxprice = Double.parseDouble(maxprice);
		}

		
		List<Product> list = service.findProduct(_name,_category,_minprice,_maxprice);
		request.setAttribute("list", list);
		request.getRequestDispatcher("/prod_list.jsp").forward(request, response);
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
