package com.silvercloudgames.combinations;

import java.text.NumberFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.silvercloudgames.combinations.helpers.Calculator;
import com.silvercloudgames.combinations.helpers.Data;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends ActionBarActivity {
	private TextView tvTitle, tvData, tvResult;
	private Calculator calc;
	private Button bBack, bNew;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);
		
		assignFieldsToViews();
		
		Typeface font = Typeface.createFromAsset(getAssets(), "Chantelli_Antiqua.ttf");
		tvTitle.setTypeface(font);
		tvData.setTypeface(font);
		tvResult.setTypeface(font);
		bBack.setTypeface(font);
		bNew.setTypeface(font);
		
		// Obtain calculations and display for the text view
		calc = new Calculator();

	}
	
	public void assignFieldsToViews() {
		tvTitle = (TextView)findViewById(R.id.tv_title);
		tvData = (TextView)findViewById(R.id.tv_data);
		tvResult = (TextView)findViewById(R.id.tv_result);
		bBack = (Button)findViewById(R.id.b_back);
		bNew = (Button)findViewById(R.id.b_new_problem);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.result_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		switch (id) {
		case R.id.restart:
			Intent toMainMenu = new Intent(this, MainActivity.class);
			toMainMenu.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
			startActivity(toMainMenu);
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
	
	@Override
	protected void onResume() {
		super.onResume();
		
		// We are displaying this information from the onResume method because if the user decides to reset the application
		// then we will need to supply the new information given to the text views.
		
		// Retrieving all the information supplied in previous activities
		tvData.setText(Data.getAllDataToString());
		
		// Setting the results from all the information gathered
		String possibilities = formatNumbers(
				calc.getResult(Data.getTotalSize(), Data.getChoiceSize(), Data.isOrderMatters(), Data.isRepetitionAllowed()).toString());

		// Adjust text according to the possibility being plural or not
		if (possibilities.contentEquals("1"))
			tvResult.setText(possibilities	+ " possibility");
		else
			tvResult.setText(possibilities	+ " possibilities");
	}
	
	// Provides commas to our possibilities
	public String formatNumbers(String input) {
		  Pattern p = Pattern.compile("\\d+");
		  Matcher m = p.matcher(input);
		  NumberFormat nf = NumberFormat.getInstance();        
		  StringBuffer sb = new StringBuffer();
		  while(m.find()) {
		    String g = m.group();
		    m.appendReplacement(sb, nf.format(Double.parseDouble(g)));            
		  }
		  return m.appendTail(sb).toString();
		}
	
	// return to the previous activity
	public void onBackPressed(View view) {
		Intent back = new Intent(this, OrderActivity.class);
		back.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
		startActivity(back);
	}
	
	// return to main activity
	public void onNewPressed(View view) {
		Intent main = new Intent(this, MainActivity.class);
		main.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
		startActivity(main);
	}
	
}
