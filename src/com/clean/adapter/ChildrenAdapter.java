package com.clean.adapter;

import java.util.List;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.clean.R;
import com.clean.model.Item;

public class ChildrenAdapter extends BaseAdapter {
	private List<Item> items;
	public ChildrenAdapter(List<Item> items){
		this.items=items;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return items.size();
	}

	@Override
	public Item getItem(int position) {
		// TODO Auto-generated method stub
		return items.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return items.get(position)._id;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder;
		Item item=getItem(position);
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = LayoutInflater.from(parent.getContext()).inflate(
					R.layout.children_item, null);
			
			holder.titleTV = (TextView) convertView.findViewById(R.id.titleTV);
			
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		holder.titleTV.setText(item.name);
		
		return convertView;
	}
	static class ViewHolder {
		TextView titleTV;
	}
}
