package com.intel.beijing.weather;

import java.util.ArrayList;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class ForecastActivity extends ListActivity {
	ArrayList<String> list;
	ArrayAdapter<String> adapter;
	String request;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

        //setup the list activity
		list = new ArrayList<String>();
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
		setListAdapter(adapter);
		
		// get the passed values from the MainActivty and prepare tge
		Intent intent = getIntent();
		String city = intent.getStringExtra("city");
		city = city.replaceAll(" ", "%20");
		request = "http://api.openweathermap.org/data/2.5/forecast?q=" + city
				+ ",en&units=metric";

		//get the forecast
		new ForecastUpdater().execute();
	}
	
	class ForecastUpdater extends AsyncTask<String, Integer, String> {
		private ProgressDialog progress;

		@Override
		protected String doInBackground(String... statuses) {
			try {
				// transporter for our in->out call
				HttpClient client = new DefaultHttpClient();
				HttpGet httpget = new HttpGet(request);
				String response = client.execute(httpget, new BasicResponseHandler());
				return response;
			} catch (Throwable e) {
				return "Error on posting" + e.getMessage();
			}
		}

		@Override
		protected void onPostExecute(String result) {
			progress.dismiss();

			//parse the JSON and get the forecast
			try {
				JSONObject jobject = new JSONObject(result);
				JSONArray jArray = jobject.getJSONArray("list");
				list.clear();
				
				JSONObject jObject;
				for (int i = 0; i < jArray.length(); i++) {
					jObject = jArray.getJSONObject(i);
					list.add("Temperature: " + 
					jObject.getJSONObject("main").getString("temp"));
				}
				adapter.notifyDataSetChanged();
				
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}

		@Override
		protected void onPreExecute() {
			progress = ProgressDialog.show(ForecastActivity.this,
					"Get the forecast", "Please wait ....");
		}

	}
	

}
