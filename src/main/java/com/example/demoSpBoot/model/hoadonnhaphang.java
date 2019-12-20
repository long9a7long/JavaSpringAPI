package com.example.demoSpBoot.model;

import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "hoadonnhaphang")
public class hoadonnhaphang {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String mahoadon;
	@ManyToOne
    @JoinColumn(name = "nhacungcap")
	private nhacungcap nhacungcap;
	private int loaithanhtoan;
	public void setNhacungcap(nhacungcap nhacungcap) {
		this.nhacungcap = nhacungcap;
	}
	private long tonggia;
	private long giamgia;
	private long datra;
	private int trangthai;
	private Date createdAt;
	private Date updatedAt;
	private String nguoitao;
	private String nguoisua;
	@OneToMany(
            cascade =  CascadeType.ALL,
            mappedBy = "hoadonnhaphang")
	private Set<phieuchi> phieuchi;
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
	public long getDatra() {
		return datra;
	}
	public void setDatra(long datra) {
		this.datra = datra;
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
	public String getNguoitao() {
		return nguoitao;
	}
	public void setNguoitao(String nguoitao) {
		this.nguoitao = nguoitao;
	}
	public String getNguoisua() {
		return nguoisua;
	}
	public void setNguoisua(String nguoisua) {
		this.nguoisua = nguoisua;
	}
	public hoadonnhaphang(int id, String mahoadon, int loaithanhtoan, long tonggia, long giamgia,
			long datra, int trangthai, Date createdAt, Date updatedAt, String nguoitao, String nguoisua,
			nhacungcap nhacungcap, phieuchi...phieuchis) {
		this.id = id;
		this.mahoadon = mahoadon;
		this.loaithanhtoan = loaithanhtoan;
		this.tonggia = tonggia;
		this.giamgia = giamgia;
		this.datra = datra;
		this.trangthai = trangthai;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.nguoitao = nguoitao;
		this.nguoisua = nguoisua;
		this.nhacungcap=nhacungcap;
		this.phieuchi=Stream.of(phieuchis).collect(Collectors.toSet());
		this.phieuchi.forEach(x -> x.setHoadonnhaphang(this));
	}
	public nhacungcap getNhacungcap() {
		return nhacungcap;
	}
	public hoadonnhaphang() {

	}
}
