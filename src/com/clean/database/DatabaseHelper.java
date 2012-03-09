package com.clean.database;

import java.sql.SQLException;
import java.util.List;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.clean.model.Item;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

	private static final String DATABASE_NAME = "clean_todolist.db";
	private static final int DATABASE_VERSION = 1;

	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);

	}

	@Override
	public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
		try {
			Log.i(DatabaseHelper.class.getName(), "onCreate");
			TableUtils.createTable(connectionSource, Item.class);
			initData();
		} catch (SQLException e) {
			Log.e(DatabaseHelper.class.getName(), "Can't create database", e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource,
			int oldVersion, int newVersion) {

	}

	public void save(Item item) throws SQLException {
			BaseDaoImpl<Item, Integer> itemDao = getDao(Item.class);
			itemDao.create(item);
	}
	
	public void delete(Item item) throws SQLException{
		BaseDaoImpl<Item, Integer> itemDao = getDao(Item.class);
		itemDao.delete(item);
	}
	
	public void update(Item item) throws SQLException{
		BaseDaoImpl<Item, Integer> itemDao = getDao(Item.class);
		itemDao.update(item);
	}
	
	public List<Item> getAllItems() throws SQLException{
		BaseDaoImpl<Item, Integer> itemDao = getDao(Item.class);
		return itemDao.queryForAll();
	}
	
	public List<Item> getAllChildren(int parentId) throws SQLException{
		BaseDaoImpl<Item, Integer> itemDao = getDao(Item.class);
		QueryBuilder<Item, Integer> qb = itemDao.queryBuilder();
		qb.where().eq(Item.PARENT_ID, parentId);
		qb.orderBy(Item.ITEM_ORDER, true);
		PreparedQuery<Item> query = qb.prepare();
		
		return itemDao.query(query);
	}
	public Item getItem(String title) throws SQLException{
		BaseDaoImpl<Item, Integer> itemDao = getDao(Item.class);
		return itemDao.queryForEq(Item.NAME_FIELD_NAME, title).get(0);
	}
	
	
	public int getNextOrder() throws SQLException {
		int largest = 0;
		BaseDaoImpl<Item, Integer> itemDao;

		itemDao = getDao(Item.class);
		List<Item> items = itemDao.queryForAll();

		for (Item item : items) {
			if (item.order > largest) {
				largest = item.order;
			}
		}
		largest += 1;

		return largest;

	}

	private void initData(){
		
		try {
			Item item=new Item();
			item.childrenNum=6;
			item.name="It's for test";
			item.order=1;
			item.parentId=0;
			save(item);
			
			item.childrenNum=8;
			item.name="It's for test 2";
			item.order=2;
			item.parentId=0;
			save(item);
			
			for(int i=0;i<4;i++){
				item.childrenNum=0;
				item.name="It's for test "+i;
				item.order=3+i;
				item.parentId=0;
				save(item);
			}
			for(int i=0;i<6;i++){
				item.parentId=1;
				item.is_active=false;
				item.name="item "+i;
				item.order=i+1;
				save(item);
			}
			

			for(int i=0;i<8;i++){
				item.parentId=2;
				item.is_active=false;
				item.name="item test2 "+i;
				item.order=i+1;
				save(item);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
