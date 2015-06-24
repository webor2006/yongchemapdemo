package com.yongche.yongchemapdemo;

import java.net.URLEncoder;
import java.util.ArrayList;

import com.yongche.map.H5UrlUtils;
import com.yongche.model.Marker;

public class CreatMarkerUtils {
	private ArrayList<Marker> markers = new ArrayList<Marker>();
	private double baseLat = (double) 39.9862827;
	private double baselng = (double) 116.302101;
	private double addCount = 0.003;

	public synchronized ArrayList<Marker> addMarkers(int count) {
		ArrayList<Marker> adds = new ArrayList<Marker>();
		for (int i = 0; i < count; i++) {
			synchronized (markers) {
				if (markers.size() == 0 && adds.size() == 0) {
					adds.add(new Marker("1", baseLat + addCount,
							baselng + addCount, (1 % 4) + 1, H5UrlUtils.encodeUrlStr("Vereyskaya ulitsa, 6, Sankt-Peterburg, 俄罗斯190013"), true));
				} else  {
					String preId;
					if (adds.size() == 0) {
						preId = markers.get(markers.size() - 1).getId();
					}else {
						preId = adds.get(adds.size() - 1).getId();
					}
					int currentId = Integer.parseInt(preId) + 1;
					adds.add(new Marker(currentId + "", baseLat
							+ (addCount * currentId), baselng
							+ (addCount * currentId), (currentId % 4) + 1, "",
							false));
				} 
			}
		}
		markers.addAll(adds);
		return adds;		
	}

	public synchronized void removeMarker(String id) {
		synchronized (markers) {
			for (int i = 0; i < markers.size(); i++) {
				if (markers.get(i).getId().equals(id)) {
					markers.remove(i);
					return;
				}
			}
		}
	}

	public synchronized void celarMarker() {
		synchronized (markers) {
			markers.clear();
		}
	}
}
