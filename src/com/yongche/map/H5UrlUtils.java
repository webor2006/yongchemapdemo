package com.yongche.map;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.IllegalFormatException;

import android.text.TextUtils;

/**
 * h5交互的URl以及js
 * 
 * @author cc
 * 
 */
public class H5UrlUtils {
	// http://www.xxxxxx.com/map?version=1.0&platform=android&lat=39.92&lng=116.46&zoom=6&canmove=true
	public static final String LOAD_URL = "http://sandbox2.yongche.org/map/google.html?version=1.0&platform=android";
	public static final String REPLACE_STR = "base_content";
	public static final String TITLE_STR = "yongche://map";
	public static final String BASE_URL = TITLE_STR + "." + REPLACE_STR;

	public static String getH5Url(String key) {
		if (key != null) {
			return BASE_URL.replace(REPLACE_STR, key);
		}
		return BASE_URL;
	}

	public static String getJsStr(String js, Object... parameters) {
		if (js != null && parameters != null) {
			try {
				return String.format(js, parameters);
			} catch (NullPointerException e) {
				// TODO: handle exception
			} catch (IllegalFormatException e) {
				// TODO: handle exception
			}
		}
		return null;
	}

	/**
	 * 1.1设置是否是国外
	 */
	public static String JS_SET_OVERSEAS = "javascript:setOverseas(%1$s)";

	/**
	 * 1.2地图加载完毕
	 */
	public static String URL_ON_MAP_LOADED = getH5Url("onmaploaded");

	/**
	 * 1.3地图点击监听
	 */
	public static String URL_ON_MAP_CLICK = getH5Url("onmapclick");
	/**
	 * 1.4监听地图滑动，中心的改变
	 */
	public static String URL_ON_CENTER_CHANGE = getH5Url("centerchange");
	/**
	 * 1.5监听地图缩放的改变
	 */
	public static String URL_ON_ZOOM_CHANGE = getH5Url("zoomchange");
	/**
	 * 1.6设置地图中心
	 */
	public static String JS_SET_CENTER = "javascript:setCenter(%1$f, %2$f)";

	/**
	 * 1.7设置地图缩放
	 */
	public static String JS_SET_ZOOM = "javascript:setZoom(%1$d)";

	/**
	 * 1.8设置地图rotate
	 */
	public static String JS_SET_ROTATE = "javascript:setRotate(%1$f)";

	/**
	 * 1.9监听地图rotate改变
	 */
	public static String URL_ON_ROTATE_CHANGE = getH5Url("rotatechange");

	/**
	 * 1.10设置地图是否可旋转
	 */
	public static String JS_SET_CAN_ROTATE = "javascript:setCanRotate(%1$s)";

	/**
	 * 1.11设置地图type
	 */
	public static String JS_SET_MAP_TYPE = "javascript:setMapType(%1$d)";
	/**
	 * 1.12监听地图type改变
	 */
	public static String URL_MAP_TYPE_CHANGE = getH5Url("maptypechange");

	/**
	 * 1.13清空地图
	 */
	public static String JS_CLEAR_MAP = "javascript:clearMap()";

	/**
	 * 1.14根据latlng坐标转换为point平面坐标
	 */
	public static String JS_FROM_LATLNG_TO_POINT = "javascript:fromLatLngToPoint(%1$d,%2$f,%3$f)";
	/**
	 * 1.14根据latlng坐标转换为point平面坐标
	 */
	public static String URL_ON_FROM_LATLNG_TO_POINT = getH5Url("onfromlatlngtopoint");

	/**
	 * 1.15根据point平面坐标转换为latlng坐标
	 */
	public static String JS_FROM_POINT_TO_LATLNG = "javascript:fromPointToLatLng(%1$d,%2$d,%3$d)";
	/**
	 * 1.15根据point平面坐标转换为latlng坐标
	 */
	public static String URL_ON_FROM_POINT_TO_LATLNG = getH5Url("onfrompointtolatlng");

	/**
	 * 2.1添加单个marker
	 */
	public static String JS_ADD_MARKER = "javascript:addMarker(%1$s)";

	/**
	 * 2.2添加marker list
	 */
	public static String JS_ADD_MARKER_LIST = "javascript:addMarkerList(%1$s)";

	/**
	 * 2.3修改marker坐标
	 */
	public static String JS_CHANGE_MARKER_POI = "javascript:changeMarkerPoi(%1$s,%2$f,%3$f)";

	/**
	 * 2.4删除marker
	 */
	public static String JS_REMOVE_MARKER = "javascript:removeMarker(%1$s)";

	/**
	 * 2.5清空markers
	 */
	public static String JS_CLAEAR_MARKER = "javascript:clearMarkers()";

	/**
	 * 2.6marker点击事件监听
	 */
	public static String URL_ON_MARKER_CLICK = getH5Url("onmarkerclick");

	/**
	 * 3.1添加一条轨迹
	 */
	public static String JS_ADD_POLY_LINE = "javascript:addPolyline(%1$s)";

	/**
	 * 3.2删除一条轨迹
	 */
	public static String JS_REMOVE_POLYLINE = "javascript:removePolyline(%1$s)";

	/**
	 * 3.3清空轨迹
	 */
	public static String JS_CLEAR_POLYLINES = "javascript:clearPolylines()";

	/**
	 * 4.1根据坐标检索位置名称
	 */
	public static String JS_SEARCH_ADDRESS_POI = "javascript:searchAddressPoi(%1$d,%2$f,%3$f)";
	public static String URL_ON_SEARCH_ADDRESS_POI = getH5Url("onsearchaddresspoi");

	/**
	 * 4.2根据关键字检索poi
	 */
	public static String JS_SEARCH_NEAR_BY_POI = "javascript:searchNearbyPoi(%1$d,%2$f,%3$f,%4$d,%5$s)";
	public static String URL_ON_SEARCH_NEAR_BY_POI = getH5Url("onsearchnearbypoi");

	public static String encodeUrlStr(String str) {
//		if (!TextUtils.isEmpty(str)) {
//			return URLEncoder.encode(str).replaceAll("\\+", "%20");
//		}
		return str;
	}

	public static String decodeUrlStr(String str) {
		if (!TextUtils.isEmpty(str)) {
			return URLDecoder.decode(str).replaceAll("%20", "\\+");
		}
		return str;
	}
}
