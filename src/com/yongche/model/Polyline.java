package com.yongche.model;

import java.util.ArrayList;

public class Polyline {
	private String id;
	private int strokeWidth;
	private String strokeColor;
	private String title;
	private ArrayList<PoiModel> points;

	public Polyline() {
		super();
	}

	public Polyline(String id, int strokeWidth, String strokeColor,
			String title, ArrayList<PoiModel> points) {
		super();
		this.id = id;
		this.strokeWidth = strokeWidth;
		this.strokeColor = strokeColor;
		this.title = title;
		this.points = points;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getStrokeWidth() {
		return strokeWidth;
	}

	public void setStrokeWidth(int strokeWidth) {
		this.strokeWidth = strokeWidth;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public ArrayList<PoiModel> getPoints() {
		return points;
	}

	public void setPoints(ArrayList<PoiModel> points) {
		this.points = points;
	}

	public String getStrokeColor() {
		return strokeColor;
	}

	public void setStrokeColor(String strokeColor) {
		this.strokeColor = strokeColor;
	}

}
