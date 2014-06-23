package com.intel.beijing.weather;

import org.apache.http.HttpConnection;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public void getWeatherData (View v)
	{
		//display visual confirmation
		Toast.makeText(this, "Click on weather search.", Toast.LENGTH_LONG ).show();

		//setup proxy because you are in I. network
		//System.setProperty("http.proxyHost", "prox here");
		//System.setProperty("http.proxyPort", "port here");
		
		//prepare to get the data from url
		EditText textCity = (EditText) findViewById(R.id.textCity);
		String city = textCity.getEditableText().toString();
		String urlWeather = "http://api.openweathermap.org/data/2.5/forecast?q=" + city + ",en&units=metric";
		
		//TODO get data from urlWeather using HttpConnection ....
		String weatherResult = "";
		
		//display the weather response
		TextView weatherText = (TextView) findViewById(R.id.weatherText);
		weatherText.setText(weatherResult);
	}
	
	
}
