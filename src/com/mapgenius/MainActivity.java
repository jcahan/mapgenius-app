package com.mapgenius;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
//https://www.youtube.com/watch?v=FKd1yVpIXHo for setting it up
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
