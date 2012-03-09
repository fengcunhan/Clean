package com.clean.widget;

import java.util.List;

import android.content.Context;
import android.widget.ArrayAdapter;

public class CleanAdapter<T> extends ArrayAdapter<T> {

	public CleanAdapter(Context context, int textViewResourceId) {
		super(context, textViewResourceId);
		// TODO Auto-generated constructor stub
	}

	public CleanAdapter(Context context, int resource, int textViewResourceId,
			List<T> objects) {
		super(context, resource, textViewResourceId, objects);
		// TODO Auto-generated constructor stub
	}

	public CleanAdapter(Context context, int resource, int textViewResourceId,
			T[] objects) {
		super(context, resource, textViewResourceId, objects);
		// TODO Auto-generated constructor stub
	}

	public CleanAdapter(Context context, int resource, int textViewResourceId) {
		super(context, resource, textViewResourceId);
		// TODO Auto-generated constructor stub
	}

	public CleanAdapter(Context context, int textViewResourceId, List<T> objects) {
		super(context, textViewResourceId, objects);
		// TODO Auto-generated constructor stub
	}

	public CleanAdapter(Context context, int textViewResourceId, T[] objects) {
		super(context, textViewResourceId, objects);
		// TODO Auto-generated constructor stub
	}

}
