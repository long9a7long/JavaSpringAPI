package com.example.demoSpBoot.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;


@Embeddable
@Entity
@Table(name = "users")
@Data
public class users {
	@Id
	@Column(name = "manhanvien")
	private String username;
	private String password;
	private int vaitro;
	private String salt;
	private boolean trangthai;
	private Date createdAt;
	private Date updatedAt;
	private String ten;
	private String sdt;
	private String email;
	private String diachi;
	private Date ngaysinh;
	private boolean gioitinh;
		
	public users() {	}
	
	public String getManhanvien() {
		return username;
	}

	public void setManhanvien(String manhanvien) {
		this.username = manhanvien;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getVaitro() {
		return vaitro;
	}

	public void setVaitro(int vaitro) {
		this.vaitro = vaitro;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public boolean isTrangthai() {
		return trangthai;
	}

	public void setTrangthai(boolean trangthai) {
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

	public Date getNgaysinh() {
		return ngaysinh;
	}

	public void setNgaysinh(Date ngaysinh) {
		this.ngaysinh = ngaysinh;
	}

	public boolean isGioitinh() {
		return gioitinh;
	}

	public void setGioitinh(boolean gioitinh) {
		this.gioitinh = gioitinh;
	}

	public users(String manhanvien, String password, int vaitro, String salt, boolean trangthai, Date createdAt,
			Date updatedAt, String ten, String sdt, String email, String diachi, Date ngaysinh, boolean gioitinh) {
		this.username = manhanvien;
		this.password = password;
		this.vaitro = vaitro;
		this.salt = salt;
		this.trangthai = trangthai;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.ten = ten;
		this.sdt = sdt;
		this.email = email;
		this.diachi = diachi;
		this.ngaysinh = ngaysinh;
		this.gioitinh = gioitinh;
	}
}
	
