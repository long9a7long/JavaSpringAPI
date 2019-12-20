package com.example.demoSpBoot.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "chitiethoadonbh")
public class chitiethoadonbh {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private int id_hoadon;
	
	@ManyToOne
    @JoinColumn(name = "id_sanpham", referencedColumnName= "id")
	private sanpham sanpham;
	public sanpham getSanpham() {
		return sanpham;
	}
	public void setSanpham(sanpham sanpham) {
		this.sanpham = sanpham;
	}
	//private int id_sanpham;
	private int soluong;
	private long gia;
	private long giamgia;
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getSoluong() {
		return soluong;
	}
	public void setSoluong(int soluong) {
		this.soluong = soluong;
	}
	public chitiethoadonbh(int id, int soluong, long gia, long giamgia) {
		this.id = id;
		this.soluong = soluong;
		this.gia=gia;
		this.giamgia=giamgia;
	}
	public chitiethoadonbh() {
		
	}
	public int getId_hoadon() {
		return id_hoadon;
	}
	public void setId_hoadon(int id_hoadon) {
		this.id_hoadon = id_hoadon;
	}
	
}
