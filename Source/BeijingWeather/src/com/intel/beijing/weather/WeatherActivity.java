package com.intel.beijing.weather;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class WeatherActivity extends Activity {
	TextView weatherText;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_weather);
		
			//setup proxy because you are in I. network
		//System.setProperty("http.proxyHost", "prox here");
		//System.setProperty("http.proxyPort", "port here");
		
		//prepare to get the data from url
			String city = "";
		String urlWeather = "http://api.openweathermap.org/data/2.5/weather?q=" + city + ",en&units=metric";
		
		//display the weather response
		weatherText = (TextView) findViewById(R.id.weatherText);
		new WeatherUpdater().execute(urlWeather);
	}

	class WeatherUpdater extends AsyncTask<String, Integer, String> {
		private ProgressDialog progress;

		@Override
		protected String doInBackground(String... url) {
			try {
				// transporter for our in->out call
				HttpClient client = new DefaultHttpClient();
				HttpGet httpget = new HttpGet(url[0]);
				String response = client.execute(httpget, new BasicResponseHandler());

				return response;

			} catch (Throwable e) {
				Log.d("Weather", e.getMessage());
				return "Error on posting" + e.getMessage();
			}
		}

		@Override
		protected void onPostExecute(String result) {
			progress.dismiss();

			// parse the JSON and get the temperature
			try {
				JSONObject jObj = new JSONObject(result);
				JSONObject jsonObj = jObj.getJSONObject("main");
				
				String weatherString = "";
				weatherString = new Float(jsonObj.getString("temp")).toString();
				weatherText.setText(weatherString);

			} catch (JSONException e) {
				e.printStackTrace();
			}
		}

		@Override
		protected void onPreExecute() {
			progress = ProgressDialog.show(WeatherActivity.this,
					"Get the weather", "Please wait ....");
		}

	}

}
