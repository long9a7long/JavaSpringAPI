package com.example.demoSpBoot.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="chitietdanhmuc")
public class chitietdanhmuc {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name="id_danhmuc")
	private danhmucsp danhmucsp;
	private int id_sanpham;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId_sanpham() {
		return id_sanpham;
	}
	public void setId_sanpham(int id_sanpham) {
		this.id_sanpham = id_sanpham;
	}
	public chitietdanhmuc(int id, int id_sanpham, danhmucsp danhmucsp) {
		this.id = id;
		this.id_sanpham = id_sanpham;
		this.danhmucsp=danhmucsp;
	}
	public danhmucsp getDanhmucsp() {
		return danhmucsp;
	}
	public void setDanhmucsp(danhmucsp danhmucsp) {
		this.danhmucsp = danhmucsp;
	}
	public chitietdanhmuc() {
		
	}
	
}
