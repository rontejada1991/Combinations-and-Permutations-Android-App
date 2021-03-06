package com.silvercloudgames.combinations;

import com.silvercloudgames.combinations.helpers.Data;

import android.support.v7.app.ActionBarActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {
	private TextView tvTitle;
	private Button bComb, bPerm, bNotSure;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        assignFieldsToViews();

        Typeface font = Typeface.createFromAsset(getAssets(), "Chantelli_Antiqua.ttf");
        tvTitle.setTypeface(font);
        bComb.setTypeface(font);
        bPerm.setTypeface(font);
        bNotSure.setTypeface(font);

    }
    
    public void assignFieldsToViews() {
        tvTitle = (TextView)findViewById(R.id.tv_title);
        bComb = (Button)findViewById(R.id.b_comb);
        bPerm = (Button)findViewById(R.id.b_perm);
        bNotSure = (Button)findViewById(R.id.b_not_sure);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
    	int id = item.getItemId();
		switch (id) {
		case R.id.about:
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage(R.string.about_message).setTitle(R.string.about_title);
			builder.setNeutralButton("Got It", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					
				}
			});
			
			AlertDialog dialog = builder.create();
			dialog.show();
			break;
		case R.id.rate:
			Intent rateIntent = new Intent(Intent.ACTION_VIEW);
			rateIntent.setData(Uri.parse("market://details?id=com.silvercloudgames.combinations"));
			startActivity(rateIntent);
			break;
		case R.id.apps:
			Intent appsIntent = new Intent(Intent.ACTION_VIEW);
			appsIntent.setData(Uri.parse("market://search?q=pub:Silvercloud+Games"));
			startActivity(appsIntent);
			break;
		case R.id.donate:
			Intent link = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.paypal.com/cgi-bin/webscr?cmd=_donations&business=FVBDUPSJKFNCE&lc=US&item_name=Silvercloud%20Games%20Support&currency_code=USD&bn=PP%2dDonationsBF%3abtn_donateCC_LG%2egif%3aNonHosted"));
			startActivity(link);
			break;
		}
        return super.onOptionsItemSelected(item);
    }
    
    public void onBeginPressed(View view) {
    	// Based on button press, we will skip the order activity and just set 
    	// the boolean ourselves. Choice size will check whether or not we need it.
    	switch (view.getId()) {
    	case R.id.b_comb:
    		Data.setOrderMatters(false);	// combination
    		Data.setKnown(true);	// user knows its comb
    		break;
    	case R.id.b_perm:
    		Data.setOrderMatters(true);	// permutation
    		Data.setKnown(true);	// user knows is perm
    		break;
    	case R.id.b_not_sure:
    		Data.setKnown(false);	// User does not know whether its a comb or perm
    		break;
    	}
    	// Next activity
    	Intent firstQuestion = new Intent(this, TotalSizeActivity.class);
		firstQuestion.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
    	startActivity(firstQuestion);
    }
    
}
