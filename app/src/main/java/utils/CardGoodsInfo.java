package utils;

import java.io.Serializable;

import org.json.JSONObject;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "GOODSCAR")
public class CardGoodsInfo extends Model implements Serializable, Cloneable{	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column
	String title;
	@Column
	String ImgURL;
	@Column
	int totalprice; //单价
	@Column
	String goodsid;
	@Column
	int count;
	@Column
	String shoppingName;
	@Column
	String shoppingID;
	@Column
	boolean ischeck;
	@Column
	int Zongjia;
	public int getZongjia() {
		return Zongjia;
	}
	public void setZongjia(int zongjia) {
		Zongjia = zongjia;
	}
	@Column
	JSONObject GOODSLIST;


	@Override
	public CardGoodsInfo clone()
	{
		try
		{
			return (CardGoodsInfo) super.clone();
		} catch (CloneNotSupportedException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public JSONObject getGOODSLIST() {
		return GOODSLIST;
	}

	public void setGOODSLIST(JSONObject gOODSLIST) {
		GOODSLIST = gOODSLIST;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImgURL() {
		return ImgURL;
	}
	public void setImgURL(String imgURL) {
		ImgURL = imgURL;
	}
	public int getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(int totalprice) {
		this.totalprice = totalprice;
	}

	public String getGoodsid() {
		return goodsid;
	}

	public void setGoodsid(String goodsid) {
		this.goodsid = goodsid;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}


	public String getShoppingName() {
		return shoppingName;
	}


	public void setShoppingName(String shoppingName) {
		this.shoppingName = shoppingName;
	}


	public String getShoppingID() {
		return shoppingID;
	}


	public void setShoppingID(String shoppingID) {
		this.shoppingID = shoppingID;
	}


	public boolean isIscheck() {
		return ischeck;
	}


	public void setIscheck(boolean ischeck) {
		this.ischeck = ischeck;
	}
}
