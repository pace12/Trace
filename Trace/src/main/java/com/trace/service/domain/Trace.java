package com.trace.service.domain;

import java.sql.Date;

public class Trace {
	
	private int traceNo;
	private String traceId;
	private String latitude; 
	private String longtitude; 
	private Date tracedate; 
	private String text;
	private int likes;
	private String oriImgName;
	private String stoImgName;
	
	
	public int getTraceNo() {
		return traceNo;
	}
	public void setTraceNo(int traceNo) {
		this.traceNo = traceNo;
	}
	public String getTraceId() {
		return traceId;
	}
	public void setTraceId(String traceId) {
		this.traceId = traceId;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongtitude() {
		return longtitude;
	}
	public void setLongtitude(String longtitude) {
		this.longtitude = longtitude;
	}
	public Date getTracedate() {
		return tracedate;
	}
	public void setTracedate(Date tracedate) {
		this.tracedate = tracedate;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}
	public String getOriImgName() {
		return oriImgName;
	}
	public void setOriImgName(String oriImgName) {
		this.oriImgName = oriImgName;
	}
	public String getStoImgName() {
		return stoImgName;
	}
	public void setStoImgName(String stoImgName) {
		this.stoImgName = stoImgName;
	}
	
	
	@Override
	public String toString() {
		return "Trace [traceNo=" + traceNo + ", traceId=" + traceId + ", latitude=" + latitude + ", longtitude="
				+ longtitude + ", tracedate=" + tracedate + ", text=" + text + ", likes=" + likes + ", oriImgName="
				+ oriImgName + ", stoImgName=" + stoImgName + "]";
	}
	
	
}
