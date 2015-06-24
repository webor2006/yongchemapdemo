package com.yongche.model;

public class Marker {
    private String id;
    private double lat;
    private double lng;
    private int type;
    private String infoString;
    private boolean isShowWindow;
    
	public Marker() {
		super();
	}
	
	public Marker(String id, double lat, double lng, int type,
			String infoString, boolean isShowWindow) {
		super();
		this.id = id;
		this.lat = lat;
		this.lng = lng;
		this.type = type;
		this.infoString = infoString;
		this.isShowWindow = isShowWindow;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLng() {
		return lng;
	}
	public void setLng(double lng) {
		this.lng = lng;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getInfoString() {
		return infoString;
	}
	public void setInfoString(String infoString) {
		this.infoString = infoString;
	}
	public boolean isShowWindow() {
		return isShowWindow;
	}
	public void setShowWindow(boolean isShowWindow) {
		this.isShowWindow = isShowWindow;
	}
    
}
