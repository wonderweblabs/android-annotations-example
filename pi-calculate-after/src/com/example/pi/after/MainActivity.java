package com.example.pi.after;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.pi.after.R;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.Background;
import com.googlecode.androidannotations.annotations.Click;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.ItemClick;
import com.googlecode.androidannotations.annotations.UiThread;
import com.googlecode.androidannotations.annotations.ViewById;

@EActivity
public class MainActivity extends Activity {
	
	@ViewById
	ListView listViewResults;
	
	@ViewById(R.id.btCalculate)
	Button btCalculate;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		setContentView(R.layout.activity_main);
		getActionBar().setDisplayShowTitleEnabled(false);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@AfterViews
	void assignAdapter() {
		// Executed after the views were injected
		TwoLineAdapter listAdapter = new TwoLineAdapter(this);
		listViewResults.setAdapter(listAdapter);
	}
	
	@Click(R.id.btCalculate)
	void buttonClick() {
		setProgressBarIndeterminateVisibility(true);
		calculatePi();
	}
	
	@Background
	void calculatePi() {
		for (int i = 100000; i <= 500000; i+=10000) {
			Result result = Pi.calcPi(i);
			showResult(result);
		}
		setProgressBarIndeterminateVisibility(false);
	}

	@UiThread
	void showResult(Result result) {
		TwoLineAdapter listAdapter = (TwoLineAdapter) listViewResults.getAdapter();
		listAdapter.add(result);
		listAdapter.notifyDataSetChanged();
	}
	
	@ItemClick
	void listViewResultsItemClicked(Result item) {
		Toast.makeText(this, String.valueOf(item.pi), Toast.LENGTH_SHORT).show();
	}
}
