package com.ncsu.stinkbug.frags;

import java.util.Hashtable;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

import com.ncsu.stinkbug.R;

public class EighthFragment_Calc extends Fragment implements OnFocusChangeListener {

	private EditText mtfWeekOfBloom, mtfNumBollsSampled, mtfDamagedBolls;
	private TextView mlblTreatmentThreshold, mlblYourDamage;
	private Hashtable<String, String> mTable = new Hashtable<String, String>();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.threshold_calc, container, false);

		mtfWeekOfBloom = (EditText) v.findViewById(R.id.tfWeekOfBloom);
		mtfNumBollsSampled = (EditText) v.findViewById(R.id.tfNumBolls);
		mtfDamagedBolls = (EditText) v.findViewById(R.id.tfDamagedBolls);

		mtfWeekOfBloom.setOnFocusChangeListener(this);
		mtfNumBollsSampled.setOnFocusChangeListener(this);
		mtfDamagedBolls.setOnFocusChangeListener(this);
		// focus change listener

		mtfWeekOfBloom.setOnEditorActionListener(new OnEditorActionListener() {

			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				int result = actionId & EditorInfo.IME_MASK_ACTION;

				switch (result) {

				case EditorInfo.IME_ACTION_NEXT:
					setTreatmentThreshold();
					break;
				}
				return false;
			}
		});
		mtfDamagedBolls.setOnEditorActionListener(new OnEditorActionListener() {

			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				int result = actionId & EditorInfo.IME_MASK_ACTION;

				switch (result) {
				case EditorInfo.IME_ACTION_DONE:
				case EditorInfo.IME_ACTION_NEXT:
					calculateYourDamage();

					break;
				}
				return false;
			}
		});

		mTable.put("1", "50%");
		mTable.put("2", "30%");
		mTable.put("3", "10%");
		mTable.put("4", "10%");
		mTable.put("5", "10%");
		mTable.put("6", "20%");
		mTable.put("7", "30%");
		mTable.put("8", "50%");

		mlblTreatmentThreshold = (TextView) v.findViewById(R.id.tvTreatmentThreshold);
		mlblYourDamage = (TextView) v.findViewById(R.id.tvYourDamage);

		return v;
	}

	public static EighthFragment_Calc newInstance(String text) {

		EighthFragment_Calc f = new EighthFragment_Calc();
		Bundle b = new Bundle();
		b.putString("msg", text);

		f.setArguments(b);

		return f;
	}

	private void calculateYourDamage() {
		// Log.e("EighthFrag", "calculateYourDamage");
		/**
		 * formula: Your damage = (Damaged bolls / num bolls sampled) * 100
		 */
		try {
			float valDamagedBolls = Float.parseFloat(mtfDamagedBolls.getText().toString());
			float valNumBollsSample = Float.parseFloat(mtfNumBollsSampled.getText().toString());

			int result = (int) ((valDamagedBolls / valNumBollsSample) * 100.00);
			mlblYourDamage.setText(result + "%");
			// Log.d("EighthFrag",
			// String.format("Num bools: %s, Damaged Bolls: %s, result: %d",
			// mtfNumBollsSampled
			// .getText().toString(), mtfDamagedBolls.getText().toString(),
			// result));
		} catch (Exception e) {
			Log.e("Eighth Frag", "Exception: " + e.getMessage());
		}
	}

	private void setTreatmentThreshold() {
		String text = "---";

		String key = mtfWeekOfBloom.getText().toString();

		if (mTable.containsKey(key)) {
			text = mTable.get(key);
		}

		mlblTreatmentThreshold.setText(text);

	}

	@Override
	public void onFocusChange(View v, boolean hasFocus) {
		calculateYourDamage();
		setTreatmentThreshold();

	}
}
