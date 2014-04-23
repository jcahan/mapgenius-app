package com.mapgenius;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.SherlockFragmentActivity;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
//https://www.youtube.com/watch?v=FKd1yVpIXHo for setting it up
public class MainActivity extends SherlockFragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
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
}

