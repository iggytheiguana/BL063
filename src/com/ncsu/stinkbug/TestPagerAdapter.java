package com.ncsu.stinkbug;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.view.View;

public class TestPagerAdapter extends PagerAdapter {
	private Activity mActivity;

	public TestPagerAdapter(Activity activity) {
		mActivity = activity;
	}

	public Object instantiateItem(View collection, int position) {

		int resId = 0;
		switch (position) {
		case 0:
			// resId = R.id.page_one;
			break;
		case 1:
			// resId = R.id.page_two;
			break;
		}
		return mActivity.findViewById(resId);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 2;
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0 == ((View) arg1);
	}

}
