package com.intel.beijing.weather;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class WeatherService extends Service {

	static final int DELAY = 6000; // 1 minute delay between polling data
	private boolean isAllowToRun = false; // we keep the state of the worker thread
	Updater updater;
	
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		Log.d("Weather", "I am on onCreate()");
		
		updater = new Updater();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		super.onStartCommand(intent, flags, startId);
		Log.d("Weather", "I am on onStartCommand()");
		
		//we check if our worker is maybe already running
		// if not start the worker
		if (!this.isAllowToRun)
		{
			this.isAllowToRun = true;
			this.updater.start();
		}
			
		return START_STICKY;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.d("Weather", "I am on onDestroy()");
		
		//just to be sure we close the threader correctly
		this.isAllowToRun= false;
		this.updater.interrupt();
		this.updater = null;
	}
	
	/**
	 * Updater thread for getting the actual weather 
	 */
	private class Updater extends Thread{

		private Updater()
		{
			super ("WeatherService");
		}

		@Override
		public void run() {
			WeatherService weatherService = WeatherService.this;
			
			//check the flag if we are still allowed to run			
			while (weatherService.isAllowToRun)
			{	
				//get from url the weather and parse it
				try {
					// transporter for our in->out call
					String urlWeather = "http://api.openweathermap.org/data/2.5/weather?q=" + "Beijing" + ",en&units=metric";
					
					HttpClient client = new DefaultHttpClient();
					HttpGet httpget = new HttpGet(urlWeather);
					String response = client.execute(httpget, new BasicResponseHandler());
	
					JSONObject jObj = new JSONObject(response);
					JSONObject jsonObj = jObj.getJSONObject("main");
					
					String weatherString = "";
					weatherString = new Float(jsonObj.getString("temp")).toString();
					Log.d("Weather", "The new weather is: "+weatherString);
	
					//go to sleep for 1 minute
					Thread.sleep(DELAY);
					
				} catch (Throwable e) {
					weatherService.isAllowToRun = false;
					//Log.d("Weather", e.getMessage());
				}
			}
		}
		
	}
}
