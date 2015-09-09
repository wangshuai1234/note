package com.jerry.note;


import java.util.List;

import com.jerry.note.adapter.GalleryAdapter;
import com.jerry.note.bean.Note;
import com.jerry.note.db.DBManager;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.widget.Gallery;

@SuppressWarnings("deprecation")
public class DetailNoteActivity extends Activity {

	private Gallery gallery;
	private List<Note> data;
	private Note note;
	private MyApp myApp;
	private DBManager dbManager;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detial_activity);
		initData();
		initView();
	}

	private void initView() {
		// TODO Auto-generated method stub
		gallery=(Gallery)findViewById(R.id.id_gallery);
		GalleryAdapter adapter=new GalleryAdapter(this, data);
		gallery.setAdapter(adapter);
		int position=0;
		for(position=0;position<data.size();position++)
		{
			Note temp=data.get(position);
			if(temp.getId()==note.getId())
				break;
		}
		gallery.setSelection(position);
	}

	private void initData() {
		// TODO Auto-generated method stub
		myApp=(MyApp)getApplication();
		dbManager=myApp.getDBManager();
		Intent intent=getIntent();
		Bundle bundle = intent.getExtras();
		note=(Note)bundle.get("note");
		data=dbManager.getAllNotes();
	}
}
