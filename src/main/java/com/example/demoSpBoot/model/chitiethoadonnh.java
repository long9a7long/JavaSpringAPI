package com.example.demoSpBoot.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="chitiethoadonnh")
public class chitiethoadonnh {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int id_hoadon;
	private int id_sanpham;
	private int soluong;
	private long gia;
	private long giamgia;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId_hoadon() {
		return id_hoadon;
	}
	public void setId_hoadon(int id_hoadon) {
		this.id_hoadon = id_hoadon;
	}
	public int getId_sanpham() {
		return id_sanpham;
	}
	public void setId_sanpham(int id_sanpham) {
		this.id_sanpham = id_sanpham;
	}
	public int getSoluong() {
		return soluong;
	}
	public void setSoluong(int soluong) {
		this.soluong = soluong;
	}
	public long getGia() {
		return gia;
	}
	public void setGia(long gia) {
		this.gia = gia;
	}
	public long getGiamgia() {
		return giamgia;
	}
	public void setGiamgia(long giamgia) {
		this.giamgia = giamgia;
	}
	public chitiethoadonnh(int id, int id_hoadon, int id_sanpham, int soluong, long gia, long giamgia) {
		this.id = id;
		this.id_hoadon = id_hoadon;
		this.id_sanpham = id_sanpham;
		this.soluong = soluong;
		this.gia = gia;
		this.giamgia = giamgia;
	}
	public chitiethoadonnh() {
	
	}
	
}
