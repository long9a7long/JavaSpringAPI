package com.example.demoSpBoot.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
@Table(name = "hoadonbanhang")
public class hoadonbanhang {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String mahoadon;
	private int loaithanhtoan;
	private long tonggia;
	private long giamgia;
	private long khachhangtra;
	private int trangthai;
	private String ghichu;
	public String getGhichu() {
		return ghichu;
	}
	public void setGhichu(String ghichu) {
		this.ghichu = ghichu;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at")
	private Date createdAt;
	
	
	@Column(name = "updated_at",nullable = true)
	private Date updatedAt;
	
	@ManyToOne
    @JoinColumn(name = "nguoisua", referencedColumnName= "manhanvien")
	private users nguoisua;
	public users getNguoisua() {
		return nguoisua;
	}
	public void setNguoisua(users nguoisua) {
		this.nguoisua = nguoisua;
	}
	@ManyToOne
    @JoinColumn(name = "nguoitao", referencedColumnName= "manhanvien")
	private users nguoitao;
	public users getNguoitao() {
		return nguoitao;
	}
	public void setNguoitao(users nguoitao) {
		this.nguoitao = nguoitao;
	}
	@ManyToOne
    @JoinColumn(name = "makhachhang")
	private khachhang khachhang;
	
	@Column(nullable = true)
	@OneToMany( cascade =  CascadeType.ALL )
	@JoinColumn( name= "idhoadon", referencedColumnName= "id")
	private List<phieuthu> phieuthus;
	
	public List<phieuthu> getPhieuthus() {
		return phieuthus;
	}
	public void setPhieuthus(List<phieuthu> phieuthus) {
		this.phieuthus = phieuthus;
	}
	@Column(nullable = true)
	@OneToMany( cascade =  CascadeType.ALL, fetch= FetchType.EAGER )
	@JoinColumn( name= "id_hoadon", referencedColumnName= "id")
	private List<chitiethoadonbh> chitiethoadons;
	
	public List<chitiethoadonbh> getChitiethoadons() {
		return chitiethoadons;
	}
	public void setChitiethoadons(List<chitiethoadonbh> chitiethoadons) {
		this.chitiethoadons = chitiethoadons;
	}
	public khachhang getKhachhang() {
		return khachhang;
	}
	public void setKhachhang(khachhang khachhang) {
		this.khachhang = khachhang;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMahoadon() {
		return mahoadon;
	}
	public void setMahoadon(String mahoadon) {
		this.mahoadon = mahoadon;
	}
	
	public int getLoaithanhtoan() {
		return loaithanhtoan;
	}
	public void setLoaithanhtoan(int loaithanhtoan) {
		this.loaithanhtoan = loaithanhtoan;
	}
	public long getTonggia() {
		return tonggia;
	}
	public void setTonggia(long tonggia) {
		this.tonggia = tonggia;
	}
	public long getGiamgia() {
		return giamgia;
	}
	public void setGiamgia(long giamgia) {
		this.giamgia = giamgia;
	}
	public long getKhachhangtra() {
		return khachhangtra;
	}
	public void setKhachhangtra(long khachhangtra) {
		this.khachhangtra = khachhangtra;
	}
	public int getTrangthai() {
		return trangthai;
	}
	public void setTrangthai(int trangthai) {
		this.trangthai = trangthai;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	

	public hoadonbanhang(int id, String mahoadon,  int loaithanhtoan, long tonggia, long giamgia,
			long khachhangtra, int trangthai, Date createdAt, Date updatedAt, users nguoisua,  users nguoitao, String ghichu, List<chitiethoadonbh> chitiethoadons) {
		this.id = id;
		this.ghichu = ghichu;
		this.mahoadon = mahoadon;
		this.loaithanhtoan = loaithanhtoan;
		this.tonggia = tonggia;
		this.giamgia = giamgia;
		this.khachhangtra = khachhangtra;
		this.trangthai = trangthai;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.nguoisua = nguoisua;
		this.nguoitao = nguoitao;
		this.chitiethoadons = chitiethoadons;
	}
	public hoadonbanhang() {

	}
	
}
