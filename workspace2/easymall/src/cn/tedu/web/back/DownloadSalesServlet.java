package cn.tedu.web.back;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.Utils.TimeUtils;
import cn.tedu.domain.SaleInfo;
import cn.tedu.factory.BasicFactory;
import cn.tedu.service.OrderService;

public class DownloadSalesServlet extends HttpServlet {
	private OrderService service = BasicFactory.getFactory().getInstance(OrderService.class);
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<SaleInfo> list = service.findSaleInfo();
		String result = "商品id,商品名称,销售数量\n";
		for(SaleInfo si:list){
			result+=si;
		}
		response.setCharacterEncoding("gbk");
		
		response.setHeader("Content-Disposition", "attachment;filename="+
		TimeUtils.getTimeStamp("yyyy-MM-dd  hh-MM-ss")+".csv");
		response.getWriter().write(result); 
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
