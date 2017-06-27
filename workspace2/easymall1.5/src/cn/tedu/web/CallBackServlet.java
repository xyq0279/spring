package cn.tedu.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.Utils.PaymentUtil;
import cn.tedu.factory.BasicFactory;
import cn.tedu.service.OrderService;

public class CallBackServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//接受请求打过来的参数
		// 获得回调所有数据
		String p1_MerId = request.getParameter("p1_MerId");
		String r0_Cmd = request.getParameter("r0_Cmd");
		String r1_Code = request.getParameter("r1_Code");
		String r2_TrxId = request.getParameter("r2_TrxId");
		String r3_Amt = request.getParameter("r3_Amt");
		String r4_Cur = request.getParameter("r4_Cur");
		String r5_Pid = request.getParameter("r5_Pid");
		String r6_Order = request.getParameter("r6_Order");
		String r7_Uid = request.getParameter("r7_Uid");
		String r8_MP = request.getParameter("r8_MP");
		String r9_BType = request.getParameter("r9_BType");
		String rb_BankId = request.getParameter("rb_BankId");
		String ro_BankOrderId = request.getParameter("ro_BankOrderId");
		String rp_PayDate = request.getParameter("rp_PayDate");
		String rq_CardNo = request.getParameter("rq_CardNo");
		String ru_Trxtime = request.getParameter("ru_Trxtime");
		// 身份校验 --- 判断是不是支付公司通知你
		String hmac = request.getParameter("hmac");
		String keyValue = PaymentUtil.getPro("keyValue");
		//签名数据
		boolean varify = PaymentUtil.verifyCallback(hmac, p1_MerId, r0_Cmd, r1_Code, r2_TrxId, r3_Amt, r4_Cur, r5_Pid, r6_Order, r7_Uid, r8_MP, r9_BType, keyValue);
		if(varify){
			if("1".equals(r9_BType)&&"1".equals(r1_Code)){//重定向转发过来的
				response.getWriter().write("支付操作正在执行，支付结果需等待进一步的通知，不要重复支付操作...");
			}else if ("2".equals(r9_BType)&&"1".equals(r1_Code)){//点对点通讯
				//表示支付成功 
				//修改订单的支付状态
				OrderService os =BasicFactory.getFactory().getInstance(OrderService.class);
				os.updatePaystate(r6_Order,1);
				//响应success
				response.getWriter().write("success");
			}else{//交易失败
				response.getWriter().write("支付失败");
			}
		}else{
			System.out.println("数据被篡改");
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
