package com.clean.adapter;

import java.util.List;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.clean.CleanActivity;
import com.clean.R;
import com.clean.util.ColorUtil;

public class ParentAdapter extends BaseAdapter {
	private List<CleanActivity.Pair> items;;

	public ParentAdapter( List<CleanActivity.Pair> items) {
		this.items = items;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return items.size();
	}

	@Override
	public CleanActivity.Pair getItem(int position) {
		// TODO Auto-generated method stub
		return items.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder;
		CleanActivity.Pair pair=getItem(position);
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = LayoutInflater.from(parent.getContext()).inflate(
					R.layout.parent_item, null);
			
			holder.titleTV = (TextView) convertView.findViewById(R.id.titleTV);
			holder.countTV = (TextView) convertView.findViewById(R.id.countTV);
			
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		
		
		holder.titleTV.setText(pair.name);
		holder.countTV.setText(String.valueOf(pair.count));
		
		return convertView;
	}

	static class ViewHolder {
		TextView titleTV;
		TextView countTV;
	}
}
