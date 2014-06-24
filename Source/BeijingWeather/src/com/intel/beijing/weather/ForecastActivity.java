package com.intel.beijing.weather;
import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class ForecastActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//TODO AsyncTask for forecast
		//add in adapter new values
		//do a adapter.notifyDataChanged ()
		
		//initializeaza o lista de test
		String[] valuesAvailable = { "Tomorrow 29", "Aftertomorrow 30"};
		setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, valuesAvailable));

	}

}
