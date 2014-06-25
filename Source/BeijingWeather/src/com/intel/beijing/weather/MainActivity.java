package com.intel.beijing.weather;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//setup proxy because you are in I. network
		//System.setProperty("http.proxyHost", "prox here");
		//System.setProperty("http.proxyPort", "port here");
	}
	
	public void weatherCall (View v)
	{
		//jump in WeatherActivity
		String valueCity = ((EditText) findViewById(R.id.textCity)).getText().toString();
		
		Intent intent = new Intent(this, WeatherActivity.class);
		intent.putExtra("city", valueCity );
		startActivity(intent);
	}
	
	public void forecastCall (View v)
	{
		//jump in ForecastActivity
		String valueCity = ((EditText) findViewById(R.id.textCity)).getText().toString();
		
		Intent intent = new Intent(this, ForecastActivity.class);
		intent.putExtra("city", valueCity );
		startActivity(intent);	
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		if (item.getItemId() == R.id.action_START)
		{
			//start the weather service
			startService(new Intent(this, WeatherService.class));
		}
		else if (item.getItemId() == R.id.action_STOP)
		{
			//stop the weather service
			stopService(new Intent(this, WeatherService.class));
		}
		
		return  (true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
