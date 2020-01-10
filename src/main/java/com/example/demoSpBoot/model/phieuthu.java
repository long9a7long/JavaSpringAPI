package com.example.demoSpBoot.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
@Data
@Entity
@Table(name = "phieuthu")
public class phieuthu {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String maphieuthu;
	private int idhoadon;
	private long sotienthu;
	private Date ngaythu;
	private String nguoithu;
	private int hinhthucthu;
	private Date ngaysua;
	private String nguoisua;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMaphieuthu() {
		return maphieuthu;
	}
	public void setMaphieuthu(String maphieuthu) {
		this.maphieuthu = maphieuthu;
	}
	
	public long getSotienthu() {
		return sotienthu;
	}
	public void setSotienthu(long sotienthu) {
		this.sotienthu = sotienthu;
	}
	public Date getNgaythu() {
		return ngaythu;
	}
	public void setNgaythu(Date ngaythu) {
		this.ngaythu = ngaythu;
	}
	public String getNguoithu() {
		return nguoithu;
	}
	public void setNguoithu(String nguoithu) {
		this.nguoithu = nguoithu;
	}
	public int getHinhthucthu() {
		return hinhthucthu;
	}
	public void setHinhthucthu(int hinhthucthu) {
		this.hinhthucthu = hinhthucthu;
	}
	public Date getNgaysua() {
		return ngaysua;
	}
	public void setNgaysua(Date ngaysua) {
		this.ngaysua = ngaysua;
	}
	public String getNguoisua() {
		return nguoisua;
	}
	public void setNguoisua(String nguoisua) {
		this.nguoisua = nguoisua;
	}
	public phieuthu(int id, String maphieuthu, int idhoadon, long sotienthu, Date ngaythu, String nguoithu,
			int hinhthucthu, Date ngaysua, String nguoisua) {
		this.id = id;
		this.maphieuthu = maphieuthu;
		this.sotienthu = sotienthu;
		this.idhoadon = idhoadon;
		this.ngaythu = ngaythu;
		this.nguoithu = nguoithu;
		this.hinhthucthu = hinhthucthu;
		this.ngaysua = ngaysua;
		this.nguoisua = nguoisua;
	}
	public phieuthu() {
		
	}
	public int getIdhoadon() {
		return idhoadon;
	}
	public void setIdhoadon(int idhoadon) {
		this.idhoadon = idhoadon;
	}
	
}
