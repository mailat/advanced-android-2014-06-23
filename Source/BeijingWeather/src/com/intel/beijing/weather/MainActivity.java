package com.intel.beijing.weather;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
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
	

}
