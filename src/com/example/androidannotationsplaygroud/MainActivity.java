package com.example.androidannotationsplaygroud;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.googlecode.androidannotations.annotations.Background;
import com.googlecode.androidannotations.annotations.Click;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.UiThread;
import com.googlecode.androidannotations.annotations.ViewById;

@EActivity
public class MainActivity extends Activity {
	
	@ViewById
	TextView textHello;
	
	@ViewById
	TextView textResult;
	
	@ViewById
	EditText editNumIterations;
	
	@ViewById
	Button buttonFire;
	
	
	@Click(R.id.buttonFire)
	void buttonClick() {
		int numIterations = Integer.valueOf(editNumIterations.getText().toString());
		calculatePi(numIterations);
	}
	
	@Background
	void calculatePi(int numIterations) {
		showProgressBar();
		double pi = Pi.calcPi(numIterations);
		showResult(pi);
	}

	@UiThread
	void showResult(double pi) {
		textResult.setText(String.valueOf(pi));
		setProgressBarIndeterminateVisibility(false);
	}
	
	@UiThread
	void showProgressBar() {
		setProgressBarIndeterminateVisibility(true);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
