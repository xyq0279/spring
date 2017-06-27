package cn.tedu.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.Utils.PaymentUtil;
import cn.tedu.domain.Order;
import cn.tedu.factory.BasicFactory;
import cn.tedu.service.OrderService;

public class PayServlet extends HttpServlet {
//	private OrderService service = BasicFactory.getFactory().getInstance(OrderService.class);
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获得订单ID
		String oid = request.getParameter("orderid");
//		Order order =  service.findOrderByOid(oid);
		//准备第三方所需要的参数
		String p0_Cmd = "Buy";//业务类型
		String p1_MerId = PaymentUtil.getPro("p1_MerId");//商户编号
		String p2_Order = oid;//订单ID
		//String p3_Amt = order.getMoney()+"";//订单金额
		String p3_Amt = "0.01";
		String p4_Cur = "CNY";//交易货币
		String p5_Pid = "";//商品ID
		String p6_Pcat = "";//商品种类
		String p7_Pdesc = "";//商品描述
		String p8_Url = PaymentUtil.getPro("responseURL");//商户接受支付成功数据的地址
		String p9_SAF = "";//送货地址
		String pa_MP = "";//商户拓展信息
		String pd_FrpId = request.getParameter("pd_FrpId");//支付通道编码
		String pr_NeedResponse = "1";//应答机制
		//加密
		//签名数据
		String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt, p4_Cur, p5_Pid,p6_Pcat, 
				p7_Pdesc, p8_Url, p9_SAF, pa_MP, pd_FrpId, pr_NeedResponse, PaymentUtil.getPro("keyValue"));
		
		//将这些数据添加到request域中
		request.setAttribute("p0_Cmd", p0_Cmd);
		request.setAttribute("p1_MerId", p1_MerId);
		request.setAttribute("p2_Order", p2_Order);
		request.setAttribute("p3_Amt", p3_Amt);
		request.setAttribute("p4_Cur", p4_Cur);
		request.setAttribute("p5_Pid", p5_Pid);
		request.setAttribute("p6_Pcat", p6_Pcat);
		request.setAttribute("p7_Pdesc", p7_Pdesc);
		request.setAttribute("p8_Url", p8_Url);
		request.setAttribute("p9_SAF", p9_SAF);
		request.setAttribute("pa_MP", pa_MP);
		request.setAttribute("pr_NeedResponse", pr_NeedResponse);
		request.setAttribute("pd_FrpId", pd_FrpId);
		request.setAttribute("hmac", hmac);
		//转发
		request.getRequestDispatcher("/confirm.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
