package utils;

import java.util.List;

import com.activeandroid.Model;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;

public final class DButils {
	
	
	
		public static void insert(CardGoodsInfo info)
		{
			CardGoodsInfo goodsinfo = info.clone();
			goodsinfo.save();//添加到购物车
		}
		public static void delete(String param,CardGoodsInfo info)
		{
			//取消加入购物车
			new Delete().from(CardGoodsInfo.class)
			.where(param,info.getGoodsid()).executeSingle();
		}
		public static boolean hasLike(String param,CardGoodsInfo info)
		{
			CardGoodsInfo goodsinfo = new Select().from(CardGoodsInfo.class).where(param,info.getGoodsid()).executeSingle();
			return goodsinfo!=null;//查询是否已加入
		}		
		// 查询购物车数据库
		public static List<CardGoodsInfo> query()
		{
			List<CardGoodsInfo> info = new Select().from(CardGoodsInfo.class).execute();
			return info;
					
		}
	
	
}
