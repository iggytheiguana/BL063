package com.ncsu.stinkbug;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.ncsu.stinkbug.frags.EighthFragment;
import com.ncsu.stinkbug.frags.FifthFragment;
import com.ncsu.stinkbug.frags.FirstFragment;
import com.ncsu.stinkbug.frags.FourthFragment;
import com.ncsu.stinkbug.frags.NinthFragment;
import com.ncsu.stinkbug.frags.SecondFragment;
import com.ncsu.stinkbug.frags.SeventhFragment;
import com.ncsu.stinkbug.frags.SixthFragment;
import com.ncsu.stinkbug.frags.ThirdFragment;
import com.viewpagerindicator.IconPagerAdapter;

public class TestAdapter extends FragmentPagerAdapter implements IconPagerAdapter {

	protected static final int[] ICONS = new int[] { R.drawable.perm_group_calendar, R.drawable.perm_group_camera,
			R.drawable.perm_group_device_alarms, R.drawable.perm_group_location };

	public TestAdapter(FragmentManager fm) {
		super(fm);

	}

	@Override
	public Fragment getItem(int pos) {
		Log.i("TestAdapter", "pos: " + pos);
		switch (pos) {

		case 0:
			return FirstFragment.newInstance("FirstFragment, Instance 1");
		case 1:
			return SecondFragment.newInstance("SecondFragment, Instance 1");
		case 2:
			return ThirdFragment.newInstance("ThirdFragment, Instance 1");
		case 3:
			return FourthFragment.newInstance("FourthFragment, Instance 1");
		case 4:
			return FifthFragment.newInstance("FifthFragment, Instance 1");
		case 5:
			return SixthFragment.newInstance("SixthFragment, Instance 1");
		case 6:
			return SeventhFragment.newInstance("SeventhFragment, Instance 1");
		case 7:
			return EighthFragment.newInstance("EighthFragment, Instance 1");
		case 8:
			return NinthFragment.newInstance("NinthFragment, Instance 1");
		default:
			return ThirdFragment.newInstance("ThirdFragment, Default");
		}
	}

	@Override
	public int getCount() {

		return 9;
	}

	@Override
	public int getIconResId(int index) {
		return ICONS[index % ICONS.length];
	}

}
