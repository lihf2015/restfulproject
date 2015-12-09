package com.andy.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestServlet
 */
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    @Override
    public void init() throws ServletException {
    	// TODO Auto-generated method stub
    	//super.init();
    	System.out.println("do get---init--start---------");
    	System.out.println("do get---init--end---------");
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("do get---back--start---------");
		if(null == request){
			System.out.println("request null");
		}
		
		String utf8 = "utf-8";
		String reqCharset = request.getCharacterEncoding();
		reqCharset = utf8;
		System.out.println("queryId:"+request.getParameter("queryId"));
		System.out.println("settleAmt:"+request.getParameter("settleAmt"));
		System.out.println("traceNo:"+request.getParameter("traceNo"));
		//System.out.println(request.getParameter(""));
		//------------------start---------------
		//银行返回信息，用于校验其真实性
//		String responseMsg = request.getQueryString();
//		String[] strRespArray = new String[]{
//				"Succeed",
//				"CoNo",
//				"BillNo",
//				"Amount",
//				"Date",
//				"Msg",
//				"MerchantPara",
//				"Signature",
//			};
//		//按接受字段表取出交易正确的数据
//		Map<String, String> respMap = new HashMap<String, String>();
//		for(int i=0,len=strRespArray.length; i<len; i++){
//			String key = strRespArray[i];
//			String value = request.getParameter(key);
//			if(null != value){
//				value = new String(value.getBytes(reqCharset),utf8);
//				respMap.put(key, value);
//				
//			}
//		}
		//cmb
//		CMBBankPayTrans cb=new CMBBankPayTrans();
//		BankTxnResult txnResult=cb.BankRequestTrans("2055", request);//2055
//		ApiBankTxnBean reqBean = new ApiBankTxnBean();
//		if(BankTxnResult.RETURN_SUCC.equals(txnResult.getTxnstate())){
//				reqBean = (ApiBankTxnBean) txnResult.getDetailvo();
//			System.out.println("Test Trans Result Data : " + reqBean.toString());
//		}else{
//			System.out.println("Test Trans Return Error : [" + txnResult.getErrCode() + "] " + txnResult.getErrMessage());
//		}
		//chinapay
//		ChinapayWapPayTrans cb=new ChinapayWapPayTrans();
//		BankTxnResult txnResult=cb.BankRequestTrans("2055", request);//2055
//		ApiBankTxnBean reqBean = new ApiBankTxnBean();
//		if(BankTxnResult.RETURN_SUCC.equals(txnResult.getTxnstate())){
//				reqBean = (ApiBankTxnBean) txnResult.getDetailvo();
//			System.out.println("Test Trans Result Data : " + reqBean.toString());
//		}else{
//			System.out.println("Test Trans Return Error : [" + txnResult.getErrCode() + "] " + txnResult.getErrMessage());
//		}
		
		//------------------end---------------
		// 获取工商银行通知信息
//		String notifyData = request.getParameter("notifyData");
//		String signMsg = request.getParameter("signMsg");
//		//ICBCBankPayProcess payProcess = new ICBCBankPayProcess();
//	//	String res=new String(payProcess.base64Decode(notifyData),"GBK");
//		//System.out.println("response:"+res);
//		System.err.println("notifyData:"+notifyData);
//		System.out.println("-----------------------------------------");
//		System.err.println("signMsg:"+signMsg);
//		ICBCBankPayTrans icbcTrans=new ICBCBankPayTrans();
//		BankTxnResult txnResult= icbcTrans.BankRequestTrans("2055", request);
//		if(BankTxnResult.RETURN_SUCC.equals(txnResult.getTxnstate())){
//			ApiBankTxnBean reqBean = (ApiBankTxnBean) txnResult.getDetailvo();
//			if(TransConstants.API_TXN_CODE_CARD_SIGN_SEND_THIRD.equals("2055")){
//				System.err.println("Test Trans Result Data : " + reqBean.getSendTxnMessage());
//			}else{
//				System.err.println("Test Trans Result Data : " + reqBean.toString());
//			}
//		}else{
//			System.err.println("Test Trans Return Error : [" + txnResult.getErrCode() + "] " + txnResult.getErrMessage());
//		}
		System.out.println("do get ----back----end");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
