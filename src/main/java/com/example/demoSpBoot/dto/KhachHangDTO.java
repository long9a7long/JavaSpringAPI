package com.example.demoSpBoot.dto;

import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;

@Embeddable
@Entity
public class KhachHangDTO {
	
	@Id
	private String makhachhang;
	private String ten;
	private String sdt;
	private String diachi;
	private long tonggia;
	private long tongno;
	private Date lancuoimuahang;
	public String getMakhachhang() {
		return makhachhang;
	}
	public void setMakhachhang(String makhachhang) {
		this.makhachhang = makhachhang;
	}
	public String getTen() {
		return ten;
	}
	public void setTen(String ten) {
		this.ten = ten;
	}
	public String getSdt() {
		return sdt;
	}
	public void setSdt(String sdt) {
		this.sdt = sdt;
	}
	
	public String getDiachi() {
		return diachi;
	}
	public void setDiachi(String diachi) {
		this.diachi = diachi;
	}
	public long getTonggia() {
		return tonggia;
	}
	public void setTonggia(long tonggia) {
		this.tonggia = tonggia;
	}
	public long getTongno() {
		return tongno;
	}
	public void setTongno(long tongno) {
		this.tongno = tongno;
	}
	public Date getCreatedAt() {
		return lancuoimuahang;
	}
	public void setCreatedAt(Date createdAt) {
		this.lancuoimuahang = createdAt;
	}
	public KhachHangDTO(String makhachhang, String ten, String sdt, String email, String diachi, long tonggia,
			long tongno, Date createdAt) {
		this.makhachhang = makhachhang;
		this.ten = ten;
		this.sdt = sdt;
		this.diachi = diachi;
		this.tonggia = tonggia;
		this.tongno = tongno;
		this.lancuoimuahang = createdAt;
	}
	
	public KhachHangDTO() {
		
	}
}
