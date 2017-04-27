package com.example.hanlian.Fristpager;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;

public class Result {

    @Expose
    private List<Show> Shows = new ArrayList<Show>();
    @Expose
    private List<Good> Goods = new ArrayList<Good>();

    /**
     * 
     * @return
     *     The Shows
     */
    public List<Show> getShows() {
        return Shows;
    }

    /**
     * 
     * @param Shows
     *     The Shows
     */
    public void setShows(List<Show> Shows) {
        this.Shows = Shows;
    }

    /**
     * 
     * @return
     *     The Goods
     */
    public List<Good> getGoods() {
        return Goods;
    }

    /**
     * 
     * @param Goods
     *     The Goods
     */
    public void setGoods(List<Good> Goods) {
        this.Goods = Goods;
    }

	@Override
	public String toString() {
		return "Result [Shows=" + Shows + ", Goods=" + Goods + "]";
	}
    
    

}
