package com.jerry.note;

import com.jerry.note.db.DBManager;

import android.app.Application;

public class MyApp extends Application {

	DBManager dbManager;
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		dbManager=new DBManager(this);
	}
	
	public DBManager getDBManager()
	{
		return dbManager;
	}
}
