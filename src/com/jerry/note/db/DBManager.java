package com.jerry.note.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jerry.note.bean.Note;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {
	DBHelper mHelper;
	SQLiteDatabase mDatabase;
	public DBManager(Context context)
	{
		mHelper=new DBHelper(context);
		mDatabase=mHelper.getWritableDatabase();
	}
	
	/**
	 * 
	 * @param note
	 * @return
	 */
	public boolean insertNote(Note note)
	{
		ContentValues values=new ContentValues();
		values.put("title", note.getTitle());
		values.put("content", note.getContent());
		values.put("create_time", note.getCreateTime());
		values.put("last_edit_time", note.getLastModifyTime());
		values.put("month_of_time", note.getMonthOfTime());
		values.put("type", note.getType());
		long id=mDatabase.insert("tbl_note", null, values);
		if(id!=-1)
			return true;
		return false;
	}
	/**
	 * 
	 * @param note
	 * @return
	 */
	public boolean updateNote(Note note)
	{
		ContentValues values=new ContentValues();
		values.put("title", note.getTitle());
		values.put("content", note.getContent());
		values.put("last_edit_time", note.getLastModifyTime());
		values.put("type", note.getType());
		values.put("month_of_time", note.getMonthOfTime());
		String whereClause="id=?";
		String[] whereArgs={String.valueOf(note.getId())};
		int affect=mDatabase.update("tbl_note", values, whereClause, whereArgs);
		if(affect>0)
			return true;
		return false;
	}
	public List<Map<String,Note>>getAllNotes()
	{
		List<Map<String,Note>> list=new ArrayList<Map<String,Note>>();
		String[] columns=null;
		String selection=null;
		String[] selectionArgs=null;
		String groupBy="month_of_time";
		String having=null;
		String orderBy="create_time desc, month_of_time desc";
		Cursor cursor = mDatabase.query("tbl_note", columns, selection, selectionArgs, groupBy, having, orderBy);
		while(cursor.moveToNext())
		{
			Map<String, Note>item=new HashMap<String, Note>();
		}
		return null;
	}
}
