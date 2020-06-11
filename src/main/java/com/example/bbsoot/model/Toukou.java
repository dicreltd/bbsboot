package com.example.bbsoot.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Toukou")
public class Toukou {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int tid;
	private int uid;
	private String body;
	private Date hi;

	public Toukou() {
		this.tid=0;
		this.hi = new Date();
	}

	public Toukou(int uid, String body) {
		this();
		this.uid = uid;
		this.body = body;
	}

	public Toukou(int tid, int uid, String body, Date hi) {
		this.tid = tid;
		this.uid = uid;
		this.body = body;
		this.hi = hi;
	}

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Date getHi() {
		return hi;
	}

	public void setHi(Date hi) {
		this.hi = hi;
	}

	@ManyToOne
	@JoinColumn(name = "uid",referencedColumnName = "uid",
	        insertable = false,updatable = false)
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
