package com.ncsu.stinkbug;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.ncsu.stinkbug.frags.EighthFragment_Calc;
import com.ncsu.stinkbug.frags.FifthFragment_Threshold;
import com.ncsu.stinkbug.frags.FirstFragment_Intro;
import com.ncsu.stinkbug.frags.FourthFragment_ScoutingSteps;
import com.ncsu.stinkbug.frags.NinthFragment_Summary;
import com.ncsu.stinkbug.frags.SecondFragment_Photos;
import com.ncsu.stinkbug.frags.SeventhFragment_BollSizer;
import com.ncsu.stinkbug.frags.SixthFragment_Card1;
import com.ncsu.stinkbug.frags.ThirdFragment_DamageSymptoms;
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
			return FirstFragment_Intro.newInstance("FirstFragment, Instance 1");
		case 1:
			return SecondFragment_Photos.newInstance("SecondFragment, Instance 1");
		case 2:
			return ThirdFragment_DamageSymptoms.newInstance("ThirdFragment, Instance 1");
		case 3:
			return FourthFragment_ScoutingSteps.newInstance("FourthFragment, Instance 1");
		case 4:
			return FifthFragment_Threshold.newInstance("FifthFragment, Instance 1");
		case 5:
			return SixthFragment_Card1.newInstance("SixthFragment, Instance 1");
		case 6:
			return SeventhFragment_BollSizer.newInstance("SeventhFragment, Instance 1");
		case 7:
			return EighthFragment_Calc.newInstance("EighthFragment, Instance 1");
		case 8:
			return NinthFragment_Summary.newInstance("NinthFragment, Instance 1");
		default:
			return ThirdFragment_DamageSymptoms.newInstance("ThirdFragment, Default");
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
