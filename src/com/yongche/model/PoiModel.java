package com.yongche.model;

public class PoiModel {
	private double lat;
	private double lng;

	public PoiModel() {
		super();
	}

	public PoiModel(double lat, double lng) {
		super();
		this.lat = lat;
		this.lng = lng;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

}
