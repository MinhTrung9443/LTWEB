package vn.iotstar.model;

import java.io.Serializable;

public class CategoryModel implements Serializable{
	@Override
	public String toString() {
		return "CategoryModel [cate_id=" + cate_id + ", cate_name=" + cate_name + ", icon=" + icon + ", active="
				+ active + "]";
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = -1309566883832857739L;
	private int cate_id;
	private String cate_name;
	private String icon;
	private boolean active;
	public CategoryModel(int cate_id, String cate_name, String icon) {
		super();
		this.cate_id = cate_id;
		this.cate_name = cate_name;
		this.icon = icon;
	}
	public CategoryModel() {
		super();
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public int getCate_id() {
		return cate_id;
	}
	public void setCate_id(int cate_id) {
		this.cate_id = cate_id;
	}
	public String getCate_name() {
		return cate_name;
	}
	public void setCate_name(String cate_name) {
		this.cate_name = cate_name;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	
}
