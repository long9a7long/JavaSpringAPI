package com.example.demoSpBoot.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
public class phieuchi {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String maphieuchi;
	@ManyToOne
    @JoinColumn(name = "idhoadon")
	private hoadonnhaphang hoadonnhaphang;
	private long sotienchi;
	private Date ngaychi;
	private String nguoichi;
	private int hinhthucchi;
	private Date ngaysua;
	private String nguoisua;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMaphieuchi() {
		return maphieuchi;
	}
	public void setMaphieuchi(String maphieuchi) {
		this.maphieuchi = maphieuchi;
	}
	public long getSotienchi() {
		return sotienchi;
	}
	public void setSotienchi(long sotienchi) {
		this.sotienchi = sotienchi;
	}
	public Date getNgaychi() {
		return ngaychi;
	}
	public void setNgaychi(Date ngaychi) {
		this.ngaychi = ngaychi;
	}
	public String getNguoichi() {
		return nguoichi;
	}
	public void setNguoichi(String nguoichi) {
		this.nguoichi = nguoichi;
	}
	public int getHinhthucchi() {
		return hinhthucchi;
	}
	public void setHinhthucchi(int hinhthucchi) {
		this.hinhthucchi = hinhthucchi;
	}
	public Date getNgaysua() {
		return ngaysua;
	}
	public void setNgaysua(Date ngaysua) {
		this.ngaysua = ngaysua;
	}
	public String getNguoisua() {
		return nguoisua;
	}
	public void setNguoisua(String nguoisua) {
		this.nguoisua = nguoisua;
	}
	public phieuchi(int id, String maphieuchi, long sotienchi, Date ngaychi, String nguoichi,
			int hinhthucchi, Date ngaysua, String nguoisua, hoadonnhaphang hoadonnhaphang) {
		this.id = id;
		this.maphieuchi = maphieuchi;
		this.sotienchi = sotienchi;
		this.ngaychi = ngaychi;
		this.nguoichi = nguoichi;
		this.hinhthucchi = hinhthucchi;
		this.ngaysua = ngaysua;
		this.nguoisua = nguoisua;
		this.hoadonnhaphang=hoadonnhaphang;
	}
	public hoadonnhaphang getHoadonnhaphang() {
		return hoadonnhaphang;
	}
	public void setHoadonnhaphang(hoadonnhaphang hoadonnhaphang) {
		this.hoadonnhaphang = hoadonnhaphang;
	}
	public phieuchi() {
		
	}
	
}
