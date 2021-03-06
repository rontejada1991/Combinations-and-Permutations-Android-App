package com.silvercloudgames.combinations;

import com.silvercloudgames.combinations.helpers.Data;

import android.support.v7.app.ActionBarActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class RepeatActivity extends ActionBarActivity {
	private TextView tvTitle, tvSub;
	private Button bBack, bNext;
	private ToggleButton tbRepeat;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_repeat);
		
		assignFieldsToViews();
		
		Typeface font = Typeface.createFromAsset(getAssets(), "Chantelli_Antiqua.ttf");
		tvTitle.setTypeface(font);
		tvSub.setTypeface(font);
		bBack.setTypeface(font);
		bNext.setTypeface(font);
		tbRepeat.setTypeface(font);		
	}
	
	private void assignFieldsToViews() {
		tvTitle = (TextView)findViewById(R.id.tv_title);
		tvSub = (TextView)findViewById(R.id.tv_sub);
		bBack = (Button)findViewById(R.id.b_back);
		bNext = (Button)findViewById(R.id.b_next);
		tbRepeat = (ToggleButton)findViewById(R.id.tb_repeat);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.standard_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		switch (id) {
		case R.id.help:
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage(R.string.help_repeat).setTitle(R.string.help_title);
			builder.setNeutralButton("Got It", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					
				}
			});
			
			AlertDialog dialog = builder.create();
			dialog.show();
			break;
		case R.id.restart:
			Intent toMainMenu = new Intent(this, MainActivity.class);
			toMainMenu.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
			startActivity(toMainMenu);
			break;

		}
		return super.onOptionsItemSelected(item);
	}
	
	// Sends us back to the previous activity if button is pressed by closing this one.
	public void onBackPressed(View view) {
		Intent toTotalSize = new Intent(this, TotalSizeActivity.class);
		toTotalSize.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
		startActivity(toTotalSize);
	}
	
	// Sends us to the next activity question but not before storing the data the user provided
	public void onNextPressed(View view) {
		Data.setRepetitionAllowed(tbRepeat.isChecked());
		
		Toast newToast = Toast.makeText(this, "Repetition Allowed: " + Data.isRepetitionAllowed(), Toast.LENGTH_SHORT);
		newToast.setGravity(Gravity.BOTTOM|Gravity.CENTER, 0, 0);
		newToast.show();
		
		Intent thirdQuestion = new Intent(this, ChoiceSizeActivity.class);
		thirdQuestion.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
		startActivity(thirdQuestion);
		
	}
	
}
