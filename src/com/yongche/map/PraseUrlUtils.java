package com.yongche.map;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yongche.model.Marker;
import com.yongche.model.AddressModel;

public class PraseUrlUtils {
	private OnMapURLListener listener;

	public PraseUrlUtils(OnMapURLListener listener) {
		super();
		this.listener = listener;
	}

	public OnMapURLListener getListener() {
		return listener;
	}

	public void setListener(OnMapURLListener listener) {
		this.listener = listener;
	}

	public void prase(String url) {
		if (listener == null) {
			return;
		}
		Map<String, String> params = parserUrlParamsCommand(url);
		if (params.size() == 0) {
			return;
		}
		if (url.startsWith(H5UrlUtils.URL_ON_MAP_LOADED)) {
			boolean result = false;
			result = Boolean.parseBoolean(params.get("result"));

			listener.onMapLoaded(result);
		} else if (url.startsWith(H5UrlUtils.URL_ON_MAP_CLICK)) {
			try {
				listener.onMapClick(Double.parseDouble(params.get("lat")),
						Double.parseDouble(params.get("lng")));
			} catch (NumberFormatException e) {
				// TODO: handle exception
			}
		} else if (url.startsWith(H5UrlUtils.URL_ON_CENTER_CHANGE)) {
			try {
				listener.onCenterChange(Double.parseDouble(params.get("lat")),
						Double.parseDouble(params.get("lng")));
			} catch (NumberFormatException e) {
				// TODO: handle exception
			}
		} else if (url.startsWith(H5UrlUtils.URL_ON_ZOOM_CHANGE)) {
			try {
				listener.onZoomChange(Integer.parseInt(params.get("zoom")));
			} catch (NumberFormatException e) {
				// TODO: handle exception
			}
		} else if (url.startsWith(H5UrlUtils.URL_ON_MARKER_CLICK)) {
			String markerStr = params.get("marker");
			Marker marker = new Gson().fromJson(markerStr,
					new TypeToken<Marker>() {
					}.getType());
			if (marker != null) {
				if (marker.getInfoString() != null) {
					marker.setInfoString(H5UrlUtils.decodeUrlStr(marker
							.getInfoString()));
				}
				listener.onMarkerClick(marker);
			}
		} else if (url.startsWith(H5UrlUtils.URL_ON_SEARCH_ADDRESS_POI)) {
			try {
				listener.onSearchAddressResult(
						Integer.parseInt(params.get("tag")),
						Double.parseDouble(params.get("lat")),
						Double.parseDouble(params.get("lng")),
						params.get("address") != null ? H5UrlUtils
								.decodeUrlStr(params.get("address")) : "",
						params.get("city") != null ? H5UrlUtils
								.decodeUrlStr(params.get("city")) : "");
			} catch (NumberFormatException e) {
				// TODO: handle exception
			}
		} else if (url.startsWith(H5UrlUtils.URL_ON_FROM_LATLNG_TO_POINT)) {
			try {
				listener.onFromLatLngToPoint(
						Integer.parseInt(params.get("tag")),
						Double.parseDouble(params.get("lat")),
						Double.parseDouble(params.get("lng")),
						Integer.parseInt(params.get("x")),
						Integer.parseInt(params.get("y")));
			} catch (NumberFormatException e) {
				// TODO: handle exception
			}
		} else if (url.startsWith(H5UrlUtils.URL_ON_FROM_POINT_TO_LATLNG)) {
			try {
				listener.onFromPointToLatLng(
						Integer.parseInt(params.get("tag")),
						Double.parseDouble(params.get("lat")),
						Double.parseDouble(params.get("lng")),
						Integer.parseInt(params.get("x")),
						Integer.parseInt(params.get("y")));
			} catch (NumberFormatException e) {
				// TODO: handle exception
			}
		} else if (url.startsWith(H5UrlUtils.URL_ON_SEARCH_NEAR_BY_POI)) {
			String resultStr = params.get("result");
			ArrayList<AddressModel> addressModels = new Gson().fromJson(
					resultStr, new TypeToken<ArrayList<AddressModel>>() {
					}.getType());
			if (addressModels != null) {
				for (int i = 0; i < addressModels.size(); i++) {
					if (addressModels.get(i) != null
							&& addressModels.get(i).getAddress() != null) {
						addressModels.get(i).setAddress(
								H5UrlUtils.decodeUrlStr(addressModels.get(i)
										.getAddress()));
						addressModels.get(i).setCity(
								H5UrlUtils.decodeUrlStr(addressModels.get(i)
										.getCity()));
					}
				}
				listener.onSearchNearbyPoi(Integer.parseInt(params.get("tag")),
						addressModels);
			}

		}
	}

	private Map<String, String> parserUrlParamsCommand(String url) {
		Map<String, String> params = new HashMap<String, String>();
		if (!url.contains("?")) {
			return params;
		}
		int commandIndex = url.indexOf("?");
		String paramString = url.substring(commandIndex + 1, url.length());
		String[] paramsArray = paramString.split("&");

		for (int i = 0; i < paramsArray.length; i++) {

			String[] paramsSub = paramsArray[i].split("=");
			if (paramsSub.length == 0 || paramsSub[0] == null
					|| paramsSub[0].length() == 0) {
				continue;
			} else if (paramsSub.length == 1) {
				params.put(paramsSub[0], "");
			} else {
				params.put(paramsSub[0], paramsSub[1]);
			}
		}
		return params;
	}
}
