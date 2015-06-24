package com.yongche.yongchemapdemo;

import android.app.Application;

/**
 * @author cc
 */
public class DemoApplication extends Application {

	private static final String TAG = DemoApplication.class.getSimpleName();
	public static final String DEVICE_UUID = "device_uuid";

	private static DemoApplication app;

	@Override
	public void onCreate() {
		super.onCreate();
		app = this;
	}

	public static final DemoApplication getApp() {
		return app;
	}

}
