package com.example.androidannotationsplaygroud;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class TwoLineAdapter extends BaseAdapter {
	
	private List<Result> data = new ArrayList<Result>();
	private Context context;
	
	public TwoLineAdapter(Context context) {
		this.context = context;
	}

	public void add(Result result) {
		data.add(result);
	}
	
	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Result getItem(int position) {
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
		
		View parentView = inflater.inflate(android.R.layout.two_line_list_item, null);
		
		if (parentView != null) {
			TextView tv1 = (TextView) parentView.findViewById(android.R.id.text1);
			TextView tv2 = (TextView) parentView.findViewById(android.R.id.text2);
			
			Result result = data.get(position);
			
			tv1.setText(String.valueOf(result.pi));
			tv2.setText(result.hits + " hits - " + result.misses + " misses - " + result.iterations + " iterations");
		}
		return parentView;
	}

}
