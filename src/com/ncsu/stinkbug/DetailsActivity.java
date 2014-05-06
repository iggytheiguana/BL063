package com.ncsu.stinkbug;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.viewpagerindicator.CirclePageIndicator;

public class DetailsActivity extends FragmentActivity implements OnPageChangeListener {

	private Uri fileUri;
	private MyUtil mUtil;
	private TextView mTextFooter;
	private int mActiveFrag;
	private String[] footer = { "STINK BUG INTRODUCTION", "STINK BUG PESTS OF COTTON", "STINK BUG DAMAGE SYMPTOMS",
			"STINK BUG SCOUTING STEPS", "STINK BUG DYNAMIC THRESHOLD", "STINK BUG DECISION AID CARD",
			"STINK BUG BOLL SIZER", "STINK BUG THRESHOLD CALCULATOR", "STINK BUG SUMMARY" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_details);

		getActionBar().setDisplayHomeAsUpEnabled(true);

		mTextFooter = (TextView) findViewById(R.id.footerText);

		mUtil = new MyUtil(this);

		// Set the pager with an adapter
		ViewPager pager = (ViewPager) findViewById(R.id.viewPager);
		// pager.setAdapter(new TestPagerAdapter(this));
		pager.setAdapter(new TestAdapter((getSupportFragmentManager())));

		mActiveFrag = getIntent().getExtras().getInt("activeFrag", 0);
		updateFooter(footer[mActiveFrag]);

		//		Log.d("DetailsActivity", "active fragment:" + mActiveFrag);

		CirclePageIndicator circleIndicator = (CirclePageIndicator) findViewById(R.id.titles);
		circleIndicator.setViewPager(pager);
		circleIndicator.setCurrentItem(mActiveFrag);
		circleIndicator.setOnPageChangeListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);

		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_photo:
			openCamera();
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}

	public void openCamera() {
		Intent camIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

		// create a file to save the image
		fileUri = MyUtil.getOutputMediaFileUri(MyUtil.MEDIA_TYPE_IMAGE);

		// set the image file name
		camIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);

		startActivityForResult(camIntent, MyUtil.CAM_REQUEST_CODE);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == MyUtil.CAM_REQUEST_CODE) {
			if (resultCode == RESULT_OK) {
				// Image captured and saved to fileUri specified in the Intent

				// Toast.makeText(this, "Image saved to:\n" + data.getData(),
				// Toast.LENGTH_LONG).show();
				mUtil.sendEmail(fileUri);

			} else if (resultCode == RESULT_CANCELED) {
				// User cancelled the image capture
			} else {
				// Image capture failed, advise user
				Toast.makeText(this, "Image capture failed.", Toast.LENGTH_LONG).show();
			}
		}

	}

	@Override
	public void onAttachFragment(Fragment fragment) {
		super.onAttachFragment(fragment);
		// Log.d("Details", fragment.getTag() + "");
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
		// Log.d("PagerListeber", "page: " + arg0 + " title: " + footer[arg0]);
		// getActionBar().setTitle(footer[arg0]);
		updateFooter(footer[arg0]);
	}

	private void updateFooter(String string) {
		mTextFooter.setText(string);
	}

}
