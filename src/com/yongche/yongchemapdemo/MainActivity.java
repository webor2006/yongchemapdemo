package com.yongche.yongchemapdemo;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.yongche.map.H5UrlUtils;
import com.yongche.map.OnMapURLListener;
import com.yongche.map.PraseUrlUtils;
import com.yongche.model.AddressModel;
import com.yongche.model.Marker;
import com.yongche.model.PoiModel;
import com.yongche.model.Polyline;
import com.yongche.util.CommonUtils;
import com.yongche.util.LogUtils;

/**
 * @author cc
 * 
 */
public class MainActivity extends Activity implements OnClickListener,
		OnMapURLListener {
	private WebView mapWv;
	private Button setOverseasBtn, setCenterBtn, setZoomBtn, setCanMoveBtn,
			clearMapBtn;
	private TextView overseasTv, onmapLoadTv, mapClickTv, canMoveTv, setUrlBtn;
	private EditText latTv, lngTv, zoomTv, urlEt;
	private boolean isOverseas = true;
	private int zoom = 12;
	private boolean canMove = true;

	private PoiModel centerPoi, clickPoi;
	private PraseUrlUtils praseUrlUtils;
	private Button baseBtn, markerBtn, lineBtn, searchBtn;
	private LinearLayout baseLy, markerLy, lineLy, searchLy;

	private Button addMarkerBtn, addMarkerListBtn, removeMerkerBtn,
			changeMarkerBtn, celarMarkerBtn;
	private Button addLineBtn, removeLineBtn, celarLineBtn;
	private EditText removeMarkerIdEt, removeLineIdEt, changeMarkerIdEt,
			changeMarkerLatEt, changeMarkerLngEt;
	private TextView markerClickTv;

	private EditText searchLatEt, searchLngEt;
	private Button searchPoiBtn;
	private TextView searchResult;
	private CreatMarkerUtils creatMarkerUtils = new CreatMarkerUtils();
	private CreatPolylineUtils creatPolylineUtils = new CreatPolylineUtils();
	private TextView toPointPTv, toLatlngPTv, toPointResultTv,
			toLatlngResultTv;
	private Button toPointBtn, toLatlngBtn;
	private EditText nearbyLatEt, nearbyLngEt, nearbyRadiusEt, nearbyKeyEt;
	private Button searchNearbyBtn;
	private TextView searchNearbyResultTv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		praseUrlUtils = new PraseUrlUtils(this);
		findViewById();
		iniData();
		iniView();
	}

	private void initWebView() {
		WebSettings webViewSettings = mapWv.getSettings();
		webViewSettings.setSupportZoom(true);
		webViewSettings.setBuiltInZoomControls(false);
		webViewSettings.setJavaScriptEnabled(true);
		webViewSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
		// webViewSettings.setDomStorageEnabled(true);
		// webViewSettings.setAppCacheMaxSize(1024*1024*8);//设置缓冲大小，我设的是8M
		// String appCacheDir = this.getApplicationContext().getDir("cache",
		// Context.MODE_PRIVATE).getPath();
		// webViewSettings.setAppCachePath(appCacheDir);
		// webViewSettings.setAllowFileAccess(true);
		// webViewSettings.setAppCacheEnabled(true);
		// webViewSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);

		mapWv.setWebViewClient(new WebViewClient() {

			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				if (url.startsWith(H5UrlUtils.TITLE_STR)) {
					CommonUtils.showToast(MainActivity.this, url);
					LogUtils.e("shouldOverrideUrlLoading", "msg:" + url);
					praseUrlUtils.prase(url);
					return true;
				}
				return super.shouldOverrideUrlLoading(view, url);

			}

			@Override
			public void onPageFinished(WebView view, String url) {

				if (url.startsWith(urlEt.getText().toString())) {
					mapWv.loadUrl(H5UrlUtils.getJsStr(
							H5UrlUtils.JS_SET_OVERSEAS, false));
				}
				super.onPageFinished(view, url);
			}

			@Override
			public void onReceivedError(WebView view, int errorCode,
					String description, String failingUrl) {
				CommonUtils.showToast(MainActivity.this, "description:"
						+ description + ".....failingUrl:" + failingUrl);
				super.onReceivedError(view, errorCode, description, failingUrl);
			}

		});
		mapWv.setWebChromeClient(new WebChromeClient() {
			@Override
			public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
				// TODO Auto-generated method stub
				if (consoleMessage.message()
						.contains("Uncaught ReferenceError")) {
					CommonUtils.showToast(MainActivity.this,
							consoleMessage.message());
				}
				return super.onConsoleMessage(consoleMessage);
			}
		});

	}

	private void iniData() {
		centerPoi = new PoiModel((double) 39.9862827, (double) 116.302101);
		clickPoi = new PoiModel((double) 39.9862827, (double) 116.302101);
	}

	private void findViewById() {
		mapWv = (WebView) findViewById(R.id.webView1);
		setOverseasBtn = (Button) findViewById(R.id.set_overseas_btn);
		setOverseasBtn.setOnClickListener(this);
		overseasTv = (TextView) findViewById(R.id.set_overseas_result_tv);
		onmapLoadTv = (TextView) findViewById(R.id.on_map_loaded_tv);
		mapClickTv = (TextView) findViewById(R.id.on_map_click_tv);
		setCenterBtn = (Button) findViewById(R.id.set_center_btn);
		setCenterBtn.setOnClickListener(this);
		setZoomBtn = (Button) findViewById(R.id.set_zoom_btn);
		setZoomBtn.setOnClickListener(this);
		latTv = (EditText) findViewById(R.id.on_center_lat_et);
		lngTv = (EditText) findViewById(R.id.on_center_lng_et);
		zoomTv = (EditText) findViewById(R.id.on_zoom_et);
		setCanMoveBtn = (Button) findViewById(R.id.set_can_move_btn);
		setCanMoveBtn.setOnClickListener(this);
		clearMapBtn = (Button) findViewById(R.id.clear_map_btn);
		clearMapBtn.setOnClickListener(this);
		urlEt = (EditText) findViewById(R.id.url_et);
		setUrlBtn = (Button) findViewById(R.id.set_url_btn);
		setUrlBtn.setOnClickListener(this);
		baseBtn = (Button) findViewById(R.id.base_btn);
		markerBtn = (Button) findViewById(R.id.marker_btn);
		lineBtn = (Button) findViewById(R.id.line_btn);
		searchBtn = (Button) findViewById(R.id.search_btn);
		baseBtn.setOnClickListener(this);
		markerBtn.setOnClickListener(this);
		lineBtn.setOnClickListener(this);
		searchBtn.setOnClickListener(this);
		baseLy = (LinearLayout) findViewById(R.id.base_ly);
		markerLy = (LinearLayout) findViewById(R.id.marker_ly);
		lineLy = (LinearLayout) findViewById(R.id.line_ly);
		searchLy = (LinearLayout) findViewById(R.id.search_ly);
		canMoveTv = (TextView) findViewById(R.id.can_move_tv);

		addMarkerBtn = (Button) findViewById(R.id.add_marker_btn);
		addMarkerListBtn = (Button) findViewById(R.id.add_marker_list_btn);
		removeMerkerBtn = (Button) findViewById(R.id.remove_marker_btn);
		celarMarkerBtn = (Button) findViewById(R.id.clear_marker_btn);
		addLineBtn = (Button) findViewById(R.id.add_line_btn);
		removeLineBtn = (Button) findViewById(R.id.remove_line_btn);
		celarLineBtn = (Button) findViewById(R.id.clear_line_btn);
		changeMarkerBtn = (Button) findViewById(R.id.change_marker_btn);
		changeMarkerBtn.setOnClickListener(this);

		addMarkerBtn.setOnClickListener(this);
		addMarkerListBtn.setOnClickListener(this);
		removeMerkerBtn.setOnClickListener(this);
		celarMarkerBtn.setOnClickListener(this);
		addLineBtn.setOnClickListener(this);
		removeLineBtn.setOnClickListener(this);
		celarLineBtn.setOnClickListener(this);

		removeMarkerIdEt = (EditText) findViewById(R.id.remove_marker_id);
		removeLineIdEt = (EditText) findViewById(R.id.remove_line_id);

		changeMarkerLatEt = (EditText) findViewById(R.id.change_marker_lat);
		changeMarkerLngEt = (EditText) findViewById(R.id.change_marker_lng);
		changeMarkerIdEt = (EditText) findViewById(R.id.change_marker_id);
		markerClickTv = (TextView) findViewById(R.id.marker_click_tv);

		searchLatEt = (EditText) findViewById(R.id.search_lat_et);
		searchLngEt = (EditText) findViewById(R.id.search_lng_et);
		searchPoiBtn = (Button) findViewById(R.id.search_poi_btn);
		searchPoiBtn.setOnClickListener(this);
		searchResult = (TextView) findViewById(R.id.search_result);

		toPointPTv = (TextView) findViewById(R.id.to_point_p_tv);
		toLatlngPTv = (TextView) findViewById(R.id.to_latlng_p_tv);
		toPointResultTv = (TextView) findViewById(R.id.to_point_result_tv);
		toLatlngResultTv = (TextView) findViewById(R.id.to_latlng_result_tv);
		toPointBtn = (Button) findViewById(R.id.to_point_btn);
		toPointBtn.setOnClickListener(this);
		toLatlngBtn = (Button) findViewById(R.id.to_latlng_btn);
		toLatlngBtn.setOnClickListener(this);

		nearbyLatEt = (EditText) findViewById(R.id.nearby_lat_et);
		nearbyLngEt = (EditText) findViewById(R.id.nearby_lng_et);
		nearbyRadiusEt = (EditText) findViewById(R.id.nearby_radius_et);
		nearbyKeyEt = (EditText) findViewById(R.id.nearby_key_et);
		searchNearbyBtn = (Button) findViewById(R.id.search_nearby_btn);
		searchNearbyBtn.setOnClickListener(this);
		searchNearbyResultTv = (TextView) findViewById(R.id.search_nearby_result);
	}

	private void iniView() {
		canMoveTv.setText(canMove + "");
		urlEt.setText(H5UrlUtils.LOAD_URL);
		latTv.setText(centerPoi.getLat() + "");
		lngTv.setText(centerPoi.getLng() + "");
		changeMarkerLatEt.setText(centerPoi.getLat() + "");
		changeMarkerLngEt.setText(centerPoi.getLng() + "");
		searchLatEt.setText(centerPoi.getLat() + "");
		searchLngEt.setText(centerPoi.getLng() + "");
		zoomTv.setText(zoom + "");
		initWebView();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.base_btn:
			setCurrent(0);
			break;
		case R.id.marker_btn:
			setCurrent(1);
			break;
		case R.id.line_btn:
			setCurrent(2);
			break;
		case R.id.search_btn:
			setCurrent(3);
			break;
		case R.id.set_url_btn:
			if (!urlEt.getText().toString().equals("")) {
				setIniUrl();
				loadJs(setCenter(0, 0));
				loadJs(setCenter((double) 39.9862827, (double) 116.302101));
			}
			break;

		case R.id.set_overseas_btn:
			loadJs(setOverSeas(!isOverseas));
			isOverseas = !isOverseas;
			overseasTv.setText(isOverseas + "");
			break;
		case R.id.set_center_btn:
			try {
				loadJs(setCenter(
						Double.parseDouble(latTv.getText().toString()),
						Double.parseDouble(lngTv.getText().toString())));
			} catch (Exception e) {
				// TODO: handle exception
			}

			break;
		case R.id.set_zoom_btn:
			try {
				loadJs(setZoom(Integer.parseInt(zoomTv.getText().toString())));
			} catch (Exception e) {
				// TODO: handle exception
			}
			break;
		case R.id.set_can_move_btn:
			canMove = !canMove;
			canMoveTv.setText(canMove + "");
			break;
		case R.id.clear_map_btn:
			clearMap();
			// clearMarker();
			// clearPolyline();
			break;
		case R.id.add_marker_btn:
			addMarker();
			break;
		case R.id.add_marker_list_btn:
			addMarkerList();
			break;
		case R.id.remove_marker_btn:
			removeMarker();
			break;
		case R.id.clear_marker_btn:
			clearMarker();
			break;
		case R.id.add_line_btn:
			addPolyline();
			break;
		case R.id.remove_line_btn:
			removePolyline();
			break;
		case R.id.clear_line_btn:
			clearPolyline();
			break;
		case R.id.change_marker_btn:
			changeMarker();
			break;
		case R.id.search_poi_btn:
			searchAddressPoi();
			break;
		case R.id.to_point_btn:
			fromLatLngToPoint();

			break;
		case R.id.to_latlng_btn:
			fromPointToLatLng();
			break;
		case R.id.search_nearby_btn:
			searchNearby();
			break;
		default:
			break;
		}

	}

	private String setOverSeas(boolean isOverseas) {
		return H5UrlUtils.getJsStr(H5UrlUtils.JS_SET_OVERSEAS, isOverseas + "");
	}

	private String setCenter(double lat, double lng) {
		return H5UrlUtils.getJsStr(H5UrlUtils.JS_SET_CENTER, lat, lng);

	}

	private String setZoom(int zoom) {
		return H5UrlUtils.getJsStr(H5UrlUtils.JS_SET_ZOOM, zoom);

	}

	private void clearMap() {
		loadJs(H5UrlUtils.JS_CLEAR_MAP);
	}

	@Override
	public void onMapLoaded(boolean result) {
		onmapLoadTv.setText(result ? "success" : "failed");
		CommonUtils.showToast(MainActivity.this, "onMapLoaded:" + result);
		isMapLoaded = true;
		setJsCacheList();
	}

	@Override
	public void onMapClick(double lat, double lng) {
		mapClickTv.setText("lat:" + lat + ",lng:" + lng);
		clickPoi = new PoiModel(lat, lng);
		searchLatEt.setText(lat + "");
		searchLngEt.setText(lng + "");
		nearbyLatEt.setText(lat + "");
		nearbyLngEt.setText(lng + "");
		toPointPTv.setText("lat:" + lat + ",lng:" + lng);
		CommonUtils.showToast(MainActivity.this, "onMapClick:---" + "lat:"
				+ lat + ",lng:" + lng);

	}

	@Override
	public void onCenterChange(double lat, double lng) {
		latTv.setText(lat + "");
		lngTv.setText(lng + "");
		centerPoi.setLat(lat);
		centerPoi.setLng(lng);

	}

	@Override
	public void onZoomChange(int zoom) {
		zoomTv.setText(zoom + "");
		MainActivity.this.zoom = zoom;

	}

	@Override
	public void onMarkerClick(Marker marker) {
		String msg = "id:" + marker.getId() + ",lat:" + marker.getLat()
				+ ",lng:" + marker.getLng();
		markerClickTv.setText(msg);
		CommonUtils.showToast(this, msg);
	}

	@Override
	public void onSearchAddressResult(int tag, double lat, double lng,
			String address, String city) {
		String msg = "tag:" + tag + "lat:" + lat + ",lng:" + lng + ",address:"
				+ address + "city:" + city;
		searchResult.setText(msg);
		CommonUtils.showToast(this, msg);
	}

	private void setIniUrl() {
		if (mapWv != null) {
			mapWv.loadUrl(urlEt.getText().toString() + "&lat="
					+ centerPoi.getLat() + "&lng=" + centerPoi.getLng()
					+ "&zoom=" + zoom + "&canmove=" + canMove);
		}
	}

	private int currentCount = 0;

	private void setCurrent(int count) {
		if (count != currentCount) {
			currentCount = count;
			baseLy.setVisibility(count == 0 ? View.VISIBLE : View.GONE);
			markerLy.setVisibility(count == 1 ? View.VISIBLE : View.GONE);
			lineLy.setVisibility(count == 2 ? View.VISIBLE : View.GONE);
			searchLy.setVisibility(count == 3 ? View.VISIBLE : View.GONE);
		}
	}

	private void addMarker() {
		Marker marker = creatMarkerUtils.addMarkers(1).get(0);
		String json = new Gson().toJson(marker);
		loadJs(H5UrlUtils.getJsStr(H5UrlUtils.JS_ADD_MARKER, json));
	}

	private void addMarkerList() {
		ArrayList<Marker> markerList = creatMarkerUtils.addMarkers(5);
		String json = new Gson().toJson(markerList);
		loadJs(H5UrlUtils.getJsStr(H5UrlUtils.JS_ADD_MARKER_LIST, json));
	}

	private void removeMarker() {
		String id = removeMarkerIdEt.getText().toString();
		if (!id.equals("")) {
			loadJs(H5UrlUtils.getJsStr(H5UrlUtils.JS_REMOVE_MARKER, id));
			creatMarkerUtils.removeMarker(id);
		}
	}

	private void clearMarker() {
		loadJs(H5UrlUtils.JS_CLAEAR_MARKER);
		creatMarkerUtils.celarMarker();
	}

	private void addPolyline() {
		Polyline polyline = creatPolylineUtils.addPolyline();
		if (polyline != null) {
			String json = new Gson().toJson(polyline);
			loadJs(H5UrlUtils.getJsStr(H5UrlUtils.JS_ADD_POLY_LINE, json));
		}
	}

	private void removePolyline() {
		String id = removeLineIdEt.getText().toString();
		if (!id.equals("")) {
			loadJs(H5UrlUtils.getJsStr(H5UrlUtils.JS_REMOVE_POLYLINE, id));
			creatPolylineUtils.removeLine(id);
		}
	}

	private void clearPolyline() {
		loadJs(H5UrlUtils.JS_CLEAR_POLYLINES);
		creatPolylineUtils.celarLines();
	}

	private void changeMarker() {
		if (changeMarkerIdEt.getText().equals("")
				|| changeMarkerLatEt.getText().equals("")
				|| changeMarkerLngEt.getText().equals("")) {
			return;
		}
		try {
			loadJs(H5UrlUtils.getJsStr(H5UrlUtils.JS_CHANGE_MARKER_POI,
					changeMarkerIdEt.getText().toString(),
					Double.parseDouble(changeMarkerLatEt.getText().toString()),
					Double.parseDouble(changeMarkerLngEt.getText().toString())));
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	private void searchAddressPoi() {
		if (!searchLatEt.getText().toString().equals("")
				&& !searchLngEt.getText().toString().equals("")) {
			try {
				loadJs(H5UrlUtils.getJsStr(H5UrlUtils.JS_SEARCH_ADDRESS_POI, 5,
						Double.parseDouble(searchLatEt.getText().toString()),
						Double.parseDouble(searchLngEt.getText().toString())));
			} catch (Exception e) {
				// TODO: handle exception
			}

		}
	}

	private void fromLatLngToPoint() {
		toPointPTv.setText("lat:" + clickPoi.getLat() + ",lng:"
				+ clickPoi.getLng());
		loadJs(H5UrlUtils.getJsStr(H5UrlUtils.JS_FROM_LATLNG_TO_POINT, 5,
				clickPoi.getLat(), clickPoi.getLng()));
	}

	private int mx = 0, my = 0;

	@Override
	public void onFromLatLngToPoint(int tag, double lat, double lng, int x,
			int y) {
		toPointResultTv.setText("tag:" + tag + "lat:" + lat + ",lng:" + lng
				+ "x:" + CommonUtils.dip2px(this, x) + "y:"
				+ CommonUtils.dip2px(this, y));
		mx = x;
		my = y;
		toLatlngPTv.setText("x:" + CommonUtils.dip2px(this, x) + "y:"
				+ CommonUtils.dip2px(this, y));
	}

	private void fromPointToLatLng() {
		toLatlngPTv.setText("x:" + CommonUtils.dip2px(this, mx) + "y:"
				+ CommonUtils.dip2px(this, my));
		loadJs(H5UrlUtils.getJsStr(H5UrlUtils.JS_FROM_POINT_TO_LATLNG, 5, mx,
				my));
	}

	@Override
	public void onFromPointToLatLng(int tag, double lat, double lng, int x,
			int y) {
		toLatlngResultTv.setText("tag:" + tag + "lat:" + lat + ",lng:" + lng
				+ "x:" + CommonUtils.dip2px(this, x) + "y:"
				+ CommonUtils.dip2px(this, y));

	}

	private void searchNearby() {
		if (nearbyLatEt.getText().toString().equals("")
				|| nearbyLngEt.getText().toString().equals("")
				|| nearbyKeyEt.getText().toString().equals("")
				|| nearbyRadiusEt.getText().toString().equals("")) {
			return;
		} else {
			try {
				loadJs(H5UrlUtils.getJsStr(H5UrlUtils.JS_SEARCH_NEAR_BY_POI, 5,
						Double.parseDouble(nearbyLatEt.getText().toString()),
						Double.parseDouble(nearbyLngEt.getText().toString()),
						Integer.parseInt(nearbyRadiusEt.getText().toString()),
						H5UrlUtils.encodeUrlStr(nearbyKeyEt.getText()
								.toString())));
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	@Override
	public void onSearchNearbyPoi(int tag, ArrayList<AddressModel> addressModels) {
		String result = new Gson().toJson(addressModels);
		searchNearbyResultTv.setText("tag:" + tag + ",result:" + result);

	}

	private boolean isMapLoaded;
	private ArrayList<String> jsCacheList = new ArrayList<String>();

	private void loadJs(String jsStr) {
		if (TextUtils.isEmpty(jsStr)) {
			return;
		}
		if (isMapLoaded && (jsCacheList == null || jsCacheList.size() == 0)) {
			mapWv.loadUrl(jsStr);
		} else {
			jsCacheList.add(jsStr);
		}
	}

	private void setJsCacheList() {
		while (jsCacheList != null && jsCacheList.size() != 0) {
			if (!TextUtils.isEmpty(jsCacheList.get(0))) {
				mapWv.loadUrl(jsCacheList.get(0));
			}
			jsCacheList.remove(0);
		}
	}
}
