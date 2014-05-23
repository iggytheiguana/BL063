package com.ncsu.stinkbug;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	private Uri fileUri;
	private TextView mOptionFirst, mOptionSecond, mOptionThird, mOptionFourth, mOptionFifth, mOptionSixth,
			mOptionSeventh, mOptionEighth, mOptionNinth;

	private MyUtil mUtil;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mainmenu_copy);

		mUtil = new MyUtil(this);

		mOptionFirst = (TextView) findViewById(R.id.optionFirst);
		mOptionSecond = (TextView) findViewById(R.id.optionSecond);
		mOptionThird = (TextView) findViewById(R.id.optionThird);
		mOptionFourth = (TextView) findViewById(R.id.optionFourth);
		mOptionFifth = (TextView) findViewById(R.id.optionFifth);
		mOptionSixth = (TextView) findViewById(R.id.optionSixth);
		mOptionSeventh = (TextView) findViewById(R.id.optionSeventh);
		mOptionEighth = (TextView) findViewById(R.id.optionEighth);
		mOptionNinth = (TextView) findViewById(R.id.optionNinth);

		mOptionFirst.setOnClickListener(this);
		mOptionSecond.setOnClickListener(this);
		mOptionThird.setOnClickListener(this);
		mOptionFourth.setOnClickListener(this);
		mOptionFifth.setOnClickListener(this);
		mOptionSixth.setOnClickListener(this);
		mOptionSeventh.setOnClickListener(this);
		mOptionEighth.setOnClickListener(this);
		mOptionNinth.setOnClickListener(this);
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
	public void onClick(View v) {
		int tag = Integer.parseInt(v.getTag().toString());

		Intent intent = new Intent(this, DetailsActivity.class);
		intent.putExtra("activeFrag", tag);
		startActivity(intent);

	}

}
