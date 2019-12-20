package com.example.demoSpBoot.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "danhmucsp")
public class danhmucsp {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String tendanhmuc;
	private String motadanhmuc;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTendanhmuc() {
		return tendanhmuc;
	}
	public void setTendanhmuc(String tendanhmuc) {
		this.tendanhmuc = tendanhmuc;
	}
	public String getMotadanhmuc() {
		return motadanhmuc;
	}
	public void setMotadanhmuc(String motadanhmuc) {
		this.motadanhmuc = motadanhmuc;
	}
	public danhmucsp(int id, String tendanhmuc, String motadanhmuc) {
		this.id = id;
		this.tendanhmuc = tendanhmuc;
		this.motadanhmuc = motadanhmuc;
	}
	public danhmucsp() {

	}	
}
