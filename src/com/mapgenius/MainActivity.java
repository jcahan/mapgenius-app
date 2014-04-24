package com.mapgenius;

import java.util.Timer;
import java.util.TimerTask;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks;
import com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
//https://www.youtube.com/watch?v=FKd1yVpIXHo for setting it up
//https://www.youtube.com/watch?v=23i5Z8m1pok
public class MainActivity extends SherlockFragmentActivity implements OnConnectionFailedListener, ConnectionCallbacks, LocationListener {

	private LocationClient aClient;
	private LocationRequest request = 
			LocationRequest.create().setInterval(50000).setFastestInterval(16).setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY).setSmallestDisplacement(0);


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initializeActionBarLayout();

		//Now testing LocationClient
		if(checkIfGooglePlay()){
			System.out.println("enters past line 35");
			aClient = new LocationClient(this, this, this);
			aClient.connect();
		}
		Timer aTimer = new Timer();
		aTimer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				if(aClient.isConnected()) {
					System.out.println("client is connected!!");
				} else {
					System.out.println("client isn't connected!");
				}
				Location aLocation = aClient.getLastLocation();
				if(aLocation!=null){
					System.out.println("GETTING LOCATION!!!");
					System.out.println(String.format("the latitude is %s", aLocation.getLatitude()));
				} else {
					System.out.println("Location is null!!");
				}
			}
		}, 15000, 30000);
	}
	private void initializeActionBarLayout() {
		ActionBar actionbar = getSupportActionBar();
		actionbar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionbar.setTitle("Tabbed Layout");

		ActionBar.Tab Frag1Tab = actionbar.newTab().setText("Fragment 1");
		ActionBar.Tab Frag2Tab = actionbar.newTab().setText("Fragment 2");
		ActionBar.Tab Frag3Tab = actionbar.newTab().setText("Fragment 3");
		ActionBar.Tab Frag4Tab = actionbar.newTab().setText("Fragment 4");

		Fragment Fragment1 = new Fragment_1();
		Fragment Fragment2 = new Fragment_1();
		Fragment Fragment3 = new Fragment_1();
		Fragment Fragment4 = new Fragment_1();

		Frag1Tab.setTabListener(new MyTabsListener(Fragment1));
		Frag2Tab.setTabListener(new MyTabsListener(Fragment2));
		Frag3Tab.setTabListener(new MyTabsListener(Fragment3));
		Frag4Tab.setTabListener(new MyTabsListener(Fragment4));

		actionbar.addTab(Frag1Tab);
		actionbar.addTab(Frag2Tab);
		actionbar.addTab(Frag3Tab);
		actionbar.addTab(Frag4Tab);
	}


	protected Boolean checkIfGooglePlay() {
		int errorCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
		if (errorCode != ConnectionResult.SUCCESS) {
			return false; 
		}
		return true;
	}

	class MyTabsListener implements ActionBar.TabListener {
		public Fragment fragment;

		public MyTabsListener(Fragment fragment){
			this.fragment = fragment;
		}

		@Override
		public void onTabSelected(Tab tab, FragmentTransaction ft) {
			// TODO Auto-generated method stub
			ft.replace(R.id.fragment_container, fragment);
		}

		@Override
		public void onTabUnselected(Tab tab, FragmentTransaction ft) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onTabReselected(Tab tab, FragmentTransaction ft) {
			// TODO Auto-generated method stub

		}
	}

	@Override
	public void onConnected(Bundle arg0) {
		System.out.println("just connected!!");
		aClient.requestLocationUpdates(request, this);
	}
	@Override
	public void onDisconnected() {
		// TODO Auto-generated method stub

	}
	@Override
	public void onConnectionFailed(ConnectionResult arg0) {
		// TODO Auto-generated method stub

	}
	@Override
	public void onLocationChanged(Location location) {
		double latitude = location.getLatitude();
		double longitude = location.getLongitude();
		System.out.println("THIS IS THE LATITUDE: " + latitude);
		System.out.println("THIS IS THE LONGITUDE: " + longitude);
	}
}

