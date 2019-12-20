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
import javax.persistence.OneToMany;

@Entity
public class nhacungcap {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String mancc;
	private String tenncc;
	private String sdt;
	private String email;
	private String diachi;
	private Date createdAt;
	private Date updatedAt;
	@OneToMany(
            cascade =  CascadeType.ALL,
            mappedBy = "nhacungcap")
	private Set<hoadonnhaphang> hoadonnhaphang;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMancc() {
		return mancc;
	}
	public void setMancc(String mancc) {
		this.mancc = mancc;
	}
	public String getTenncc() {
		return tenncc;
	}
	public void setTenncc(String tenncc) {
		this.tenncc = tenncc;
	}
	public String getSdt() {
		return sdt;
	}
	public void setSdt(String sdt) {
		this.sdt = sdt;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDiachi() {
		return diachi;
	}
	public void setDiachi(String diachi) {
		this.diachi = diachi;
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
	public nhacungcap(int id, String mancc, String tenncc, String sdt, String email, String diachi, Date createdAt,
			Date updatedAt, hoadonnhaphang... hoadonnhaphangs) {
		this.id = id;
		this.mancc = mancc;
		this.tenncc = tenncc;
		this.sdt = sdt;
		this.email = email;
		this.diachi = diachi;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.hoadonnhaphang=Stream.of(hoadonnhaphangs).collect(Collectors.toSet());
		this.hoadonnhaphang.forEach(x -> x.setNhacungcap(this));
	}
	public nhacungcap() {
		
	}
}
