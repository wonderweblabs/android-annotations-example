package com.example.pi.before;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity 
								 implements OnClickListener, 
								 			OnItemClickListener {

	private ListView listViewResults;
	
	private Button btCalculate;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		setContentView(R.layout.activity_main);
		getActionBar().setDisplayShowTitleEnabled(false);
		
		findViews();
		assignListeners();
		assignAdapter();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	private void findViews() {
		this.listViewResults = (ListView) findViewById(R.id.listViewResults);
		this.btCalculate = (Button) findViewById(R.id.btCalculate);
	}
	
	private void assignListeners() {
		this.btCalculate.setOnClickListener(this);
		this.listViewResults.setOnItemClickListener(this);
	}
	
	private void assignAdapter() {
		// Executed after the views were injected
		TwoLineAdapter listAdapter = new TwoLineAdapter(this);
		listViewResults.setAdapter(listAdapter);
	}

	private void showResult(Result result) {
		TwoLineAdapter listAdapter = (TwoLineAdapter) listViewResults.getAdapter();
		listAdapter.add(result);
		listAdapter.notifyDataSetChanged();
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.btCalculate) {
			setProgressBarIndeterminateVisibility(true);
			CalculatePiTask task = new CalculatePiTask();
			task.execute();
		}
	}

	@Override
	public void onItemClick(AdapterView<?> adapterView, View v, int position, long id) {
		if (adapterView.getId() == R.id.listViewResults) {
			TwoLineAdapter adapter = (TwoLineAdapter) listViewResults.getAdapter();
			Result result = adapter.getItem(position);
			Toast.makeText(this, String.valueOf(result.pi), Toast.LENGTH_SHORT).show();
		}
	}
	
	private class CalculatePiTask extends AsyncTask<Void, Result, Result> {

		@Override
		protected Result doInBackground(Void... params) {
			Result result = null;
			for (int i = 100000; i <= 500000; i+=10000) {
				result = Pi.calcPi(i);
				publishProgress(result);
			}
			return result;
		}
		
		protected void onProgressUpdate(Result... progress) {
	        showResult(progress[0]);
	    }
	
	    protected void onPostExecute(Result result) {
	        showResult(result);
	        setProgressBarIndeterminateVisibility(false);
	    }
	}
}
