package com.clean;

import android.app.ListActivity;
import android.os.Bundle;

import com.clean.database.DatabaseHelper;
import com.clean.widget.CleanListView;
import com.j256.ormlite.android.apptools.OpenHelperManager;

public class CleanBaseListActivity extends ListActivity {
	protected DatabaseHelper databaseHelper = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		initHelper();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (databaseHelper != null) {
			OpenHelperManager.releaseHelper();
			databaseHelper = null;
		}
	}

	protected DatabaseHelper initHelper() {
		if (databaseHelper == null) {
			databaseHelper = OpenHelperManager.getHelper(this,
					DatabaseHelper.class);
		}
		return databaseHelper;
	}
}
