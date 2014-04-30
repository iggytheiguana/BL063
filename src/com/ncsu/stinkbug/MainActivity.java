package com.ncsu.stinkbug;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
	private final int CAM_REQUEST_CODE = 100;
	public static final int MEDIA_TYPE_IMAGE = 1;
	public static final int MEDIA_TYPE_VIDEO = 2;
	private Uri fileUri;
	private TextView mOptionFirst, mOptionSecond, mOptionThird, mOptionFourth, mOptionFifth, mOptionSixth,
			mOptionSeventh, mOptionEighth, mOptionNinth;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mainmenu);

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
		fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE); // create
															// a
															// file
															// to
		// save the image
		camIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri); // set the image
																// file
																// name
		startActivityForResult(camIntent, CAM_REQUEST_CODE);
	}

	/** Create a file Uri for saving an image or video */
	private static Uri getOutputMediaFileUri(int type) {
		return Uri.fromFile(getOutputMediaFile(type));
	}

	/** Create a File for saving an image or video */
	private static File getOutputMediaFile(int type) {
		// To be safe, you should check that the SDCard is mounted
		// using Environment.getExternalStorageState() before doing this.

		File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
				"ThePrinceMap");
		// This location works best if you want the created images to be shared
		// between applications and persist after your app has been uninstalled.

		// Create the storage directory if it does not exist
		if (!mediaStorageDir.exists()) {
			if (!mediaStorageDir.mkdirs()) {
				Log.d("MyCameraApp", "failed to create directory");
				return null;
			}
		}

		// Create a media file name
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		File mediaFile;
		if (type == MEDIA_TYPE_IMAGE) {
			mediaFile = new File(mediaStorageDir.getPath() + File.separator + "IMG_" + timeStamp + ".jpg");
		} else if (type == MEDIA_TYPE_VIDEO) {
			mediaFile = new File(mediaStorageDir.getPath() + File.separator + "VID_" + timeStamp + ".mp4");
		} else {
			return null;
		}

		return mediaFile;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == CAM_REQUEST_CODE) {
			if (resultCode == RESULT_OK) {
				// Image captured and saved to fileUri specified in the Intent

				// Toast.makeText(this, "Image saved to:\n" + data.getData(),
				// Toast.LENGTH_LONG).show();
				sendEmail(fileUri);

			} else if (resultCode == RESULT_CANCELED) {
				// User cancelled the image capture
			} else {
				// Image capture failed, advise user
				Toast.makeText(this, "Image capture failed.", Toast.LENGTH_LONG).show();
			}
		}

	}

	private void sendEmail(Uri uri) {
		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType("text/plain");
		intent.putExtra(Intent.EXTRA_EMAIL, new String[] { "abc@xyzzz.com" });
		intent.putExtra(Intent.EXTRA_SUBJECT, "subject here");
		intent.putExtra(Intent.EXTRA_TEXT, "body text");
		// File root = Environment.getExternalStorageDirectory();
		// File file = new File(root, xmlFilename);
		// if (!file.exists() || !file.canRead()) {
		// Toast.makeText(this, "Attachment Error", Toast.LENGTH_SHORT).show();
		// finish();
		// return;
		// }
		// Uri uri = Uri.parse("file://" + file);
		intent.putExtra(Intent.EXTRA_STREAM, uri);
		startActivity(Intent.createChooser(intent, "Send email..."));
	}

	@Override
	public void onClick(View v) {
		int tag = Integer.parseInt(v.getTag().toString());
		// Log.d("MainActivity", "active frag:" + tag);

		Intent intent = new Intent(this, DetailsActivity.class);
		intent.putExtra("activeFrag", tag);
		startActivity(intent);

	}

}
