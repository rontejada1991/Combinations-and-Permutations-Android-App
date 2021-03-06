package com.silvercloudgames.combinations;

import com.silvercloudgames.combinations.helpers.Data;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

public class TotalSizeActivity extends ActionBarActivity {
	private TextView tvTitle, tvSub;
	private Button bBack, bNext;
	private NumberPicker npTotalSize;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total_size);
        
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
		npTotalSize = (NumberPicker)findViewById(R.id.np_total_size);
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.first_question_menu, menu);
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
			builder.setMessage(R.string.help_total_size).setTitle(R.string.help_title);
			builder.setNeutralButton("Got It", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					
				}
			});
			
			AlertDialog dialog = builder.create();
			dialog.show();
			break;
		}
        return super.onOptionsItemSelected(item);
    }
    
    // Return to the main menu
    public void onBackPressed(View view) {
		Intent toMainMenu = new Intent(this, MainActivity.class);
		toMainMenu.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
		startActivity(toMainMenu);
    }
    
    // When next is pressed, we will set to total size picked out by our user and go on to the next
    // question via an Intent
    public void onNextPressed(View view) {
    	Data.setTotalSize(npTotalSize.getValue());
    	
    	Toast newToast = Toast.makeText(this, "Total Size: " + Data.getTotalSize(), Toast.LENGTH_SHORT);
    	newToast.setGravity(Gravity.BOTTOM|Gravity.CENTER, 0, 0);
    	newToast.show();
    	
    	Intent secondQuestion = new Intent(this, RepeatActivity.class);
		secondQuestion.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
    	startActivity(secondQuestion);
    	
    }

}
