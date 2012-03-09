package com.clean;

import java.sql.SQLException;
import java.util.List;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import com.clean.adapter.ChildrenAdapter;
import com.clean.model.Item;

public class ChildrenActivity extends CleanBaseListActivity {
	private List<Item> items;
	private ChildrenAdapter adapter;
	private int parentId;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.children);
		Intent intent=getIntent();
		if(intent!=null){
			parentId=intent.getIntExtra(CleanActivity.PARENT_ID, 0);
		}
		try {
			items=databaseHelper.getAllChildren(parentId);
			
			adapter=new ChildrenAdapter(items);
			setListAdapter(adapter);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
