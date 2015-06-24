package com.yongche.model;

public class AddressModel {
	private double lat;
	private double lng;
	private String address;
	private String name;
	private String city;

	public AddressModel() {
		super();
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public AddressModel(double lat, double lng, String address, String name) {
		super();
		this.lat = lat;
		this.lng = lng;
		this.address = address;
		this.name = name;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
