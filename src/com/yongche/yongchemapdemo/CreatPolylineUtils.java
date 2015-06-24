package com.yongche.yongchemapdemo;

import java.util.ArrayList;

import com.yongche.model.PoiModel;
import com.yongche.model.Polyline;

public class CreatPolylineUtils {
	private ArrayList<Polyline> polylineList = new ArrayList<Polyline>();

	public synchronized Polyline addPolyline() {
		synchronized (polylineList) {
			int item = polylineList.size() + 1;
			if (item < 4) {
				Polyline polyline = new Polyline(item + "", 2, "#ec4949", "",
						craeatPois(item));
				polylineList.add(polyline);
				return polyline;
			}
		}
		return null;
	}

	public synchronized void removeLine(String id) {
		synchronized (polylineList) {
			for (int i = 0; i < polylineList.size(); i++) {
				if (polylineList.get(i).getId().equals(id)) {
					polylineList.remove(i);
					return;
				}
			}
		}
	}

	public synchronized void celarLines() {
		synchronized (polylineList) {
			polylineList.clear();
		}
	}

	private ArrayList<PoiModel> craeatPois(int item) {
		ArrayList<PoiModel> poiModels = new ArrayList<PoiModel>();
		if (item == 0) {
			poiModels.add(new PoiModel(39.9840715, 116.3078685));
			poiModels.add(new PoiModel(39.976001, 116.317529));
			poiModels.add(new PoiModel(39.9756598, 116.3295882));
			poiModels.add(new PoiModel(39.9419932, 116.3438951));
			poiModels.add(new PoiModel(39.9300154, 116.359173));
		} else if (item == 1) {
			poiModels.add(new PoiModel(39.959404, 116.3163571));
			poiModels.add(new PoiModel(39.9622329, 116.2670045));
			poiModels.add(new PoiModel(39.9879506, 116.2470059));
			poiModels.add(new PoiModel(40.0069531, 116.2785058));
			poiModels.add(new PoiModel(39.9993922, 116.3216786));
			poiModels.add(new PoiModel(39.990844, 116.3438229));
		} else if (item == 2) {
			poiModels.add(new PoiModel(39.9791379, 116.3325791));
			poiModels.add(new PoiModel(39.9624303, 116.3127522));
			poiModels.add(new PoiModel(39.9646012, 116.2879472));
			poiModels.add(new PoiModel(39.9489427, 116.3106923));
		}

		return poiModels;

	}
}
