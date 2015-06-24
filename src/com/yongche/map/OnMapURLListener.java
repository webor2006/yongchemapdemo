package com.yongche.map;

import java.util.ArrayList;

import com.yongche.model.Marker;
import com.yongche.model.AddressModel;

public interface OnMapURLListener {
	public void onMapLoaded(boolean result);

	public void onMapClick(double lat, double lng);

	public void onCenterChange(double lat, double lng);

	public void onZoomChange(int zoom);

	public void onMarkerClick(Marker marker);

	public void onSearchAddressResult(int tag, double lat, double lng,
			String address,String city);

	public void onFromLatLngToPoint(int tag, double lat, double lng, int x,
			int y);

	public void onFromPointToLatLng(int tag, double lat, double lng, int x,
			int y);

	public void onSearchNearbyPoi(int tag, ArrayList<AddressModel> addressModels);
}
