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
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

public class ChoiceSizeActivity extends ActionBarActivity {
	private TextView tvTitle, tvSub;
	private Button bBack, bNext;
	private NumberPicker npChoiceSize;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_choice_size);
		
		assignFieldsToViews();
		
		Typeface font = Typeface.createFromAsset(getAssets(), "Chantelli_Antiqua.ttf");
		tvTitle.setTypeface(font);
		tvSub.setTypeface(font);
		bBack.setTypeface(font);
		bNext.setTypeface(font);
	}

	private void assignFieldsToViews() {
		tvTitle = (TextView)findViewById(R.id.tv_title);
		tvSub = (TextView)findViewById(R.id.tv_sub);
		bBack = (Button)findViewById(R.id.b_back);
		bNext = (Button)findViewById(R.id.b_next);
		npChoiceSize = (NumberPicker)findViewById(R.id.np_choice_size);
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
			builder.setMessage(R.string.help_choice_size).setTitle(R.string.help_title);
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
	
	
	// Go back to the previous activity
	public void onBackPressed(View view) {
		Intent toRepeat = new Intent(this, RepeatActivity.class);
		toRepeat.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
		startActivity(toRepeat);
	}
	
	// Sets the choice size in data before moving on to the next activity
	public void onNextPressed(View view) {
		if(isSmallerThanTotal(npChoiceSize.getValue())) {
			Data.setChoiceSize(npChoiceSize.getValue());
			
			Toast newToast = Toast.makeText(this, "Choice Size: " + Data.getChoiceSize(), Toast.LENGTH_SHORT);
			newToast.setGravity(Gravity.BOTTOM|Gravity.CENTER, 0, 0);
			newToast.show();
			
			// If user already knows if they want comb/perm, we skip to the result screen
			if (Data.isKnown()) {
				Intent result = new Intent(this, ResultActivity.class);
				result.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
				startActivity(result);
			} else {	//  otherwise go to the order activity to choose it
				Intent fourthQuestion = new Intent(this, OrderActivity.class);
				fourthQuestion.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
				startActivity(fourthQuestion);
			}
			
		} else {
			Toast newToast = Toast.makeText(this, "Sample Size must be smaller than or equal to " + Data.getTotalSize() + " (Total Size)", Toast.LENGTH_LONG);
			newToast.setGravity(Gravity.BOTTOM|Gravity.CENTER, 0, 0);
			newToast.show();
		}
		
	}
	
	// If the user is attempting to select more choice items than there are in the total pool while there is no repetition
	// allowed, we need to catch that. If repetition is allowed however, then no comparison needs to be made, so we check that first.
	public boolean isSmallerThanTotal(int value) {
		if (Data.isRepetitionAllowed())
			return true;
		else if (!Data.isRepetitionAllowed() && Data.getTotalSize() >= value) 
			return true;
		else
			return false;
	}
	
}
