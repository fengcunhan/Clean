package com.clean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.clean.adapter.ParentAdapter;
import com.clean.database.LogUtil;
import com.clean.model.Item;
import com.clean.widget.CleanListView;

public class CleanActivity extends CleanBaseListActivity {
	/** Called when the activity is first created. */

	private static final String TAG = "CleanActivity";
	
	public class Pair{
		public int _id;
		public String name;
		public int count;
		public int order;
	}
	
	private List<Pair> pairs;
	private ParentAdapter adapter;
	
	
	private CleanListView listView;
	public static final String CHILDREN_ACTION="android.intent.action.VIEW_CHILDREN";
	public static final String PARENT_ID="parent_id";
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		listView=(CleanListView)findViewById(android.R.id.list);
		
		pairs=new ArrayList<CleanActivity.Pair>();
		
		try {
			
			List<Item> items=databaseHelper.getAllChildren(0);
			for(Item item:items){
				Pair pair=new Pair();
				pair._id=item._id;
				pair.name=item.name;
				pair.count=item.childrenNum;
				pair.order=item.order;
				pairs.add(pair);
			}
			
			adapter=new ParentAdapter(pairs);
			
			listView.setAdapter(adapter);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			LogUtil.exception(TAG, e);
		}
		
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(CHILDREN_ACTION);
				intent.putExtra(PARENT_ID, pairs.get(position)._id);
				startActivity(intent);
			}
		});
	
	}
	
	
}