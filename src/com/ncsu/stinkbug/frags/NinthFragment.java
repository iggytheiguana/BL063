package com.ncsu.stinkbug.frags;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.ncsu.stinkbug.R;

public class NinthFragment extends Fragment {
	private String mFile = "file:///android_asset/Summary.html";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.eighth_frag, container, false);

		WebView w = (WebView) v.findViewById(R.id.firstWebView);
		w.loadUrl(mFile);

		return v;
	}

	public static NinthFragment newInstance(String text) {

		NinthFragment f = new NinthFragment();
		Bundle b = new Bundle();
		b.putString("msg", text);

		f.setArguments(b);

		return f;
	}
}
