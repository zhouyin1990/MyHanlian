package com.example.hanlian.Adapter;

public class GoodsInfo {

	public String image;//图片 url
	public String name;
	public int id;
	public GoodsInfo(String image, String name, int id) {
		super();
		this.image = image;
		this.name = name;
		this.id = id;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}



	
}
