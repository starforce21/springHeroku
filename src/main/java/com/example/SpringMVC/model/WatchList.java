package com.example.SpringMVC.model;

import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="watchlist")
public class WatchList { 
	@Id
	private String ticker;
	public WatchList() {}
	public WatchList(String ticker) {
		super();
		this.ticker = ticker;
	}
	public String getTicker() {
		return ticker;
	}
	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

}
