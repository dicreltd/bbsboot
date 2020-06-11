package com.example.bbsoot.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="User")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int uid;
	private String uname;
	private String pass;

	public User() {
	}

	public User(int uid, String uname, String pass) {
		this.uid = uid;
		this.uname = uname;
		this.pass = pass;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}


}
