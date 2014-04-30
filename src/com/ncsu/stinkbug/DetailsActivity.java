package com.ncsu.stinkbug;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

import com.viewpagerindicator.CirclePageIndicator;

public class DetailsActivity extends FragmentActivity implements OnPageChangeListener {
	private TextView mTextFooter;
	private int mActiveFrag;
	private String[] footer = { "STINK BUG INTRODUCTION", "STINK BUG PESTS OF COTTON", "STINK BUG DAMAGE SYMPTOMS",
			"STINK BUG SCOUTING STEPS", "STINK BUG DYNAMIC THRESHOLD", "STINK BUG DECISION AID CARD",
			"STINK BUG BOLL SIZER", "STINK BUG THRESHOLD CALCULATOR", "STINK BUG SUMMARY" };

	/*
	 * private String[] footer = { "Stink Bug INTRODUCTION",
	 * "Stink Bug PESTS OF COTTON", "Stink Bug DAMAGE SYMPTOMS",
	 * "Stink Bug SCOUTING STEPS", "Stink Bug DYNAMIC THRESHOLD",
	 * "Stink Bug DECISION AID CARD", "Stink Bug BOLL SIZER",
	 * "Stink Bug SUMMARY" };
	 */

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_details);

		getActionBar().setDisplayHomeAsUpEnabled(true);

		mTextFooter = (TextView) findViewById(R.id.footerText);

		// Set the pager with an adapter
		ViewPager pager = (ViewPager) findViewById(R.id.viewPager);
		// pager.setAdapter(new TestPagerAdapter(this));
		pager.setAdapter(new TestAdapter((getSupportFragmentManager())));

		mActiveFrag = getIntent().getExtras().getInt("activeFrag", 0);
		updateFooter(footer[mActiveFrag]);

		Log.d("DetailsActivity", "active fragment:" + mActiveFrag);

		CirclePageIndicator circleIndicator = (CirclePageIndicator) findViewById(R.id.titles);
		circleIndicator.setViewPager(pager);
		circleIndicator.setCurrentItem(mActiveFrag);
		circleIndicator.setOnPageChangeListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onAttachFragment(Fragment fragment) {
		super.onAttachFragment(fragment);
		Log.d("Details", fragment.getTag() + "");
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageSelected(int arg0) {
		// TODO Auto-generated method stub
		Log.d("PagerListeber", "page: " + arg0 + " title: " + footer[arg0]);
		// getActionBar().setTitle(footer[arg0]);
		updateFooter(footer[arg0]);
	}

	private void updateFooter(String string) {
		mTextFooter.setText(string);
	}
}
