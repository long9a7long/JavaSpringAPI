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
public class nhasanxuat {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String tennsx;
	private Date createdAt;
	private Date updatedAt;
	@OneToMany(
            cascade =  CascadeType.ALL,
            mappedBy = "nhasanxuat")
	private Set<sanpham> sanpham;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTennsx() {
		return tennsx;
	}
	public void setTennsx(String tennsx) {
		this.tennsx = tennsx;
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
	public nhasanxuat(int id, String tennsx, Date createdAt, Date updatedAt, sanpham...sanphams) {
		this.id = id;
		this.tennsx = tennsx;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.sanpham=Stream.of(sanphams).collect(Collectors.toSet());
		this.sanpham.forEach(x -> x.setNhasanxuat(this));
	}
	public nhasanxuat() {

	}	
}
