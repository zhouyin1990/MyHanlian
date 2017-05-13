package utils;

public final class TCHConstants
{
	public static class url
	{
		//订单全局id
		public static String orderid ="";
		//全局token
		public static String token ="";
		//订单金额
		public static String ordermoney ="";
		public static final String Host = "http://www.zuichu.cc/";

//		public static final String getcode = Host + "api/Login/SendLoginCode?phone=";//获取验证码
		public static final String getcode = Host + "api/Login/SendLoginCode";//获取验证码


		public static final String hoturl = Host + "api/PageData/QueryHotData"; //热门
    	public static final String newurl = Host +"api/PageData/QueryNewData"; // 新品
        public static final String homeurl = Host +"api/PageData/QueryFirstData"; // 首页
		public static final String imgurl = "http://www.zuichu.cc/"+"/api/GetImages/Get?path=";//图片地址

		public static final String detailimgurl= "http://106.15.38.42/" +"/api/GetImages/Get?" ;

		public static final String detailsurl = Host +"api/PageData/QueryGoodsDetails"; // 详情 ;
	    public static final String searchurl = Host +"api/PageData/SearchGoods";// 搜索
		public static final	String JoinCollectionurl = Host +"api/UserDoMain/JoinMyCollection" ; // 加入我的收藏
		public static final String QueryMyCollectionurl = Host +"api/UserDoMain/QueryMyCollection"; // 查询收藏
		public static final String DelMyCollectionurl= Host +"api/UserDoMain/DelMyCollection"  ;		// 删除收藏
		public static final String QueryMyInformationurl= Host +"api/UserDoMain/QueryMyInformation"; // 个人信息
		public static final String SUBMITORDER= Host +"api/UserDoMain/SubmitOrder";//提交订单

		public static final String GETTESTTOKEN= Host +"api/Login/GetToken";// 获取token
		public static final String MYshouhou= Host +"api/UserDoMain/QueryMyAfterService"; //我的售后{退换货}
		// 大分类
		public static final String goodsSort= Host +"api/PageData/GetSubclassification";// 大分类
		// 分类查询商品
		public static final String goodsSortDetail= Host +"api/PageData/QueryClassifyData";

		
		public static final String QueryPromotionData= Host +"api/PageData/QueryPromotionData";// 查询推荐商品
		public static final String QueryMyAllOrders_Url = Host+"api/UserDoMain/QueryMyOrders_All"; //所有订单

		public static final String QueryMyOrders_Arrearageuri = Host+"api/UserDoMain/QueryMyOrders_Arrearage";//待付款

		public static final String daishouhuoUrl =Host+ "api/UserDoMain/QueryMyOrders_Settlement";//待收货
		//找回账号发送 验证码
		public static final String SendBackAccountCode =Host+ "api/UserDoMain/SendBackAccountCode";			   
		//找回密码发送 验证码
		public static final String SendRetrieveCode =Host+ "api/UserDoMain/SendRetrieveCode";
		// 找回账号	
		public static final String BackAccount =Host+ "api/UserDoMain/BackAccount";
		//重置密码
		public static final String ResettingPassword =Host+ "api/UserDoMain/ResettingPassword" ;
        // 修改密码
		public static final String ModifyPassword =Host+ "api/UserDoMain/ModifyPassword" ;
		//login
		public static final String Login = "http://www.zuichu.cc/api/Login/UserLogin";
        //alipay
		public static final String AliPay = Host+"api/PayStartCall/AliPay_app";
		//删除订单
		public static final String  DelMyOrder =Host +"api/UserDoMain/DelMyOrder";




	}
}
